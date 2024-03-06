#include <ESP8266WiFi.h> //include libraries
#include <Ultrasonic.h>
#include <Servo.h>
#include <ESP8266HTTPClient.h>

const char* ssid = "SSD"; //insert wifi SSID
const char* password = "########"; //insert wifi password
const char* serverUrl = "http://your_server/save_data.php"; // insert server URL

#define TRIGGER_PIN D1 // Pin D1 for ultrasonic trigger
#define ECHO_PIN D2    // Pin D2 for ultrasonic echo
#define MAX_DISTANCE 200 // Maximum distance to measure in centimeters

#define BLUE_LED D5    // Pin D5 for blue LED
#define RED_LED D6     // Pin D6 for red LED

#define OBJECT_TRIGGER_PIN D3 // Pin D3 for object detection ultrasonic trigger
#define OBJECT_ECHO_PIN D4    // Pin D4 for object detection ultrasonic echo
#define OBJECT_SERVO_PIN D7   // Pin D7 for servo motor

Ultrasonic ultrasonic(TRIGGER_PIN, ECHO_PIN);
Ultrasonic objectUltrasonic(OBJECT_TRIGGER_PIN, OBJECT_ECHO_PIN);
Servo servoMotor;

const int openAngle = 120;  // Servo angle to open the bin
const int closedAngle = 0;  // Servo angle to close the bin
const long maxObjectDistance = 30;  // Maximum distance for the bin to open 
const long minObjectDistance = 3;   // Minimum distance for the bin to close 

void setup() {
  Serial.begin(115200);
  delay(10);

  // Connect to Wi-Fi
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);

  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());

  pinMode(BLUE_LED, OUTPUT);
  pinMode(RED_LED, OUTPUT);

  // Initial state: turn on blue LED
  digitalWrite(BLUE_LED, HIGH);
  
  servoMotor.attach(OBJECT_SERVO_PIN);
  servoMotor.write(closedAngle);
  delay(500);
}

void loop() {
  unsigned int distance = getDistance();
  Serial.print("Distance: ");
  Serial.print(distance);
  Serial.println(" cm");

  long objectDistance = getObjectDistance();
  Serial.print("ObjectDistance: ");
  Serial.print(objectDistance);
  Serial.println(" cm");

  if (distance <= 10) {
    // If distance is less than or equal to 10cm, turn on red LED and turn off blue LED
    digitalWrite(BLUE_LED, LOW);
    digitalWrite(RED_LED, HIGH);
  } else {
    // turn on blue LED and turn off red LED
    digitalWrite(BLUE_LED, HIGH);
    digitalWrite(RED_LED, LOW);
  }

  // Check if the object distance is within the specified range
  if (objectDistance >= minObjectDistance && objectDistance <= maxObjectDistance) {
    openBin();
  } else {
    closeBin();
  }

  delay(1000); // delays or perform other tasks in the loop
}

unsigned int getDistance() {
  return ultrasonic.distanceRead();
}

long getObjectDistance() {
  return objectUltrasonic.read(CM);
}

void openBin() {
  servoMotor.write(openAngle);
  delay(1000);  // delay to keep the bin open for a certain duration
}

void closeBin() {
  servoMotor.write(closedAngle);
  delay(500);
}
void sendData(unsigned int distance) {
    WiFiClient client;

    if (client.connect(serverUrl, 80)) {

        client.setTimeout(5000); // Set a timeout of 5 seconds

        String postData = "distance=" + String(distance);
        client.println("POST /save_data.php HTTP/1.1");
        client.println("Host: your_server");
        client.println("Content-Type: application/x-www-form-urlencoded");
        client.println("Content-Length: " + String(postData.length()));
        client.println();
        client.print(postData);

        Serial.println("Data sent to server");
    } else {
        Serial.println("Connection to server failed");
    }

    client.stop();
}


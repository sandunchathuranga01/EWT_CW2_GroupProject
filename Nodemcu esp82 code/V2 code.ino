#include <ESP8266WiFi.h>
#include <Ultrasonic.h>
#include <Servo.h>
#include <ESP8266HTTPClient.h>

const char* ssid = "Rishi_HBB";
const char* password = "#bathalaya#";
const char* serverAddress = "192.168.8.148";  
const int serverPort = 8080;  

#define TRIGGER_PIN D1 
#define ECHO_PIN D2    
#define MAX_DISTANCE 200 

#define BLUE_LED D5    
#define RED_LED D6     

#define OBJECT_TRIGGER_PIN D3 
#define OBJECT_ECHO_PIN D4    
#define OBJECT_SERVO_PIN D7   

Ultrasonic ultrasonic(TRIGGER_PIN, ECHO_PIN);
Ultrasonic objectUltrasonic(OBJECT_TRIGGER_PIN, OBJECT_ECHO_PIN);
Servo servoMotor;

const int openAngle = 120;  
const int closedAngle = 0;  
const long maxObjectDistance = 30;  
const long minObjectDistance = 3;   

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
    
    digitalWrite(BLUE_LED, LOW);
    digitalWrite(RED_LED, HIGH);

    // Send data to the server
    sendDataToServer(distance);
  } else {

    digitalWrite(BLUE_LED, HIGH);
    digitalWrite(RED_LED, LOW);
    sendDataToServer(distance);
  }


  if (objectDistance >= minObjectDistance && objectDistance <= maxObjectDistance) {
    openBin();
  } else {
    closeBin();
  }

  delay(1000); 
}

unsigned int getDistance() {
  return ultrasonic.distanceRead();
}

long getObjectDistance() {
  return objectUltrasonic.read(CM);
}

void openBin() {
  servoMotor.write(openAngle);
  delay(1000);  
}

void closeBin() {
  servoMotor.write(closedAngle);
  delay(500);
}

void sendDataToServer(unsigned int distance) {
  HTTPClient http;

  String url = "http://" + String(serverAddress) + ":" + String(serverPort) + "/api/v1/smartbinHardware/updateDistance?binId=1&distance=" + String(distance);

  Serial.print("Sending data to server: ");
  Serial.println(url);

  WiFiClient client; 

  http.begin(client, url);

  int httpCode = http.GET();
  if (httpCode > 0) {
    Serial.printf("[HTTP] GET... code: %d\n", httpCode);

    if (httpCode == HTTP_CODE_OK) {
      String payload = http.getString();
      Serial.println(payload);
    }
  } else {
    Serial.printf("[HTTP] GET... failed, error: %s\n", http.errorToString(httpCode).c_str());
  }

  http.end();
}

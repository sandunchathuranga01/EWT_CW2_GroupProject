function saveCollector(){
    let firstname= $('#firstname').val();
    let lastname= $('#lastname').val();
    let email= $('#email').val();
    let password= $('#Password').val();
    let drivingid= $('#drivingID').val();
    let nic= $('#nicPassport').val();
    let contactnumber= $('#contact').val();
    let address= $('#address').val();
    let modelOfvehicale= $('#modelOfVehicle').val();
    let vehicleregno= $('#vehicleID').val();

    console.log(firstname);
    console.log(lastname);
    console.log(email);
    console.log(password);
    console.log(drivingid);
    console.log(nic);
    console.log(contactnumber);
    console.log(modelOfvehicale);
    console.log(address);
    console.log(vehicleregno);

    $.ajax({
        method:"POST",
        contentType:"application/json",
        url:"http://localhost:8080/api/v1/collector/saveCollector",
        async:true,
        data:JSON.stringify({
            "firstname":firstname,
            "lastname": lastname,
            "email": email,
            "password": password,
            "drivingid": drivingid,
            "nic": nic,
            "contactnumber": contactnumber,
            "address": address,
            "modelOfvehicale": modelOfvehicale,
            "vehicleregno": vehicleregno,
        }),
        success:function (data){
            alert("Your Registration request Saved")
            window.location.href = "../CollectorSignin/collector_login.html";
        },
        error:function (xhr, exception){
            alert("SORRY Please Try Again")
        }
    })
}
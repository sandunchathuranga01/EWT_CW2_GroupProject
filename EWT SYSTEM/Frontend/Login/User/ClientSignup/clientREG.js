function registerNewClient(){
    let fname= $('#firstname').val();
    let lname= $('#lastName').val();
    let email= $('#email').val();
    let password= $('#password').val();
    let mobileNumber= $('#contact_number').val();
    let address= $('#address').val();

    console.log(fname);
    console.log(lname);
    console.log(email);
    console.log(password);
    console.log(mobileNumber);
    console.log(address);

    $.ajax({
        method:"POST",
        contentType:"application/json",
        url:"http://localhost:8080/api/v1/Client/saveUser",
        async:true,
        data:JSON.stringify({
            "fname":fname,
            "lname": lname,
            "email": email,
            "password": password,
            "mobileNumber": mobileNumber,
            "address": address
        }),
        success:function (data){
            alert("Your Registration  Successfully Saved")
            window.location.href = "../ClientSignin/ClientSignin.html";
        },
        error:function (xhr, exception){
            alert("SORRY Please Try Again")
        }
    })
}

GetClients()
function GetClients(){

    $.ajax({
        method:"GET",
        url: "http://localhost:8080/api/v1/collector/getAllCollector",
        async:true,
        success: function (data) {
            if (data.code==="00"){
                $('#Allcollectors').empty();
                for (let Collectors of data.content) {
                    let ID = Users.id
                    let Name = Users.firstname
                    let Email = Users.email
                    let Contact_number = Users.contactnumber
                    let NIC = Users.nic
                    let Address = Users.address


                    var row = `<tr><td>${ID}</td><td>${Name}</td><td>${Contact_number}</td><td>${NIC}</td><td>${Address}</td><td>${Email}</td></tr>`;
                    $('#Allcollectors').append(row);
                }
            }
        },
        error: function (xhr, exception) {
            alert("Error")
        }
    })

}
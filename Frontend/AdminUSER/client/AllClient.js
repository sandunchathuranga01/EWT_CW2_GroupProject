function rowClicked(event) {
    // Remove the 'selected-row' class from all rows
    $('#AllClient tr').removeClass('selected-row');

    // Add the 'selected-row' class to the clicked row
    $(event.target).closest('tr').addClass('selected-row');

    // Update the delete button to get the location ID from the selected row
    let id = $(event.target).closest('tr').find('td:first').text();
    $('#id').val(id);
}

function deleteClient() {
    // Get the locationID directly from the selected row
    let id = $('#AllClient tr.selected-row').find('td:first').text();

    $.ajax({
        method: "DELETE",
        url: "http://localhost:8080/api/v1/Client/deleteUser/" + id,
        async: true,
        success: function (data) {
            alert("Deleted");
            GetClients();
        },
        error: function (xhr, exception) {
            alert("Error");
        }
    });
}


GetClients()
function GetClients(){

    $.ajax({
        method:"GET",
        url: "http://localhost:8080/api/v1/Client/getAllUser",
        async:true,
        success: function (data) {
            if (data.code==="00"){
                $('#AllClient').empty();
                for (let Users of data.content) {
                    let id = Users.id
                    let fname = Users.fname
                    let lname = Users.lname
                    let email = Users.email
                    let mobileNumber = Users.mobileNumber
                    let address = Users.address


                    var row = `<tr><td>${id}</td><td>${fname}</td><td>${lname}</td><td>${email}</td><td>${mobileNumber}</td><td>${address}</td></tr>`;
                    $('#AllClient').append(row);
                }
            }
        },
        error: function (xhr, exception) {
            alert("Error")
        }
    })

}
  
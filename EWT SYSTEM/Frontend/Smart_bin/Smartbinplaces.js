function saveSmartBinPlace(){
    let address= $('#Address').val();
    let name= $('#Name').val();
    let lat= $('#DLatitude').val();
    let lng= $('#DLongitude').val();

    console.log(address);
    console.log(name);
    console.log(lat);
    console.log(lng);

    $.ajax({
        method:"POST",
        contentType:"application/json",
        url:"http://localhost:8080/api/v1/disposal_place/saveDisposalPlace",
        async:true,
        data:JSON.stringify({
            "locationID":"",
            "name": name,
            "address": address,
            "lat": lat,
            "lng": lng
        }),
        success:function (data){
            alert("Disposal Place Successfully Saved")
        },
        error:function (xhr, exception){
            alert("SORRY Please Try Again")
        }
    })
}



function rowClicked(event) {
    // Remove the 'selected-row' class from all rows
    $('#disposalPlaseTable tr').removeClass('selected-row');

    // Add the 'selected-row' class to the clicked row
    $(event.target).closest('tr').addClass('selected-row');

    // Update the delete button to get the location ID from the selected row
    let locationID = $(event.target).closest('tr').find('td:first').text();
    $('#Location_ID').val(locationID);
}

function deleteSmartBinPlace() {
    // Get the locationID directly from the selected row
    let locationID = $('#disposalPlaseTable tr.selected-row').find('td:first').text();

    $.ajax({
        method: "DELETE",
        url: "http://localhost:8080/api/v1/disposal_place/deleteDisposalPlace/" + locationID,
        async: true,
        success: function (data) {
            alert("Deleted");
            getAllDisposalPlace();
        },
        error: function (xhr, exception) {
            alert("Error");
        }
    });
}

getAllDisposalPlace()
function getAllDisposalPlace() {
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/v1/disposal_place/getAllDisposalPlaces",
        async: true,
        success: function (data) {
            if (data.code === "00") {
                $('#disposalPlaseTable').empty();
                for (let disposalPlace of data.content) {
                    let Location_ID = disposalPlace.locationID
                    let Name = disposalPlace.name
                    let Address = disposalPlace.address
                    let Latitude = disposalPlace.lat
                    let Longitude = disposalPlace.lng

                    var row = `<tr><td>${Location_ID}</td><td>${Name}</td><td>${Address}</td><td>${Latitude}</td><td>${Longitude}</td></tr>`;
                    $('#disposalPlaseTable').append(row);
                }
            }
        },
        error: function (xhr, exception) {
            alert("Error")
        }
    });
}

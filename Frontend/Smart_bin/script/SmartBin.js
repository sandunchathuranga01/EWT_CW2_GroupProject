function saveSmartBin() {
    let address = $('#BinAddress').val();
    let binCapacity = $('#BinCapacity').val();
    let lat = $('#BinLat').val();
    let lng = $('#BinLng').val();

    console.log(address);
    console.log(binCapacity);
    console.log(lat);
    console.log(lng);

    $.ajax({
        method: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/api/v1/smartbinHardware/saveSmartbin",
        async: true,
        data: JSON.stringify({
            "binID": "",
            "binCapacity": binCapacity,
            "address": address,
            "lat": lat,
            "lng": lng
        }),
        success: function (data) {
            alert("Disposal Place Successfully Saved")
        },
        error: function (xhr, exception) {
            alert("SORRY Please Try Again")
        }
    })
}

function rowClicked(event) {
    // Remove the 'selected-row' class from all rows
    $('#SmartBinTable tr').removeClass('selected-row');

    // Add the 'selected-row' class to the clicked row
    $(event.target).closest('tr').addClass('selected-row');

    // Update the delete button to get the location ID from the selected row
    let locationID = $(event.target).closest('tr').find('td:first').text();
    $('#binID').val(locationID);
}

function deleteSmartBin() {
    // Get the locationID directly from the selected row
    let binID = $('#SmartBinTable tr.selected-row').find('td:first').text();

    $.ajax({
        method: "DELETE",
        url: "http://localhost:8080/api/v1/smartbinHardware/deleteSmartbin/" + binID,
        async: true,
        success: function (data) {
            alert("Deleted");
            getAllSmartBins();
        },
        error: function (xhr, exception) {
            alert("Error");
        }
    });
}

getAllSmartBins()

function getAllSmartBins() {

    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/v1/smartbinHardware/getAllSmartbin",
        async: true,
        success: function (data) {
            if (data.code === "00") {
                $('#SmartBinTable').empty();
                for (let smartbins of data.content) {
                    let SmartBin_ID = smartbins.binID
                    let BinCapacity = smartbins.binCapacity
                    let Address = smartbins.address
                    let Latitude = smartbins.lat
                    let Longitude = smartbins.lng


                    var row = `<tr><td>${SmartBin_ID}</td><td>${Address}</td><td>${BinCapacity}</td><td>${Latitude}</td><td>${Longitude}</td></tr>`;
                    $('#SmartBinTable').append(row);
                }
            }
        },
        error: function (xhr, exception) {
            alert("Error")
        }
    })

}
function createTrackBinButton(binID, binLat, binLng) {
    var trackBinButton = $('<button>').addClass('client-button').text('Track Bin');
    trackBinButton.on('click', function() {
        navigator.geolocation.getCurrentPosition(function (position) {
            var startPoint = position.coords.latitude + ',' + position.coords.longitude;
            var endPoint = binLat + ',' + binLng;
            window.open('https://www.google.com/maps/dir/' + startPoint + '/' + endPoint);
        }, function (error) {
            console.error("Error getting user's location:", error);
        });
    });

    return trackBinButton;
}
$(document).ready(function() {
    getAllSmartBins2();
});
function getAllSmartBins2() {
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/v1/smartbinHardware/getAllNOTFilledSmartbin",
        async: true,
        success: function (data) {
            if (data.code === "00") {
                $('#NOtFilledSmartBinContainer').empty();
                for (let smartbin of data.content) {
                    let SmartBin_ID = smartbin.binID;
                    let distance=smartbin.distance;
                    let Bin_Capaciy=smartbin.binCapacity;
                    let binLat = smartbin.lat;
                    let binLng = smartbin.lng;
                    let address=smartbin.address;

                    var trackBinButton = createTrackBinButton(SmartBin_ID, binLat, binLng);


                    var binInfo = `
                        <div class="information-container">
                            <div class="client-info-container information-client1">
                                <img class="client-image" src="pictures/Vector 3.png" alt="Client Image">
                                <div class="client-details">
                                    <div class="client-id" id="binID">${SmartBin_ID}</div>
                                </div>
                                
                                <div class="client-buttons-container">
                                <div>
                                <form action="#" id="request-form">
                                <div class="prediction-section">
                                </div>
                                <div class="gray-rectangle">
                                <div class="button-description">Bin Capacity(L) :-${Bin_Capaciy}</div>
                                </div>
                                <div class="gray-rectangle">
                                <div class="button-description">Bin Filled level :-${distance}</div>
                                </div>
                                <div class="gray-rectangle">
                                <div class="button-description">Bin Address :-${address}</div>
                                </div>
                                </div>
                                </form>
                                </div> 
                                </div>
                            </div>
                        </div>`;

                    // Append the track bin button to the client-buttons-container
                    binInfo = $(binInfo).find('.client-buttons-container').append(trackBinButton).end();

                    // Append the constructed HTML to the container
                    $('#NOtFilledSmartBinContainer').append(binInfo);
                }
            }
        },
        error: function (xhr, exception) {
            alert("Error");
        }
    });
}


$(document).ready(function() {
    getAllSmartBins3();
});

function getAllSmartBins3() {
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/v1/smartbinHardware/getAllFilledSmartbin",
        async: true,
        success: function (data) {
            if (data.code === "00") {
                $('#FilledSmartBinContainer').empty();
                for (let smartbin of data.content) {
                    let SmartBin_ID = smartbin.binID;
                    let distance=smartbin.distance;
                    let Bin_Capaciy=smartbin.binCapacity;
                    let binLat = smartbin.lat;
                    let binLng = smartbin.lng;
                    let address=smartbin.address;

                    var trackBinButton = createTrackBinButton(SmartBin_ID, binLat, binLng);


                    var binInfo = `
                        <div class="information-container">
                            <div class="client-info-container information-client1">
                                <img class="client-image" src="pictures/Vector 3.png" alt="Client Image">
                                <div class="client-details">
                                    <div class="client-id" id="binID">${SmartBin_ID}</div>
                                </div>
                                <div class="client-buttons-container">
                                <div>
                                <form action="#" id="request-form">
                                <div class="prediction-section">
                                </div>
                                <div class="gray-rectangle">
                                <div class="button-description">Bin Capacity(L) :-${Bin_Capaciy}</div>
                                </div>
                                <div class="gray-rectangle">
                                <div class="button-description">Bin Filled level :-${distance}</div>
                                </div>
                                <div class="gray-rectangle">
                                <div class="button-description">Bin Address :-${address}</div>
                                </div>
                                </div>
                                </form>
                                </div> 
                                </div>
                            </div>
                        </div>`;

                    // Append the track bin button to the client-buttons-container
                    binInfo = $(binInfo).find('.client-buttons-container').append(trackBinButton).end();

                    // Append the constructed HTML to the container
                    $('#FilledSmartBinContainer').append(binInfo);
                }
            }
        },
        error: function (xhr, exception) {
            alert("Error");
        }
    });
}


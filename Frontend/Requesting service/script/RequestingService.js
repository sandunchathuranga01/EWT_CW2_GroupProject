
function saveRequestingService() {
    // Retrieve values from input fields
    let contactNumber = $('#contactNumber').val();
    let address = $('#locationid').val();
    let requiredService = $('#requiredService').val();
    let currentSituation = $('#currentSituation').val();

    // Log values to console for debugging
    console.log("Contact Number:", contactNumber);
    console.log("Address:", address);
    console.log("Required Service:", requiredService);
    console.log("Current Situation:", currentSituation);

    // Send AJAX POST request to save requesting service
    $.ajax({
        method: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/api/v1/requestingService/saveRequestingService",
        async: true,
        data: JSON.stringify({
            "requestid": "",
            "contact_number": contactNumber,
            "address": address,
            "service_type": requiredService,
            "situation": currentSituation
        }),
        success: function (data) {
            alert("Your request has been successfully submitted. Thank you!");
            // Optionally, you can clear the input fields or perform other actions here
        },
        error: function (xhr, exception) {
            alert("Sorry, an error occurred. Please try again.");
        }
    });
}

function rowClicked(event) {
    // Remove the 'selected-row' class from all rows
    $('#requests tr').removeClass('selected-row');

    // Add the 'selected-row' class to the clicked row
    $(event.target).closest('tr').addClass('selected-row');

    // Update the delete button to get the request ID from the selected row
    let requestId = $(event.target).closest('tr').find('td:first').text();
    $('#requestid').val(requestId);
}

function deleteRequestingService() {
    // Get the request ID directly from the selected row
    let requestId = $('#requests tr.selected-row').find('td:first').text();

    // Send AJAX DELETE request to delete requesting service
    $.ajax({
        method: "DELETE",
        url: "http://localhost:8080/api/v1/requestingService/deleteRequestingService/" + requestId,
        async: true,
        success: function (data) {
            alert("Request deleted successfully.");
            getAllRequestingService(); // Refresh the table after deletion
        },
        error: function (xhr, exception) {
            alert("Error deleting request.");
        }
    });
}
$(document).ready(function () {
    getAllRequestingService();
});
function getAllRequestingService() {
    // Retrieve all requesting services from the server
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/v1/requestingService/getAllRequestingService",
        async: true,
        success: function (data) {
            if (data.code === "00") {
                $('#requests').empty();
                data.content.forEach(function (request) {
                    // Populate table rows with requesting service data
                    let requestId = request.requestid;
                    let contactNumber = request.contact_number;
                    let address = request.address;
                    let requiredService = request.service_type;
                    let currentSituation = request.situation;

                    var row = `<tr><td>${requestId}</td><td>${contactNumber}</td><td>${address}</td><td>${requiredService}</td><td>${currentSituation}</td></tr>`;
                    $('#requests').append(row);
                });
            }
        },
        error: function (xhr, exception) {
            alert("Error fetching requesting services.");
        }
    });
}

// Load requesting services when the page is ready
$(document).ready(function () {
    getAllRequestingService();
});

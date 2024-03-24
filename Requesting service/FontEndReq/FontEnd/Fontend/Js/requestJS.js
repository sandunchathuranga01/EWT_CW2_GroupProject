
    getAllRequests();

    function saveRequest() {
        let requiredService = $("#exampleInputEmail2").val();
        let id = $("#exampleInputEmail3").val();
        let csituation = $("#exampleInputEmail4").val();
        let cnumber = $("#exampleInputEmail5").val();

        $.ajax({
            method: "POST",
            contentType: "application/json",
            url: "http://localhost:8080/api/v1/user/saveRequest",
            async: true,
            data: JSON.stringify({
                "ID": id,
                "CNumber": cnumber,
                "Csituation": csituation,
                "locationId": "",
                "requiredService": requiredService
            }),
            success: function (data) {
                alert("saved");
            },
            error: function (xhr, exception) {
                alert("Error");
            }
        });
    }


    function updateRequest() {
        let locationId = $("#exampleInputEmail1").val();
        let requiredService = $("#exampleInputEmail2").val();
        let id = $("#exampleInputEmail3").val();
        let csituation = $("#exampleInputEmail4").val();
        let cnumber = $("#exampleInputEmail5").val();

        $.ajax({
            method: "PUT",
            contentType: "application/json",
            url: "http://localhost:8080/api/v1/user/updateRequest",
            async: true,
            data: JSON.stringify({
                "ID": id,
                "Cnumber": cnumber,
                "Csituation": csituation,
                "locationId": locationId,
                "requiredService": requiredService
            }),
            success: function (data) {
                alert("updated");
                getAllRequests();
            },
            error: function (xhr, exception) {
                alert("Error");
            }
        });
    }

    function deleteRequest() {
        let locationId = $("#exampleInputEmail1").val();

        $.ajax({
            method: "DELETE",
            contentType: "application/json",
            url: "http://localhost:8080/api/v1/user/deleteRequest/" + locationId,
            async: true,
            success: function (data) {
                alert("Deleted");
                getAllRequests();
            },
            error: function (xhr, exception) {
                alert("Error");
            }
        });
    }

    function getAllRequests() {
        $.ajax({
            method: "GET",
            contentType: "application/json",
            url: "http://localhost:8080/api/v1/user/getAllRequests",
            async: true,
            success: function (data) {
                if (data.code === "00") {
                    $('#RequestTable').empty();
                    for (let Request of data.content) {
                        let locationId = Request.locationID;
                        let Cnumber = Request.Cnumber;
                        let id = Request.id;
                        let csituation = Request.csituation;
                        let cnumber = Request.cnumber;

                        var row = `<tr>
                            <td>${locationId}</td>
                            <td>${Cnumber}</td>
                            <td>${id}</td>
                            <td>${csituation}</td>
                            <td>${cnumber}</td>
                        </tr>`;
                        $('#RequestTable').append(row);
                    }
                }
            },
            error: function (xhr, exception) {
                alert("Error");
            }
        });
    }

    $(document).ready(function () {
        $(document).on('click', '#RequestTable', function () {
            var col0 = $(this).find('td:eq(0)').text();
            var col1 = $(this).find('td:eq(1)').text();
            var col2 = $(this).find('td:eq(2)').text();
            var col3 = $(this).find('td:eq(3)').text();
            var col4 = $(this).find('td:eq(4)').text();

            $('#exampleFormControlInput1').val(col0);
            $('#exampleFormControlInput2').val(col1);
            $('#exampleFormControlInput3').val(col2);
            $('#exampleFormControlInput4').val(col3);
            $('#exampleFormControlInput5').val(col4);

        })
    })



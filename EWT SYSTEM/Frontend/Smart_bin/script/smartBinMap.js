google.charts.load("current", {
    "packages": ["map"],
    "mapsApiKey": "AIzaSyBSoQrR3G-RybhYs3rwFMaDvJbPs-dgod4"
});

google.charts.setOnLoadCallback(getDisposalPlaces);

function searchArea() {
    var cityName = document.getElementById('searchInput').value;
    var geocoder = new google.maps.Geocoder();
    geocoder.geocode({
        'address': cityName
    }, function(results, status) {
        if (status === 'OK') {
            var location = results[0].geometry.location;
            var latLng = [location.lat(), location.lng()];
            var address = results[0].formatted_address;
            showCityAreaOnMap(latLng);
        } else {
            alert('Geocode was not successful for the following reason: ' + status);
        }
    });
}

function showCityAreaOnMap(latLng) {
    var mapOptions = {
        zoom: 10,
        center: new google.maps.LatLng(latLng[0], latLng[1]),
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById('map_div'), mapOptions);
    var marker = new google.maps.Marker({
        position: new google.maps.LatLng(latLng[0], latLng[1]),
        map: map
    });
}


function getDisposalPlaces() {
    function getUserLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(showUserLocation);
        } else {
            alert("Geolocation is not supported by this browser.");
        }
    }

    function showUserLocation(position) {
        mapData.push([position.coords.latitude, position.coords.longitude, "Your Location"]);
        var dataTable = google.visualization.arrayToDataTable([
            ['Lat', 'Long', 'Address'],
            ...mapData
        ]);

        // Create map
        var map = new google.visualization.Map(document.getElementById('map_div'));
        map.draw(dataTable, {
            showTooltip: true,
            showInfoWindow: true
        });

        // Attach event listeners to each disposal place marker
        google.visualization.events.addListener(map, 'select', function() {
            var selection = map.getSelection();
            if (selection.length > 0) {
                var item = selection[0];
                var latLng = dataTable.getValue(item.row, 0) + ',' + dataTable.getValue(item.row, 1);
                var address = dataTable.getValue(item.row, 2);
                showConnectButton(latLng, address);
            }
        });
    }

    function showConnectButton(latLng, address) {
        var connectButton = document.getElementById('connectButton');
        connectButton.style.display = 'block';
        connectButton.onclick = function() {
            connectToGoogleMaps(latLng, address);
        };
    }

    function connectToGoogleMaps(latLng, address) {
        var startPoint = document.getElementById('startPoint');
        var endPoint = document.getElementById('endPoint');

        // Get the user's current location
        navigator.geolocation.getCurrentPosition(function (position) {
            startPoint.value = position.coords.latitude + ',' + position.coords.longitude; // User's location
            endPoint.value = latLng;

            // Open Google Maps with directions
            window.open('https://www.google.com/maps/dir/' + startPoint.value + '/' + endPoint.value);

            // Log disposal place details
            console.log("Disposal Place Address:", address);
        }, function (error) {
            console.error("Error getting user's location:", error);
        });
    }

    // Array to store disposal places and user's location
    let mapData = [];

    // Get disposal places
    $.ajax({
        method: "get",
        url: "http://localhost:8080/api/v1/smartbinHardware/getAllSmartbin",
        async: true,
        success: function (data) {
            if (data.code === "00") {
                mapData = data.content.map(smartbin => [smartbin.lat, smartbin.lng, smartbin.address]);
                getUserLocation();
            }
        }
    });
}
function loginUser() {
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;

    // Make sure both email and password are provided
    if (email === '' || password === '') {
        alert("Please enter both email and password");
        return;
    }

    // Make API request to login
    fetch('http://localhost:8080/api/v1/Client/login?email=' + email + '&pws=' + password)
        .then(response => response.json())
        .then(data => {
            if (data.status === 200) {
                alert("Login Successful");
                // Redirect to dashboard page upon successful login
                window.location.href = 'Dashboard.html';
            } else {
                alert( data.message);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert("An error occurred while processing your request");
        });
}
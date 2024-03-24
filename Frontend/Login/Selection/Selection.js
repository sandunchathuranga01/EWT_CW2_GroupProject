function redirectToLogin(userType) {
    if (userType === 'client') {
        window.location.href = '../User/ClientSignin/ClientSignin.html';
    } else if (userType === 'waste-collector') {
        window.location.href = '../Collector/CollectorSignin/collector_login.html';
    } else if (userType === 'administrator') {
        window.location.href = '../Admin/AdminSignin/LoginAdmin.html';
    }
}

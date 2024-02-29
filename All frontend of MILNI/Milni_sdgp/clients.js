function toggleInterface() {
    // Toggle the visibility of elements in the first interface
    document.querySelectorAll('.client-info-container').forEach(function (el) {
      el.style.display = 'none';
    });
  
    // Toggle the visibility of elements in the second interface
    document.querySelectorAll('.information-client2').forEach(function (el) {
      el.style.display = 'block';
    });
  
    // You can add more logic here for other elements if needed
  }
  
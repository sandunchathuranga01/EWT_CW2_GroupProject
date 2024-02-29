document.addEventListener('DOMContentLoaded', function() {
  document.getElementById('request-form').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent form submission
    
    // Hide the form
    document.getElementById('request-form').classList.add('hidden');
    
    // Show the success message
    document.querySelector('.grey-rectangle').classList.remove('hidden');
  });
});

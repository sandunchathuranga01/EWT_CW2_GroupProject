document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("filledBinsButton").addEventListener("click", function() {
      var notFilledBinSection = document.querySelector(".NotFilledBin");
      var buttonContainer = document.querySelector(".button-container");
      var photoContainer = document.querySelector(".photo-container");
      
      if (notFilledBinSection.style.display === "none") {
        notFilledBinSection.style.display = "block"; // Show the Not filled bin section
        buttonContainer.style.display = "none"; // Hide the button container
        photoContainer.style.display = "none"; // Hide the photo container
      } else {
        notFilledBinSection.style.display = "none"; // Hide the Not filled bin section
        buttonContainer.style.display = "block"; // Show the button container
        photoContainer.style.display = "block"; // Show the photo container
      }
    });
  });
  
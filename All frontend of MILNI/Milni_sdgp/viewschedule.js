document.addEventListener("DOMContentLoaded", function () {
    const searchInput = document.querySelector(".search-input");
    const locationButton = document.querySelector(".location-button");
    const currentPanel = document.querySelector(".panel");
    const secondPage = document.querySelector(".second-page");

    locationButton.addEventListener("click", function () {
      if (searchInput.value.trim() !== "") {
        currentPanel.style.display = "none";
        secondPage.style.display = "block";
      }
    });
  });
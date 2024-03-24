let slideIndex = 0;
  showSlides();
  
  //manages the image slideshow
  function showSlides() {
      let i;
    // Get all elements with the class "mySlides" (assumed to be slides) from the DOM
      let slides = document.getElementsByClassName("mySlides");
  
    // Get all elements with the class "dot" (assumed to be navigation dots) from the DOM
    let dots = document.getElementsByClassName("dot");
    for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";  
      }
  
    // Increment the slideIndex to move to the next slide
    slideIndex++;
    if (slideIndex > slides.length) {slideIndex = 1}    
    for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" active", "");
    }
    slides[slideIndex-1].style.display = "block";  
    dots[slideIndex-1].className += " active";
    setTimeout(showSlides, 4000); // Change image every 2 seconds
  }
  
 
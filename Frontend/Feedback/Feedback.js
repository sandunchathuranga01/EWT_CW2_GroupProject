function changeImageBackground(id) {
    var images = document.querySelectorAll('.img-row img');
    images.forEach(function(image) {
        image.style.backgroundColor = '';
    });

    var clickedImage = document.getElementById(id);
    clickedImage.style.backgroundColor = '#fff'; 
}

const scrollUpButton = document.querySelector('.scrollUpButton');

scrollUpButton.addEventListener('click', () => {
    window.scrollTo({
        top: 0,
        left: 0,
        behavior: 'smooth',
    })
});

//returnToTopButton function
window.addEventListener('scroll', () => {
    if(window.scrollY === 0){
        scrollUpButton.style.bottom = "-3vw";
    }
    else {
        scrollUpButton.style.bottom = "0";
    }
});
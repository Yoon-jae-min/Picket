function GotoInfo(title){
    fetch("http://localhost:8080/performInfo",{
        method: "POST",
        headers:{
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            title: title,
        }),
    }).then(response => {
        if(response.ok){
            window.location.href = "/performInfo";
        }
    });
}

const performanceList = document.querySelectorAll('.product');
const carouselUrlList = document.querySelectorAll('.GotoInfo');

//List Goto Info
performanceList.forEach(performance => {
    performance.addEventListener('click', () => {
        const title = performance.querySelector('.goodscontent_title').textContent;
        GotoInfo(title);
    });
})

//carousel Goto Info
carouselUrlList.forEach(carouselUrl => {
    carouselUrl.addEventListener('click', () => {
        const title = carouselUrl.querySelector('.carouselTitle').textContent;
        GotoInfo(title);
    });
});

document.addEventListener('DOMContentLoaded', () => {
    const moveDistance = (0 - (((window.innerWidth - 250) / 8)*3 + 30));

    itemBox.style.left = `${moveDistance}px`;
});




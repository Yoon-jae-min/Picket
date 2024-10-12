let itemBox = document.querySelector('.item_box');
let prevBtn = document.querySelector('.prevBtn');
let nextBtn = document.querySelector('.nextBtn');
let debounceTimer;
let isAnimating = false;

function moveCarousel(direct){

    clearTimeout(debounceTimer);
    debounceTimer = setTimeout(() => {
        if(!isAnimating){
            isAnimating = true;
            let items = document.querySelectorAll('.item');

            if(direct == "prev"){
                let appendItem = items[items.length - 3].cloneNode(true);

                itemBox.style.transform = "translateX(" + (items[0].offsetWidth + 30) + "px)";
                items.forEach((item) => {
                    if(item == items[items.length - 5]) item.classList.add("center");
                    else item.classList.remove("center");
                })

                items.forEach((item, index) => {
                    item.style.opacity = (index == 0 || index == 4) ? "0.5" : "1";
                })

                setTimeout(() => {
                    itemBox.style.transition = "none";
                    itemBox.insertBefore(appendItem, items[0]);
                    itemBox.removeChild(items[items.length - 1]);
                    itemBox.style.transform = "translateX(0px)";
                }, 300);

                setTimeout(() => {
                    itemBox.style.transition = "all 0.3s";
                    isAnimating = false;
                }, 400);
            }
            else if(direct == "next"){
                let appendItem = items[2].cloneNode(true);

                itemBox.style.transform = "translateX(" + -(items[0].offsetWidth + 30) + "px)";

                items.forEach((item) => {
                    if(item == items[items.length - 3]) item.classList.add("center");
                    else item.classList.remove("center");
                })

                items.forEach((item, index) => {
                    item.style.opacity = (index == 2 || index == 6) ? "0.5" : "1";
                })

                setTimeout(() => {
                    itemBox.style.transition = "none";
                    itemBox.appendChild(appendItem);
                    itemBox.removeChild(items[0]);
                    itemBox.style.transform = "translateX(0px)";
                }, 300);

                setTimeout(() => {
                    itemBox.style.transition = "all 0.3s";
                    isAnimating = false;
                }, 400);
            }
            else if(direct == "auto"){
                items.forEach((item) => {
                    item.style.transition = "all 1s";
                    if(item == items[items.length - 3]) item.classList.add("center");
                    else item.classList.remove("center");
                })

                itemBox.style.transition = "all 1s"

                let appendItem = items[2].cloneNode(true);

                itemBox.style.transform = "translateX(" + -(items[0].offsetWidth + 30) + "px)";

                items.forEach((item, index) => {
                    item.style.opacity = (index == 2 || index == 6) ? "0.5" : "1";
                })

                setTimeout(() => {
                    itemBox.style.transition = "none";
                    itemBox.appendChild(appendItem);
                    itemBox.removeChild(items[0]);
                    itemBox.style.transform = "translateX(0px)";
                }, 1000);

                setTimeout(() => {
                    items.forEach((item) => {
                        item.style.transition = "all 0.3s";
                    })
                    itemBox.style.transition = "all 0.3s";
                    isAnimating = false;
                }, 1100);
            }
        }
    }, 100);
}

prevBtn.addEventListener('click', function(){
    moveCarousel("prev");
});
nextBtn.addEventListener('click', function(){
    moveCarousel("next");
});

setInterval(function(){
    moveCarousel("auto");
}, 5000);


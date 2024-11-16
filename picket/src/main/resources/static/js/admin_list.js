//list업데이트
function renderList(category){
    fetch("http://localhost:8080/adminList",{
        method: "POST",
        headers: {
          'Content-Type':'application/json'
        },
        body: JSON.stringify({
                category: category
        })
    }).then((response) => {
        return response.json();
    }).then((data) => {
        updateList(data);
    }).catch((err) => {
        console.log((err));
    });
}

function updateList(data) {
    const listContainer = document.querySelector('.listContainer');
    listContainer.innerHTML = '';
    data.forEach(performance => {
        const contentDiv = document.createElement('div');
        contentDiv.className = 'content';
        const img = document.createElement('img');
        img.className = 'listImg';
        img.src = performance.imgUrl;
        const textContainerDiv = document.createElement('div');
        textContainerDiv.className = 'textContainer';
        const titleBox = document.createElement('p');
        titleBox.className = 'titleBox';
        titleBox.textContent = performance.title;
        textContainerDiv.appendChild(titleBox);
        contentDiv.appendChild(img);
        contentDiv.appendChild(textContainerDiv);
        listContainer.appendChild(contentDiv);
    });

    const contents = document.querySelectorAll(".content");
    contents.forEach((content) => {
        content.addEventListener("click", function(){
            const titleText = content.querySelector('.titleBox').innerText;
            renderInfo(titleText);
        });
    });
}

const selectBox = document.querySelector('.selectBox');

selectBox.addEventListener('change', function(){
    renderList(selectBox.value);
});


//info페이지 요청
function renderInfo(titleText){
    fetch("http://localhost:8080/adminInfo",{
        method: "POST",
        headers:{
            "Content-Type":"application/json",
        },
        body: JSON.stringify({
            titleText: titleText,
        }),
    }).then(response => {
        if (response.ok) {
            window.location.href = "/adminInfo"; // 성공 시 페이지 이동
        } else {
            console.log("Request failed.");
        }
    }).catch(err => console.log(err));
}

const contents = document.querySelectorAll(".content");

contents.forEach((content) => {
    content.addEventListener("click", function(){
        const titleText = content.querySelector('.titleBox').innerText;
        renderInfo(titleText);
    });
});

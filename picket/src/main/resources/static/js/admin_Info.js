function deleteInfo(title){
    fetch('http://localhost:8080/deleteInfo',{
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            title: title
        }),
    }).then(response => {
        return response.json()
    }).then(data => {
        window.location.href = data.redirectUrl;
    }).catch(error => {
        console.error("에러발생: ", error);
    });
}

const buttonBox = document.querySelector(".deleteButton");

buttonBox.addEventListener('click', function(){
    title = document.querySelector(".infoTitleBox").textContent;
    deleteInfo(title);
});
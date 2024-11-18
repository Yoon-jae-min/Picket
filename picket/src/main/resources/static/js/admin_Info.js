//버튼
const deleteButton = document.querySelector(".deleteButton");

//이벤트 리스너
deleteButton.addEventListener('click', function(){
    const deleteResult = confirm("정말 삭제하시겠습니까?");
    const title = document.querySelector(".infoTitleBox").textContent;

    if(deleteResult){
        deleteInfo(title);
    }
});

//메소드
//공연 정보 삭제
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


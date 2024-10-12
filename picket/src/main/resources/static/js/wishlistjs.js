const wishListCancelButton = document.querySelector(".wishListButton");
const wishListCheckBoxs = document.querySelectorAll(".wishListCheckbox");
let performanceTitle = [];

if(wishListCancelButton != null){
    wishListCancelButton.addEventListener("click", function(){
        let performanceTitle = [];

        wishListCheckBoxs.forEach(wishListCheckBox => {
            if(wishListCheckBox.checked){
                let checkBoxParent = wishListCheckBox.closest("tr");
                performanceTitle.push(checkBoxParent.querySelector("td:nth-child(3) a").textContent);
            }
        });

        fetch("http://localhost:8080/wishlist/delete",{
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({ performanceTitles: performanceTitle })
        }).then(response => {
            if (response.ok) {
                console.log("공연 삭제 요청이 성공적으로 전송되었습니다.");
                window.location.href = "/wishlist";
            } else {
                console.error("공연 삭제 요청이 실패하였습니다.");
                  // 실패 시 처리하는 코드 작성
            }
        })
        .catch(error => {
            console.error("네트워크 오류:", error);
        });
    });
}

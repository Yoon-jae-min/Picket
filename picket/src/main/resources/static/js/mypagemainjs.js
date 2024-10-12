function selectAll1(selectAll)  {
  const checkboxes
     = document.querySelectorAll('input[name="mypagemain_ticket1"]');

  checkboxes.forEach((checkbox) => {
    checkbox.checked = selectAll.checked
  })
};
function selectAll2(selectAll)  {
  const checkboxes
     = document.querySelectorAll('input[name="mypagemain_ticket2"]');

  checkboxes.forEach((checkbox) => {
    checkbox.checked = selectAll.checked
  })
};


//환불하기
const payCancelButton = document.querySelector("#payCancelBtn");
const payTicketTablebodys = document.querySelectorAll(".payTicketTablebody");
let payTicketRequestBody = [];


payCancelButton.addEventListener("click", function(){
    const currentUrl = window.location.href;
    const baseUrl = currentUrl.split('?')[0];
    let isEmpty = true;
    payTicketTablebodys.forEach(payTicketTablebody => {
        if(payTicketTablebody.querySelector(".payTicketCheckbox").checked){
            let payTicketRequestContent = {
                payDate: payTicketTablebody.querySelector("td:nth-of-type(2)").textContent,
                performDate: payTicketTablebody.querySelector("td:nth-of-type(4) span").textContent,
                performTitle: payTicketTablebody.querySelector("td:nth-of-type(3)").textContent
            };
            payTicketRequestBody.push(payTicketRequestContent);

            isEmpty = false;
        }
    });

    if(isEmpty){
        alert('환볼하실 티켓을 선택해주세요!!');
    }
    else{
        const result = confirm('정말 환불하시겠습니까?');

        if(result){
            fetch("http://localhost:8080/deletePayment", {
                method: "POST",
                headers: {"Content-Type" : "application/json"},
                body: JSON.stringify({ payTicketRequestBody: payTicketRequestBody })
            });

            alert('환불 완료');
            console.log('작업완료');
            window.location.href = baseUrl;

        }
        else {
            alert('환불을 취소합니다.');
        }
    }
});
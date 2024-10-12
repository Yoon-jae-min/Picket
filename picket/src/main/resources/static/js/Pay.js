//
//IMP.init("imp01723330");3
//
//function requestPay() {
//    IMP.request_pay({
//        pg: "html5_inicis",
//        pay_method: "card",
//        merchant_uid: `payment-${crypto.randomUUID()}`, // 주문 고유 번호
//        name: "노르웨이 회전 의자",
//        amount: 1,
//        buyer_email: "gildong@gmail.com",
//        buyer_name: "홍길동",
//        buyer_tel: "010-4242-4242",
//        buyer_addr: "서울특별시 강남구 신사동",
//        buyer_postcode: "01181",
//    }, function (response) {
//        console.log("확인");
//        const notified = fetch(`http://localhost:8080/payment/complete`, {
//            method: "POST",
//            headers: { "Content-Type": "application/json" },
//            body: JSON.stringify({
//                imp_uid: response.imp_uid,
//                merchant_uid: response.merchant_uid,
//            }),
//        });
//    });
//}








// 예매하기 버튼 클릭 시 모달 열기
var btnOpenedit = document.querySelector('#btnOpenedit');
var modal = document.getElementById('modal');
let calcResultBox = document.querySelector('.number:nth-of-type(4)');
let customerPointBox = document.querySelector('.number:nth-of-type(1)');
let mathPriceValueBox = document.querySelector('.number:nth-of-type(2)');
let priceValue = document.querySelector('.payment_tablebody td:nth-of-type(4)');
let performanceDate = document.querySelector('.payment_tablebody td:nth-of-type(2)');
let currentDate = document.querySelector('.payment_tablebody td:nth-of-type(1)');
let currentYearMonth;
let date;
let performanceFormReceive;

btnOpenedit.addEventListener('click', function() {
    currentYearMonth = document.getElementById('currentMonth');
    date = document.querySelector('.selected');

    fetch("http://localhost:8080/loginStateCheck")
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
        return response.json();
    })
    .then(data => {

        if(data){
            const performanceTitle = document.querySelector('.rn-big-title b').textContent;
            fetch("http://localhost:8080/performanceDate",{
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    title: title
                })
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(performanceForm => {
                let startDate;
                let endDate;
                let selectDate;
                performanceFormReceive = performanceForm;
                //날짜 추출
                if(!(performanceForm.dates === 'OPEN RUN')){
                    const dateRegex = /(\d{4}\.\d{2}\.\d{2})/g;
                    const dates = performanceForm.dates.match(dateRegex);
                    const startDateString = dates[0];
                    const endDateString = dates[1];

                    // 시작 날짜와 종료 날짜를 Date 객체로 변환
                    startDate = new Date(startDateString);
                    endDate = new Date(endDateString);
                }

                if(date != null){
                    selectDate = new Date(formatYearMonth(currentYearMonth.textContent) + date.textContent);
                    if((startDate <= selectDate && endDate >= selectDate) || performanceForm.dates === 'OPEN RUN'){
                        fetch("http://localhost:8080/customerPoint")
                        .then(response => {
                            return response.json();
                        })
                        .then(point => {
                            currentDate.textContent = getCurrentDate();
                            performanceDate.textContent = currentYearMonth.textContent + " " + date.textContent + "일";
                            priceValue.textContent = performanceForm.price;

                            console.log(point);
                            customerPointBox.textContent = point.toString();
                            mathPriceValueBox.textContent = performanceForm.price;
                            calcResultBox.textContent = (point - parseInt(performanceForm.price)).toString();
                            modal.style.display = 'block';
                        })


                    }
                    else{
                        alert('공연날짜가 아닙니다.');
                    }
                }
                else{
                    alert('날짜를 선택해주세요!!!');
                }
            })
        }
        else{
            alert('로그인 후 이용해주세요');
        }
    });
});

//모달 티켓 갯수 수정
let ticketCount = document.querySelector('#pay_count');

ticketCount.addEventListener('change', (event) => {
//    priceValue.textContent = (parseInt(performanceFormReceive.price) * parseInt(ticketCount.value)).toString();
    mathPriceValueBox.textContent = (parseInt(performanceFormReceive.price) * parseInt(ticketCount.value)).toString();
    calcResultBox.textContent = (parseInt(customerPointBox.textContent) - parseInt(mathPriceValueBox.textContent)).toString();
})

// 수정 모달 닫기
var btnClose = document.getElementById('btnClose');
btnClose.onclick = function() {
    modal.style.display = 'none';
};


//현재 날짜 출력
function getCurrentDate() {
    const currentDate = new Date();

    // 연도, 월, 일, 요일 정보 가져오기
    const year = currentDate.getFullYear(); // 연도(네 자리)
    const month = currentDate.getMonth() + 1; // 월 (0부터 시작하므로 +1 해줌)
    const day = currentDate.getDate(); // 일

    // 출력할 현재 날짜 문자열 생성
    const currentDateStr = `${year}년 ${month}월 ${day}일`;

    // 생성된 현재 날짜 문자열 반환
    return currentDateStr;
}

function formatYearMonth(yearMonthText) {
    // '년'과 '월'을 공백으로 대체하여 숫자 부분을 추출
    const numericText = yearMonthText.replace(/[^\d]/g, ''); // "20245"

    // 숫자 부분을 연도와 월로 분리
    const year = numericText.substring(0, 4); // "2024"
    const month = numericText.substring(4); // "5"

    // 연도와 월을 소수점 형태로 조합하여 반환
    return `${year}.${month}.`; // "2024.5"
}



//결제 버튼을 눌렀을때
const paymentButton = document.querySelector('.payment-btn');
const titleTextBox = document.querySelector('.rn-big-title b');

paymentButton.addEventListener("click", function(){
    let currentPage = window.location.href;

    fetch("http://localhost:8080/doPay",{
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            ticketCount: ticketCount.value,
            performDate: performanceDate.textContent,
            payDate: currentDate.textContent,
            customerPoint: calcResultBox.textContent,
            performanceTitle: titleTextBox.textContent
        })
    })
    .then(response => {
        if(response.ok){
            alert('결제가 완료되었습니다!!');
        }
        else{
            alert('포인트 잔액이 부족합니다!!');
        }
        window.location.href = currentPage;
    })
});
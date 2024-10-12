
/* 달력 */

const calendarDates = document.getElementById("calendarDates");
const currentMonthElement = document.getElementById("currentMonth");
const prevBtn = document.getElementById("prevBtn");
const nextBtn = document.getElementById("nextBtn");
const today = new Date();
let currentMonth = today.getMonth();
let currentYear = today.getFullYear();
let selectedDate = null; // 이전에 선택된 요소를 저장할 변수

function renderCalendar() {
    const firstDayOfMonth = new Date(currentYear, currentMonth, 1);
    const daysInMonth = new Date(currentYear, currentMonth + 1, 0).getDate();
    const startDayOfWeek = firstDayOfMonth.getDay();
    currentMonthElement.textContent = `${currentYear}년 ${currentMonth + 1}월`;

    calendarDates.innerHTML = "";

    if(currentYear > today.getFullYear()){
        for (let i = 0; i < startDayOfWeek; i++) {
            const emptyDate = document.createElement("div");
            emptyDate.classList.add("blockDate", "empty");
            calendarDates.appendChild(emptyDate);
        }

        for (let i = 1; i <= daysInMonth; i++) {
            const dateElement = document.createElement("div");
            dateElement.textContent = i;

            dateElement.classList.add("date");
            dateElement.addEventListener("click", function() {
                // 이전에 선택된 요소의 스타일 제거
                if (selectedDate) {
                    selectedDate.classList.remove("selected");
                }
                // 클릭된 요소에 스타일 추가
                this.classList.add("selected");
                // 클릭된 요소를 이전에 선택된 요소로 저장
                selectedDate = this;
            });

            calendarDates.appendChild(dateElement);
        }
    }

    else if((currentYear == today.getFullYear()) && (currentMonth >= today.getMonth())){
        for (let i = 0; i < startDayOfWeek; i++) {
            const emptyDate = document.createElement("div");
            emptyDate.classList.add("blockDate", "empty");
            calendarDates.appendChild(emptyDate);
        }

        for (let i = 1; i <= daysInMonth; i++) {
            const dateElement = document.createElement("div");
            dateElement.textContent = i;
            if((currentMonth == today.getMonth()) && (i <= today.getDate())){
                dateElement.classList.add("blockDate");
            }
            else{
                dateElement.classList.add("date");
                dateElement.addEventListener("click", function() {
                    // 이전에 선택된 요소의 스타일 제거
                    if (selectedDate) {
                        selectedDate.classList.remove("selected");
                    }
                    // 클릭된 요소에 스타일 추가
                    this.classList.add("selected");
                    // 클릭된 요소를 이전에 선택된 요소로 저장
                    selectedDate = this;
                });
            }

            calendarDates.appendChild(dateElement);
        }
    }
    else{
        for (let i = 0; i < startDayOfWeek; i++) {
            const emptyDate = document.createElement("div");
            emptyDate.classList.add("blockDate", "empty");
            calendarDates.appendChild(emptyDate);
        }

        for (let i = 1; i <= daysInMonth; i++) {
            const dateElement = document.createElement("div");
            dateElement.classList.add("blockDate");
            dateElement.textContent = i;
            calendarDates.appendChild(dateElement);
        }
    }
}

renderCalendar();

prevBtn.addEventListener("click", () => {
    currentMonth--;
    if (currentMonth < 0) {
        currentMonth = 11;
        currentYear--;
    }
    renderCalendar();
});

nextBtn.addEventListener("click", () => {
    currentMonth++;
    if (currentMonth > 11) {
        currentMonth = 0;
        currentYear++;
    }
    renderCalendar();
});

/* 달력 끝*/




/* 찜목록 하트 */

var images = ['../image/info/heart.png', '../image/info/heart_red.png']; // 이미지 파일 경로 배열
var currentIndex = 0; // 현재 이미지 인덱스
let title = document.querySelector(".rn-big-title b").textContent;

function changeImage() {
  fetch("http://localhost:8080/loginStateCheck")
  .then(response => {
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
      return response.json();
  })
  .then(data => {
    if(data){
      const imageUrl = document.querySelector('#image-container img').getAttribute('src');

      if(images[0] == imageUrl) currentIndex = 0;
      else if(images[1] == imageUrl) currentIndex = 1;

      currentIndex++; // 다음 이미지로 인덱스 증가
      if (currentIndex >= images.length) {
          currentIndex = 0; // 마지막 이미지까지 보여준 후 처음 이미지로 돌아가기
      }
      var imageElement = document.querySelector('#image-container img');
      imageElement.src = images[currentIndex]; // 이미지 변경

      let url = "";
      const path = window.location.pathname;

      if(currentIndex == 1) url = "http://localhost:8080/wishEnroll"
      else if(currentIndex == 0) url = "http://localhost:8080/wishDelete"

      fetch(url,{
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
          title: title,
          path: path
        })
      })
      .then((response) => console.log(response));
    }
    else{
      alert('로그인 후 이용해주세요');
    }
  });
}

/* 찜목록 하트 끝 */
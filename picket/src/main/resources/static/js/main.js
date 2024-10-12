const container = document.querySelector("#pic");
const pics = ['가로슬라이드.jpg','가로슬라이드2.jpg','가로슬라이드3.jpg'];
const arrows = document.querySelectorAll(".arrow");
let i = 0;  
const transitionDuration = 1000; // 슬라이드 전환 시간 (단위: 밀리초)
const slideDuration = 5000; // 각 슬라이드를 보여주는 시간 (단위: 밀리초)

// 초기 슬라이드 설정
container.style.backgroundImage = `url(../image/mainslide/큰슬라이드/${pics[i]})`;  

// 화살표에 대한 클릭 이벤트 핸들러 설정
arrows.forEach( arrow => {
  arrow.addEventListener("click", (e) => {
    clearInterval(autoSlideInterval); // 사용자가 화살표를 클릭할 때 자동 슬라이드를 멈춥니다.
    if(e.target.id === "left") {  
      i--;
      if (i < 0) {  
        i = pics.length - 1;  
      }      
    }
    else if (e.target.id === "right") { 
      i++;  
      if (i >= pics.length) {  
        i = 0;  
      }
    }
    // 슬라이드 변경
    container.style.backgroundImage = `url(../image/mainslide/큰슬라이드/${pics[i]})`;  
    setTimeout(() => {
      autoSlideInterval = setInterval(autoSlide, slideDuration); // 사용자 조작 후 일정 시간이 지나면 다시 자동 슬라이드를 시작합니다.
    }, transitionDuration);
  });
});

// 자동 슬라이드 넘김을 위한 함수
function autoSlide() {
  i++;
  if (i >= pics.length) {
    i = 0;
  }
  // 슬라이드 변경
  container.style.backgroundImage = `url(../image/mainslide/큰슬라이드/${pics[i]})`;  
}

// 일정 시간 간격으로 자동 슬라이드 넘김 설정 (일정한 간격으로 슬라이드 변경)
let autoSlideInterval = setInterval(autoSlide, slideDuration);



/*테스트*/

let test = document.querySelector('.test');
let video = document.querySelector('video');

window.addEventListener('scroll', function() {
  let divPosition = window.scrollY;

  let testHeight = test.clientHeight * 2;
  let videoHeight = (video.clientHeight /100) * 90;


  // 스크롤 위치에 따른 투명도 계산
  let opacity = divPosition / testHeight;
  let videoOpacity = divPosition / videoHeight;

  test.style.backgroundColor = `rgba(0, 0, 0, ${opacity})`;
  video.style.opacity = `${1-videoOpacity}`;
});
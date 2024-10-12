let test1 = document.getElementById("test1");
let test2 = document.getElementById("test2");
let test3 = document.getElementById("test3");
let test4 = document.getElementById("test4");
let test5 = document.getElementById("test5");

let testaa1 = document.getElementById("aa1");
let testaa2 = document.getElementById("aa2");
let testaa3 = document.getElementById("aa3");
let testaa4 = document.getElementById("aa4");
let testaa5 = document.getElementById("aa5");



let testFAQ1 = document.getElementById("testFAQ1");
let height1 = testFAQ1.clientHeight + "px";
let testFAQ2 = document.getElementById("testFAQ2");
let height2 = testFAQ2.clientHeight + "px";
let testFAQ3 = document.getElementById("testFAQ3");
let height3 = testFAQ3.clientHeight + "px";
let testFAQ4 = document.getElementById("testFAQ4");
let height4 = testFAQ4.clientHeight + "px";
let testFAQ5 = document.getElementById("testFAQ5");
let height5 = testFAQ5.clientHeight + "px";
let timer; // 타이머 식별자를 저장할 변수

/*111111111111111111111111111111111111111111111111111111111111*/
testaa1.style.opacity = "0";
test1.addEventListener("click", ()=>{
console.log("확인");
testaa1.style.transition = "none";
testaa2.style.transition = "none";
testaa3.style.transition = "none";
testaa4.style.transition = "none";
testaa5.style.transition = "none";
clearTimeout(timer);
timer = null; // timer 초기화
testaa3.style.opacity = "0";
testaa2.style.opacity = "0";
testaa4.style.opacity = "0";
testaa5.style.opacity = "0";
testaa2.style.height = "0px";
testaa3.style.height = "0px";
testaa4.style.height = "0px";
testaa5.style.height = "0px";


if(testaa1.style.opacity == "0"){
    testaa1.style.transition = "1s all";

    testaa1.style.height = height1;
    testaa1.style.opacity = "1";
    testaa2.style.height = "0px";
    testaa3.style.height = "0px";
    testaa4.style.height = "0px";
    testaa5.style.height = "0px";

    if(timer) clearTimeout(timer); // 타이머가 있다면 취소
    timer = setTimeout(() => {
        testaa1.style.height = "auto";
    }, 1000);
}
else if(testaa1.style.opacity == "1"){
    testaa1.style.transition = "none";
    clearTimeout(timer); // 타이머가 있다면 취소
    testaa1.style.opacity = "0";
    testaa1.style.height = "0px";
    timer = null; // timer 초기화
}
});


/*222222222222222222222222222222222222222222222222222222222222222222*/
testaa2.style.opacity = "0"
test2.addEventListener("click", ()=>{
testaa1.style.transition = "none";
testaa2.style.transition = "none";
testaa3.style.transition = "none";
testaa4.style.transition = "none";
testaa5.style.transition = "none";
clearTimeout(timer);
timer = null; // timer 초기화
testaa1.style.opacity = "0";
testaa3.style.opacity = "0";
testaa4.style.opacity = "0";
testaa5.style.opacity = "0";
testaa1.style.height = "0px";
testaa3.style.height = "0px";
testaa4.style.height = "0px";
testaa5.style.height = "0px";


if(testaa2.style.opacity == "0"){
    testaa2.style.transition = "1s all";
    testaa2.style.height = height2;
    testaa2.style.opacity = "1";
    testaa1.style.height = "0px";
    testaa3.style.height = "0px";
    testaa4.style.height = "0px";
    testaa5.style.height = "0px";

    if(timer) clearTimeout(timer); // 타이머가 있다면 취소
    timer = setTimeout(() => {
        testaa2.style.height = "auto";
    }, 1000);
}
else if(testaa2.style.opacity == "1"){
    testaa2.style.transition = "none";
    clearTimeout(timer); // 타이머가 있다면 취소
    testaa2.style.opacity = "0";
    testaa2.style.height = "0px";
    timer = null; // timer 초기화
}
});


/*3333333333333333333333333333333333333333333333333333333333*/
testaa3.style.opacity = "0"
testaa3.style.transition = "none";
test3.addEventListener("click", ()=>{
testaa1.style.transition = "none";
testaa2.style.transition = "none";
testaa3.style.transition = "none";
testaa4.style.transition = "none";
testaa5.style.transition = "none";
clearTimeout(timer);
timer = null; // timer 초기화
testaa1.style.opacity = "0";
testaa2.style.opacity = "0";
testaa4.style.opacity = "0";
testaa5.style.opacity = "0";
testaa1.style.height = "0px";
testaa2.style.height = "0px";
testaa4.style.height = "0px";
testaa5.style.height = "0px";


if(testaa3.style.opacity == "0"){
    testaa3.style.transition = "1s all";
    testaa3.style.height = height3;
    testaa3.style.opacity = "1";
    testaa2.style.height = "0px";
    testaa1.style.height = "0px";
    testaa4.style.height = "0px";
    testaa5.style.height = "0px";


    if(timer) clearTimeout(timer); // 타이머가 있다면 취소
    timer = setTimeout(() => {
        testaa3.style.height = "auto";
    }, 1000);
}
else if(testaa3.style.opacity == "1"){
    testaa3.style.transition = "none";
    clearTimeout(timer); // 타이머가 있다면 취소
    testaa3.style.opacity = "0";
    testaa3.style.height = "0px";
    timer = null; // timer 초기화
}
});


/*4444444444444444444444444444444444444444444444444444444444444444444*/
testaa4.style.opacity = "0";
test4.addEventListener("click", ()=>{
testaa1.style.transition = "none";
testaa2.style.transition = "none";
testaa3.style.transition = "none";
testaa4.style.transition = "none";
testaa5.style.transition = "none";
clearTimeout(timer);
timer = null; // timer 초기화
testaa3.style.opacity = "0";
testaa2.style.opacity = "0";
testaa1.style.opacity = "0";
testaa5.style.opacity = "0";
testaa2.style.height = "0px";
testaa3.style.height = "0px";
testaa1.style.height = "0px";
testaa5.style.height = "0px";


if(testaa4.style.opacity == "0"){
    testaa4.style.transition = "1s all";

    testaa4.style.height = height4;
    testaa4.style.opacity = "1";
    testaa2.style.height = "0px";
    testaa3.style.height = "0px";
    testaa1.style.height = "0px";
    testaa5.style.height = "0px";

    if(timer) clearTimeout(timer); // 타이머가 있다면 취소
    timer = setTimeout(() => {
        testaa4.style.height = "auto";
    }, 1000);
}
else if(testaa4.style.opacity == "1"){
    testaa4.style.transition = "none";
    clearTimeout(timer); // 타이머가 있다면 취소
    testaa4.style.opacity = "0";
    testaa4.style.height = "0px";
    timer = null; // timer 초기화
}
});

/*555555555555555555555555555555555555555555555555555555555555555555555*/
testaa5.style.opacity = "0";
test5.addEventListener("click", ()=>{
testaa1.style.transition = "none";
testaa2.style.transition = "none";
testaa3.style.transition = "none";
testaa4.style.transition = "none";
testaa5.style.transition = "none";
clearTimeout(timer);
timer = null; // timer 초기화
testaa3.style.opacity = "0";
testaa2.style.opacity = "0";
testaa4.style.opacity = "0";
testaa1.style.opacity = "0";
testaa2.style.height = "0px";
testaa3.style.height = "0px";
testaa4.style.height = "0px";
testaa1.style.height = "0px";



if(testaa5.style.opacity == "0"){
    testaa5.style.transition = "1s all";

    testaa5.style.height = height5;
    testaa5.style.opacity = "1";
    testaa2.style.height = "0px";
    testaa3.style.height = "0px";
    testaa4.style.height = "0px";
    testaa1.style.height = "0px";


    if(timer) clearTimeout(timer); // 타이머가 있다면 취소
    timer = setTimeout(() => {
        testaa5.style.height = "auto";
    }, 1000);
}
else if(testaa5.style.opacity == "1"){
    testaa5.style.transition = "none";
    clearTimeout(timer); // 타이머가 있다면 취소
    testaa5.style.opacity = "0";
    testaa5.style.height = "0px";
    timer = null; // timer 초기화
}
});

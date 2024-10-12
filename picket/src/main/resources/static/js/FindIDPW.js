const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
  container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
  container.classList.remove("right-panel-active");
});

/*const button1 =  document.getElementById("button1");
const button2 =  document.getElementById("button2");
button1.addEventListener('click', ()=>{
  alert("아이디는  입니다")
});
button2.addEventListener('click', ()=>{
  alert("비밀번호는  입니다")
});
*/
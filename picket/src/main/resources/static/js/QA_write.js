document.getElementById('QA_comment').addEventListener('keyup', function() {
  var text = this.value; // textarea의 현재 텍스트를 가져옵니다.
  var charCount = text.length; // 텍스트의 길이(문자 수)를 계산합니다.
  if(charCount == 0 || charCount == null){
    document.getElementById('comment_length').textContent = 0;
  }else {
  document.getElementById('comment_length').textContent = charCount; // 문자 수를 화면에 표시합니다.
  }
  if(charCount>1000){
    alert("최대 1000자까지만 입력할 수 있습니다.");
  }
});
document.getElementById('QA_title').addEventListener('keyup', function() {
  var title = this.value;
  var charCount2 = title.length
  if(charCount2>50){
    alert("제목은 최대 50자까지만 입력할 수 있습니다.")
  }
})
function goBack(){
  window.history.back();
}
document.getElementById('QA_cancel').addEventListener('click', goBack);
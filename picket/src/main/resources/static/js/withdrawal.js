function agreeCheck(frm){
    if (frm.checkButton.disabled==true)
     frm.checkButton.disabled=false
    else
     frm.checkButton.disabled=true
}

var checkButton = document.getElementById('checkButton');
var deleteForm = document.getElementById('delete_form');

checkButton.addEventListener("click", function(event) {
    if(confirm("정말로 탈퇴하시겠습니까? 탈퇴 시 포인트와 티켓은 모두 소멸하며, 이에 대한 복구, 환불은 불가능합니다.")){
        deleteForm.submit();
    } else{
        event.preventDefault();
    }
});
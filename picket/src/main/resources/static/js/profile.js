// 수정하기 버튼 클릭 시 모달 열기
var btnOpenedit = document.querySelector('#btnOpenedit');
var modal = document.getElementById('modal');
btnOpenedit.addEventListener('click', function() {
    modal.style.display = 'block';
});

// 수정 모달 닫기
var btnClose = document.getElementById('btnClose');
btnClose.onclick = function() {
    modal.style.display = 'none';
};

// 회원탈퇴 버튼 클릭 시 모달 열기
var btnOpenwith = document.querySelector('#btnOpenwith');
var modal2 = document.getElementById('modal2');
btnOpenwith.addEventListener('click', function() {
    modal2.style.display = 'block';
});

// 회원탈퇴 모달 닫기
var btnClose2 = document.getElementById('btnClose2');
btnClose2.onclick = function() {
    modal2.style.display = 'none';
};

const paymentModal = document.getElementById('paymentModal');
const paymentButton = document.querySelector('.booking button');

paymentButton.addEventListener('click', function(){
    paymentModal.style.display = "flex";
})
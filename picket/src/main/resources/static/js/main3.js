// 슬라이드 2번째 
let roller2 = document.querySelector('.rolling-list2')
roller2.id = 'roller3';

let clone2 = roller2.cloneNode(true)
clone2.id = 'roller4';
document.querySelector('.wrap2').appendChild(clone2);

document.querySelector('#roller3').style.left ='0px';
document.querySelector('#roller4').style.left = document.querySelectorAll('.rolling-list2 ul')

roller2.classList.add('original2');
clone2.classList.add('clone2');

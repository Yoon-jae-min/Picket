// 슬라이드 1번째

let roller = document.querySelector('.rolling-list')
roller.id = 'roller1';

let clone = roller.cloneNode(true)
clone.id = 'roller2';
document.querySelector('.wrap').appendChild(clone);

document.querySelector('#roller1').style.left = '0px';
document.querySelector('#roller2').style.left = document.querySelectorAll('.rolling-list ul')

roller.classList.add('original');
clone.classList.add('clone');




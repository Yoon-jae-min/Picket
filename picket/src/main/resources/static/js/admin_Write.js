//info 설명 첨부 파일 요소 추가
function addFile(){
    const subBox = document.querySelector('.subBox');
    const countElement = subBox.childElementCount/2 + 1;
    const newElementP = document.createElement('p');
    const newElementLabel = document.createElement('label');
    const newElementInput = document.createElement('input');
    const newElementRadioText = ['notice', 'discount', 'casting', 'schedule'];
    const newElementRadioBox = document.createElement('p');

    newElementLabel.textContent = countElement + ": ";
    newElementInput.id = "infoExplainImg_" + countElement;
    newElementInput.className = 'infoExplainImg';
    newElementInput.type = "file";

    newElementP.appendChild(newElementLabel);
    newElementP.appendChild(newElementInput);

    newElementRadioBox.className = "infoExplainSort_" + countElement;

    for(let sort of newElementRadioText){
        const newElementRadio = document.createElement('input');
        const newElementRadioLabel = document.createElement('label');
        newElementRadio.name = "sort_" + countElement;
        newElementRadio.type = "radio";
        newElementRadio.value = sort;
        newElementRadio.className = "sort_" + countElement;
        newElementRadioLabel.textContent = sort;
        newElementRadioBox.appendChild(newElementRadio);
        newElementRadioBox.appendChild(newElementRadioLabel);
    }

    newElementP.className = "infoExplainBox_" + countElement;

    subBox.appendChild(newElementP);
    subBox.appendChild(newElementRadioBox);
}

//info 설명 첨부 파일 요소 삭제
function deleteFile(){
    const subBox = document.querySelector('.subBox');
    const countElement = subBox.childElementCount;

    if(countElement > 0){
        subBox.removeChild(subBox.lastElementChild); // 라디오 버튼 그룹 삭제
        subBox.removeChild(subBox.lastElementChild); // 파일 업로드 요소 삭제
    }
}

//info 등록 버튼
function enroll(){
    const formData = new FormData();

    formData.append("category", document.querySelector('.infoCategoryBox').value);
    formData.append("detailCategory", document.querySelector('.infoDetailCategoryBox').value);
    formData.append("title", document.querySelector('.infoTitleBox').value)
    formData.append("infoMainImg", document.querySelector('#infoMain').files[0]);
    formData.append("carousel", document.querySelector('#carousel').files[0]);
    formData.append("date", document.querySelector('.infoPerformanceDateBox').value)
    formData.append("place", document.querySelector('.infoPerformancePlaceBox').value);
    formData.append("price", document.querySelector('.infoPerformancePriceBox').value);
    formData.append("runTime", document.querySelector('.infoPerformanceRuntimeBox').value);
    formData.append("ageGrade", document.querySelector('.infoPerformanceAgeGradeBox').value);

    const explainImgCount = document.querySelector('.subBox').childElementCount/2;
    for (let i = 1; i <= explainImgCount; i++){
        console.log(document.querySelector(`#infoExplainImg_${i}`).files[0]);
        formData.append("infoExplainImgArr", document.querySelector(`#infoExplainImg_${i}`).files[0]);
        formData.append("infoExplainSortArr", document.querySelector(`.sort_${i}:checked`).value);
    }

    fetch("http://localhost:8080/addInfo",{
        method: "POST",
        body: formData,
    }).then(response => {
        window.location.href = "/adminList";
    });
}


//버튼 이벤트
const addFileButton = document.querySelector('.addFileButton');
const deleteFileButton = document.querySelector('.deleteFileButton');
const enrollButton = document.querySelector('.enrollButton');

addFileButton.addEventListener('click', () => {
    addFile();
});

deleteFileButton.addEventListener('click', () => {
    deleteFile();
});

enrollButton.addEventListener('click', () => {
    enroll();
});




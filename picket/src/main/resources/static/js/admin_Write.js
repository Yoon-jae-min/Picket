//버튼
const addFileButton = document.querySelector('.addFileButton');
const deleteFileButton = document.querySelector('.deleteFileButton');
const enrollButton = document.querySelector('.enrollButton');

//태그
const checkOpenRun = document.querySelector('#openRunBox');
const dateBox = document.querySelector('.dateBox');

//이벤트
addFileButton.addEventListener('click', () => {
    addFile();
});

deleteFileButton.addEventListener('click', () => {
    deleteFile();
});

enrollButton.addEventListener('click', () => {
    enroll();
});

checkOpenRun.addEventListener('click', (event) => {
    dateBoxActive(event);
});

//기능
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
    let formatDate = (dateString) => dateString.replace(/-/g, '.');
    const formData = new FormData();
    const subBox = document.querySelector('.subBox');
    let errorCheck = 0;
    let date = document.querySelector('#endDate').value === '' ?
        formatDate(document.querySelector('#startDate').value) + " ~ " + formatDate(document.querySelector('#startDate').value) :
        formatDate(document.querySelector('#startDate').value) + " ~ " + formatDate(document.querySelector('#endDate').value);

    if(checkOpenRun.checked === true){
        date = 'Open Run';
    }

    formData.append("category", document.querySelector('.infoCategoryBox').value);
    formData.append("detailCategory", document.querySelector('.infoDetailCategoryBox').value);
    formData.append("title", document.querySelector('.infoTitleBox').value)
    formData.append("infoMainImg", document.querySelector('#infoMain').files[0] == null ? "" :
        document.querySelector('#infoMain').files[0]);
    formData.append("carousel", document.querySelector('#carousel').files[0] == null ? "" :
        document.querySelector('#carousel').files[0]);
    formData.append("date", document.querySelector('#startDate').value === '' ? '' : date);
    formData.append("place", document.querySelector('.infoPerformancePlaceBox').value);
    formData.append("price", document.querySelector('.infoPerformancePriceBox').value === '' ?
        0 : document.querySelector('.infoPerformancePriceBox').value);
    formData.append("runTime", document.querySelector('.infoPerformanceRuntimeBox').value === '' ?
        "-" : document.querySelector('.infoPerformanceRuntimeBox').value);
    formData.append("ageGrade", document.querySelector('.infoPerformanceAgeGradeBox').value === '' ?
        "-" : document.querySelector('.infoPerformanceAgeGradeBox').value);

    const explainImgCount = document.querySelector('.subBox').childElementCount/2;
    for (let i = 1; i <= explainImgCount; i++){
        if(!document.querySelector(`#infoExplainImg_${i}`).files[0]){
            alert("상세 이미지를 첨부해주세요");
            return 0;
        }
        if(!document.querySelector(`.sort_${i}:checked`)){
            alert("이미지 분류를 선택해주세요");
            return 0;
        }
        formData.append("infoExplainImgArr", document.querySelector(`#infoExplainImg_${i}`).files[0]);
        formData.append("infoExplainSortArr", document.querySelector(`.sort_${i}:checked`).value);
    }

    if(formData.get("category") === "null_value"){
        console.log(formData.get('infoMainImg'));
        alert("카테고리를 선택해주세요");
        errorCheck++;
    }else if(formData.get("title") === '') {
        alert("제목을 입력해주세요");
        errorCheck++;
    }else if(formData.get("infoMainImg") === ""){
        alert("메인 이미지를 첨부해주세요");
        errorCheck++;
    }else if(formData.get("carousel") === ""){
        alert("캐러샐 이미지를 첨부해주세요");
        errorCheck++;
    }else if(formData.get("date") === ''){
        alert("날짜를 선택해주세요");
        errorCheck++;
    }else if(formData.get("place") === ''){
        alert("장소를 입력해주세요");
        errorCheck++;
    }else if(document.querySelector('.subBox').children.length === 0){
        alert("상세 이미지를 첨부해주세요");
        errorCheck++;
    }

    if(errorCheck === 0){
        fetch("http://localhost:8080/addInfo",{
            method: "POST",
            body: formData,
        }).then(response => {
            window.location.href = "/adminList";
        }).catch(err => {
            console.log(err);
        });
    }
}

//dateBox 숨기기/활성화
function dateBoxActive(event){
    if(event.target.checked === true){
        dateBox.style.display = 'none';
    }else{
        dateBox.style.display = 'block';
    }
}




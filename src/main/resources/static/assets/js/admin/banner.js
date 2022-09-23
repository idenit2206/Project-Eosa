"use strict";

const bannerAddItem = () => {
    const bannerAddItem = document.querySelector(".banner-add-item");
    const bannerTableBody = document.querySelector(".bannerTableBody");
    let currentBannerRowsCount = document.querySelectorAll(".bannerRows").length;

    bannerAddItem.addEventListener("click", () => {
        console.log(`현재 배너개수: ${currentBannerRowsCount}`);

        if(currentBannerRowsCount < 5) {
        const bannersRows = document.createElement("tr");
        bannersRows.setAttribute("class", "bannerRows");

        const bannerPreviewCell = document.createElement("td");
        bannerPreviewCell.setAttribute("class", "bannerPreviewCell");

        const bannerPreview = document.createElement("img");
        bannerPreview.setAttribute("class", "bannerPreview");

        const bannerInputCell = document.createElement("td");
        bannerInputCell.setAttribute("class", "bannerInputCell");

        const inputFile = document.createElement("input");
        inputFile.setAttribute("type", "file");
        inputFile.setAttribute("class", "bannerFileInput")
        inputFile.setAttribute("accept", "image/jpeg, image/png");

        const spanEl = document.createElement("span");
        spanEl.setAttribute("class", "bannerFileInputRemove");
        spanEl.innerHTML = "x";

        const brEl = document.createElement("br");

        const inputText = document.createElement("input");
        inputText.setAttribute("type", "text");
        inputText.setAttribute("class", "bannerUrlInput");

        bannerPreviewCell.appendChild(bannerPreview);
        
        bannerInputCell.appendChild(inputFile);
        bannerInputCell.appendChild(spanEl);
        bannerInputCell.appendChild(brEl);
        bannerInputCell.appendChild(inputText);

        bannersRows.appendChild(bannerPreviewCell);
        bannersRows.appendChild(bannerInputCell);

        bannerTableBody.appendChild(bannersRows);
        currentBannerRowsCount++;
        }
        else {
            alert("배너의 개수는 5개를 초과할 수 없습니다.");
        }
    })
}

const bannerInputFile = () => {

}

const FileUpProcess = (file, tag, index) => {
    console.log(`FileUpProcess input parameter: file - ${file}, tag - ${tag}, index = ${index}`);
    const imgPreviewBodyDesktop = document.querySelectorAll(".imgPreviewBodyDesktop");
    // const imgPreviewBodyMobile = document.querySelectorAll(".imgPreviewBodyMobile");

    // let result = "";
    let fileReader = new FileReader();
    // console.log("OriginFile: ", file);

    fileReader.readAsDataURL(file);
}
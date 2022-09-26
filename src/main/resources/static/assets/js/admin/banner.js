"use strict";

let currentBannerRowsCount = document.querySelectorAll(".bannerRows").length;

const bannerAddItem = () => {

    const bannerAddItem = document.querySelector(".banner-add-item");
    const bannerTableBody = document.querySelector(".bannerTableBody");    

    bannerAddItem.addEventListener("click", () => {
        console.log(`현재 배너개수: ${currentBannerRowsCount + 1}`);

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
    const bannerTableBody = document.querySelector(".bannerTableBody");
    const bannerFileInput = document.querySelectorAll(".bannerFileInput");
    const bannerFileInputRemove = document.querySelectorAll(".bannerFileInputRemove");
    const bannerUrlInput = document.querySelectorAll(".bannerUrlInput");

    for(let i = 0; i < bannerFileInput.length; i++) {
        bannerFileInput[i].addEventListener("change", (e) => {
            FileUpProcess(e.target.files[0], 'banner', i);
        })
    }
}

const FileUpProcess = (file, tag, index) => {
    console.log(`FileUpProcess input parameter: file - ${file}, tag - ${tag}, index = ${index}`);
    const bannerPreview = document.querySelectorAll(".bannerPreview");
    const bannerFileInputRemove = document.querySelectorAll(".bannerFileInputRemove");
    let fileReader = new FileReader();
    fileReader.readAsDataURL(file);
    fileReader.addEventListener("load", (e) => {
        // console.log(e.target.result);
        bannerPreview[index].setAttribute("src", e.target.result);
        bannerFileInputRemove[index].style.visibility = "visible";
    })
}

const bannerInputFileRemove = () => {
    const bannerPreview = document.querySelectorAll(".bannerPreview");
    const bannerFileInputRemove = document.querySelectorAll(".bannerFileInputRemove");
    for(let i = 0; i < bannerPreview.length; i++) {
        bannerFileInputRemove[i].style.visibility = "hidden";
    }
}

const bannerUpdate = () => {
    const bannerUpdateBtn = document.querySelector(".banner-update-btn");

    const bannerRows = document.querySelectorAll(".bannerRows");

    const bannerInputCell = document.querySelectorAll(".bannerInputCell");


    const bannerTableBody = document.querySelector(".bannerTableBody");
    const bannerFileInput = document.querySelectorAll(".bannerFileInput");
    const bannerFileInputRemove = document.querySelectorAll(".bannerFileInputRemove");
    const bannerUrlInput = document.querySelectorAll(".bannerUrlInput");

    let formData = new FormData();
    let bannerFile = [];

    bannerUpdateBtn.addEventListener("click", () => {
        console.log(`bannerRows: count: ${currentBannerRowsCount}`);
        for(let i = 0; i < bannerInputCell.length; i++) {
            console.log(i);
            // console.log(`bannerFileInput[${i}]: ${bannerFileInput[i].files[0]}`);
            // formData.append("bannerFile", bannerFileInput[i].files[0]);
            // bannerFile.push(bannerFileInput[i].files[0]);
        }        
        // for (let value of formData.values()) {
        //     console.log(value);
        // }
        // console.log(bannerFile);
        // formData.append("bannerFile", bannerFile);
        // fetch(`/admin/manage/banner/update`, { method: "PUT", body: formData })
        // if(bannerFileInput.length == 5) {
        //     fetch(`/admin/manage/banner/update`, { method: "PUT", body: formData })
        // }
        // else {
        //     alert("배너는 5개의 파일을 업로드 해야합니다.");
        // }
        
    })

    
}
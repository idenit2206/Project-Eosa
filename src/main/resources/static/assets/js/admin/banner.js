"use strict";

let currentBannerRows = document.querySelectorAll(".bannerRows");
let currentBannerRowsCount = document.querySelectorAll(".bannerRows").length;

const bannerAddItem = () => {

    const bannerAddItem = document.querySelector(".banner-add-item");
    const bannerTableBody = document.querySelector(".bannerTableBody");    

    bannerAddItem.addEventListener("click", () => {
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
        inputFile.setAttribute("onchange", `FileUpProcess(this, ${currentBannerRowsCount})`);
        
        const spanEl = document.createElement("span");
        spanEl.setAttribute("class", "bannerFileInputRemove");
        spanEl.innerHTML = "x";

        const brEl = document.createElement("br");

        const inputTextLabel = document.createElement("label");
        inputTextLabel.innerHTML = "Url: ";

        const inputText = document.createElement("input");
        inputText.setAttribute("type", "text");
        inputText.setAttribute("class", "bannerHrefInput");

        const bannerCellRemove = document.createElement("td");
        bannerCellRemove.setAttribute("class", "bannerCellRemove");
        bannerCellRemove.setAttribute("onclick", "bannerRemoveItem()");
        bannerCellRemove.innerText="삭제";

        bannerPreviewCell.appendChild(bannerPreview);
        
        bannerInputCell.appendChild(inputFile);
        bannerInputCell.appendChild(spanEl);
        bannerInputCell.appendChild(brEl);
        bannerInputCell.appendChild(inputTextLabel);
        bannerInputCell.appendChild(inputText);
        
        bannersRows.appendChild(bannerPreviewCell);
        bannersRows.appendChild(bannerInputCell);
        bannersRows.appendChild(bannerCellRemove);

        bannerTableBody.appendChild(bannersRows);
        currentBannerRowsCount++;
        console.log(`현재 배너개수: ${currentBannerRowsCount}`);
        }
        else {
            alert("배너의 개수는 5개를 초과할 수 없습니다.");
        }
    })
}

const bannerRemoveItem = () => {
    const bannerCellRemove = document.querySelectorAll(".bannerCellRemove");
    alert(bannerCellRemove.length);
    // for(let i = 0; i < bannerCellRemove.length; i++) {
    //     bannerCellRemove[i].addEventListener("click", () => {
    //         currentBannerRows[i].remove();
    //         currentBannerRowsCount--;
    //         console.log(`현재 배너개수: ${currentBannerRowsCount}`);
    //     })
    // }
}

const bannerInputFile = () => {
    const bannerFileInput = document.querySelectorAll(".bannerFileInput");    
    for(let i = 0; i < bannerFileInput.length; i++) {
        bannerFileInput[i].addEventListener("change", (e) => {
            FileUpProcess(e.target.files[0], i);
        })
    }
}

//file, tag, index
const FileUpProcess = (file, index) => {
    console.log(`FileUpProcess input parameter: file - ${file}, index = ${index}`);
    console.log(file.files)
    const bannerPreview = document.querySelectorAll(".bannerPreview");
    const bannerFileInputRemove = document.querySelectorAll(".bannerFileInputRemove");   
    let fileReader = new FileReader();
    fileReader.readAsDataURL(file.files.length > 0 && file.files[0]);
    fileReader.onloadend = function (e) {
        if(bannerPreview.length > 0) {
            // console.log(bannerPreview[index].getAttribute("src"));
            bannerPreview[index].removeAttribute("src");              
            bannerPreview[index].setAttribute("src", e.target.result);
            bannerFileInputRemove[index].style.visibility = "visible";   
        }
          
    };

}

const bannerInputFileRemove = () => {
    const bannerPreview = document.querySelectorAll(".bannerPreview");
    const bannerFileInput = document.querySelectorAll(".bannerFileInput");
    const bannerHrefInput = document.querySelectorAll(".bannerHrefInput");
    const bannerFileInputRemove = document.querySelectorAll(".bannerFileInputRemove");
    for(let i = 0; i < bannerPreview.length; i++) {
        bannerFileInputRemove[i].addEventListener("click", () => {
            bannerPreview[i].setAttribute("src", "");
            bannerFileInput[i].value = "";
            bannerHrefInput[i].value = "";
            bannerFileInputRemove[i].style.visibility = "hidden";
        })
        
    }
}

const IsShowbannerInputFileRemove = () => {
    const bannerPreview = document.querySelectorAll(".bannerPreview");
    const bannerFileInputRemove = document.querySelectorAll(".bannerFileInputRemove");
    for(let i = 0; i < bannerPreview.length; i++) {
        if(bannerPreview[i].getAttribute("src") != null) {
            bannerFileInputRemove[i].style.visibility = "visible";
        }
    }
}

const bannerUpdate = () => {
    const bannerUpdateBtn = document.querySelector(".banner-update-btn");
    let formData = new FormData();
    let bannerFile = [];

    bannerUpdateBtn.addEventListener("click", () => {
        // const test = document.querySelectorAll('.bannerRows');
        // console.log(`bannerRows: count: ${test.length}`);
        
        let count = 0;
        const mainElement = document.querySelector("#main").getAttribute("data-category");
        const bannerPreviewCell = document.querySelectorAll(".bannerPreviewCell");
        const bannerPreview = document.querySelectorAll(".bannerPreview");
        const bannerFileInput = document.querySelectorAll(".bannerFileInput");
        const bannerHrefInput = document.querySelectorAll(".bannerHrefInput");
        let bannerItems = [];
        
        for(let i = 0; i < bannerFileInput.length; i++) {        
            formData.append("bannerFile", bannerFileInput[i].files[0]);
           
            bannerItems.push({ 
                idx: bannerPreviewCell[i].getAttribute("id"),
                bannerFileName: bannerFileInput[i].getAttribute("id"),
                bannerFileLink: bannerPreview[i].getAttribute("src"),
                bannerHref: bannerHrefInput[i].value
            });
            count++;
        }
        // for (let value of formData.keys()) {
        //     console.log(value);
        // }
        // console.log(bannerItems);
        formData.append("bannerItem", new Blob([JSON.stringify(bannerItems)]));
        if(count < 1) {
            alert("최소 한개의 배너가 필요합니다.");
            window.location.reload();
        }
        else {
            if(mainElement == 'mainbanner') {
                fetch(`/admin/manage/banner/update`, { method: "PUT", body: formData })
                    .then(response => response)
                    .then(data => {
                        if(data.status == 200) {
                            alert("변경을 완료했습니다.");
                            window.location.reload();
                        }
                    })
            }
            else {
                fetch(`/admin/manage/banner/detectiveBannerUpdate`, { method: "PUT", body: formData })
                    .then(response => response)
                    .then(data => {
                        if(data.status == 200) {
                            alert("변경을 완료했습니다.");
                            window.location.reload();
                        }
                    })
            }
        }
    })

    
}
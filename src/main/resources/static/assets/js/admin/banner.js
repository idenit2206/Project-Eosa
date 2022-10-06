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

        const prevDiv = document.createElement('div');
        prevDiv.classList.add('img-wrap');

        const bannerPreview = document.createElement("img");
        bannerPreview.setAttribute("class", "bannerPreview");

        //
        const bannerInputCell = document.createElement("td");
        bannerInputCell.setAttribute("class", "bannerInputCell");

        const bannerWrap = document.createElement('div');
        bannerWrap.classList.add('banner-wrap');

        const fileWrap = document.createElement('div');
        fileWrap.classList.add('banner-file-wrap');

        const fileLabel = document.createElement('label');
        fileLabel.setAttribute("for", "bannerFile" + currentBannerRowsCount);
        fileLabel.innerText = '업로드';

        const inputFile = document.createElement("input");
        inputFile.setAttribute("type", "file");
        inputFile.setAttribute("class", "bannerFileInput");
        inputFile.setAttribute("id", "bannerFile" + currentBannerRowsCount);
        inputFile.setAttribute("accept", "image/jpeg, image/png");
        inputFile.setAttribute("onchange", `FileUpProcess(this, ${currentBannerRowsCount})`);

        const fileTag = document.createElement('div');
        fileTag.classList.add('banner-file-tag');

        const fileName = document.createElement('a');
        fileName.classList.add('banner-file-name');

        const spanEl = document.createElement("span");
        spanEl.setAttribute("class", "bannerFileInputRemove");
        spanEl.setAttribute('onclick', 'bannerRemove(this, ' + currentBannerRowsCount + ')');

        const redirectWrap = document.createElement('div');
        redirectWrap.classList.add('redirect-wrap');

        const inputTextLabel = document.createElement("label");
        inputTextLabel.innerHTML = "바로가기";

        const inputWrap = document.createElement('div');
        inputWrap.classList.add('input-wrap');

        const inputText = document.createElement("input");
        inputText.setAttribute("type", "text");
        inputText.setAttribute("class", "bannerHrefInput");

        const bannerCellRemove = document.createElement("td");
        bannerCellRemove.setAttribute("id", currentBannerRowsCount+1);
        bannerCellRemove.setAttribute("class", "bannerCellRemove");
        bannerCellRemove.setAttribute("onclick", "bannerRemoveItem(this)");
        bannerCellRemove.innerText="삭제";

        bannerPreviewCell.appendChild(prevDiv);
        prevDiv.append(bannerPreview);

        bannerInputCell.appendChild(bannerWrap);

        bannerWrap.append(fileWrap);
        bannerWrap.append(redirectWrap);

        fileWrap.append(fileLabel);
        fileWrap.append(inputFile);
        fileWrap.append(fileTag);

        fileTag.append(fileName);
        fileTag.append(spanEl);

        redirectWrap.append(inputTextLabel);
        redirectWrap.append(inputWrap);

        inputWrap.append(inputText);

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

const bannerRemoveItem = (event) => {
    const bannerRows = event.parentElement;
    bannerRows.remove();
    currentBannerRowsCount--;
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
    const fileName = document.querySelectorAll(".banner-file-name");
    const fileInput = document.querySelectorAll(".bannerFileInput");
    fileName[index].innerText = fileInput[index].value.split('\\')[2];

}

function bannerRemove(e, i) {

    document.querySelectorAll(".bannerPreview")[i].src = '';
    document.querySelectorAll(".bannerFileInput")[i].value = '';
    document.querySelectorAll(".bannerHrefInput")[i].value = '';
    document.querySelectorAll('.banner-file-name')[i].Text = '';
    document.querySelectorAll('.banner-file-name')[i].innerText = '';
    e.style.visibility = 'hidden';

};
const bannerInputFileRemove = () => {

    const bannerFileName = document.querySelectorAll('.banner-file-name');
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
            bannerFileName[i].innerText = '';
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
        const mainElement = document.querySelector("#main").getAttribute("data-value");
        const bannerRows = document.querySelectorAll(".bannerRows");
        const bannerPreviewCell = document.querySelectorAll(".bannerPreviewCell");
        const bannerPreview = document.querySelectorAll(".bannerPreview");
        const bannerFileInput = document.querySelectorAll(".bannerFileInput");
        const bannerHrefInput = document.querySelectorAll(".bannerHrefInput");
        let bannerItems = [];

        for(let i = 0; i < bannerRows.length; i++) {
            // formData.append("bannerFile", bannerFileInput[i].files[0]);

            // formData.append("idx", bannerPreviewCell[i].getAttribute("id"));
            // formData.append("bannerFileName", bannerFileInput[i].getAttribute("id"));
            // // formData.append("bannerFileLink", bannerPreview[i].getAttribute("src"));
            // formData.append("bannerHref", bannerHrefInput[i].value);

            if(bannerPreview[i].getAttribute("src").startsWith("data:")) {
                formData.append("bannerFile", bannerFileInput[i].files[0]);
                bannerItems.push({
                    idx: bannerPreviewCell[i].getAttribute("id"),
                    bannerFileName: bannerFileInput[i].getAttribute("id"),
                    // bannerFileLink: bannerFileInput[i].files[0],
                    bannerFileLink: "",
                    bannerHref: bannerHrefInput[i].value
                });
            }
            else {
                bannerItems.push({
                    idx: bannerPreviewCell[i].getAttribute("id"),
                    bannerFileName: bannerFileInput[i].getAttribute("id"),
                    bannerFileLink: bannerPreview[i].getAttribute("src"),
                    bannerHref: bannerHrefInput[i].value
                });
            }
            count++;

        }
        // for (let value of formData.keys()) {
        //     console.log(value);
        // }
        console.log("bannerItem: ");
        console.log(bannerItems);
        formData.append("bannerItem", new Blob([JSON.stringify(bannerItems)]));

        const nameLabel = document.querySelectorAll('.banner-file-name');
        let valid = 0;
        for (let i = 0; i < nameLabel.length; i++) {
            if (nameLabel[i].textContent == '') {
                valid++;
            }
        }

        if(count < 1) {
            alert("최소 한개의 배너가 필요합니다.");
            window.location.reload();
        } else if (valid > 0) {
            alert('배너가 비어있습니다.');
        } else {
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

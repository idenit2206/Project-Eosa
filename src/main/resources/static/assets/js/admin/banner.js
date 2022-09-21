"use strict";

/**
 * 공지 상세
 */
function bannerFileHandler() {
    const imgPreviewBodyDesktop = document.querySelectorAll(".imgPreviewBodyDesktop");
    const imgPreviewBodyMobile = document.querySelectorAll(".imgPreviewBodyMobile");

    const inputFilesDesktop = document.querySelectorAll(".fileInputDesktop");
    const fileRemoveBtnDesktop = document.querySelectorAll(".fileRemoveBtnDesktop");
    const inputURLDesktop = document.querySelectorAll(".bannerUrlDesktop");

    const inputFilesMobile = document.querySelectorAll(".fileInputMobile");
    const fileRemoveBtnMobile = document.querySelectorAll(".fileRemoveBtnMobile");
    const inputURLMobile = document.querySelectorAll(".bannerUrlMobile");

    for(let i = 0; i < inputFilesDesktop.length; i++) {
        inputFilesDesktop[i].addEventListener("change", async (e) => {
            if(e.target.value != null) {
                // console.log(e.target);
                // console.log(e.target.nodeName);
                // console.log(e.target.value);
                fileRemoveBtnDesktop[i].style.visibility = "visible";
                inputURLDesktop[i].value = "";
                inputURLDesktop[i].readOnly = true;
                let imgSrc = await FileUpProcess(e.target.files[0], "Desktop", i);
                imgPreviewBodyDesktop[i].setAttribute("src", imgSrc);
            }
            else if(e.target.value == "") {
                // console.log("file neq whitespace");
                // console.log(e.target.value);
                fileRemoveBtnDesktop[i].style.visibility = "hidden";
                inputURLDesktop[i].value = "";
                inputURLDesktop[i].readOnly = false;
            }
        })
    }

    for(let i = 0; i < inputURLDesktop.length; i++) {
        if(inputURLDesktop[i].value != "") {
            inputFilesDesktop[i].disabled = true;
        }
    }

    for(let i = 0; i < inputFilesMobile.length; i++) {
        inputFilesMobile[i].addEventListener("change", async (e) => {
            if(e.target.value != null) {
                // console.log(e.target);
                // console.log(e.target.nodeName);
                // console.log(e.target.value);
                fileRemoveBtnMobile[i].style.visibility = "visible";
                inputURLMobile[i].value = "";
                inputURLMobile[i].readOnly = true;
                let imgSrc = await FileUpProcess(e.target.files[0], "Mobile", i);
                imgPreviewBodyMobile[i].setAttribute("src", imgSrc);
            }
            else if(e.target.value == "") {
                // console.log("file neq whitespace");
                // console.log(e.target.value);
                fileRemoveBtnMobile[i].style.visibility = "hidden";
                inputURLMobile[i].value = "";
                inputURLMobile[i].readOnly = false;
            }
        })
    }

    for(let i = 0; i < inputURLMobile.length; i++) {
        if(inputURLMobile[i].value != "") {
            inputFilesMobile[i].disabled = true;
        }
    }
}

const FileUpProcess = (file, tag, index) => {
    const imgPreviewBodyDesktop = document.querySelectorAll(".imgPreviewBodyDesktop");
    const imgPreviewBodyMobile = document.querySelectorAll(".imgPreviewBodyMobile");

    // let result = "";
    let fileReader = new FileReader();
    // console.log("OriginFile: ", file);

    fileReader.readAsDataURL(file);

    fileReader.onload = (e1) => {
        // console.log("onload: ", e1);
    }

    fileReader.onloadend = (e2) => {
        // console.log('lonloadend', e2.target.result);
        if(tag == "Desktop") {
            imgPreviewBodyDesktop[index].setAttribute("src", e2.target.result);
        }

        if(tag == "Mobile") {
            imgPreviewBodyMobile[index].setAttribute("src", e2.target.result);
        }
        // result = e2.target.result;

    }
    // return result;

}


function inputFileRemove() {
    const imgPreviewBodyDesktop = document.querySelectorAll(".imgPreviewBodyDesktop");
    const imgPreviewBodyMobile = document.querySelectorAll(".imgPreviewBodyMobile");

    const fileRemoveBtnDesktop = document.querySelectorAll(".fileRemoveBtnDesktop");
    const bannerUrlDesktop = document.querySelectorAll(".bannerUrlDesktop");
    for(let i = 0; i < fileRemoveBtnDesktop.length; i++) {
        fileRemoveBtnDesktop[i].addEventListener("click", (e) => {
            // console.log(e.target.previousSibling.previousSibling);
            e.target.previousSibling.previousSibling.value = "";
            e.target.style.visibility = "hidden";
            bannerUrlDesktop[i].readOnly = false;
            let deleteImgElement = document.querySelectorAll(".imgPreviewBodyDesktop");
            imgPreviewBodyDesktop[i].removeAttribute("src");
            // const elementP = document.createElement("p");
            // elementP.innerHTML = "배너"+(i+1)+"_Desktop";
            // imgPreviewDesktop[i].appendChild(elementP)
            // e.target.nextSibling.nextSibling.nextSibling.nextSibling.readOnly = false;
        })
    }

    const fileRemoveBtnMobile = document.querySelectorAll(".fileRemoveBtnMobile");
    const bannerUrlMobile = document.querySelectorAll(".bannerUrlMobile");
    for(let i = 0; i < fileRemoveBtnMobile.length; i++) {
        fileRemoveBtnMobile[i].addEventListener("click", (e) => {
            // console.log(e.target.previousSibling.previousSibling);
            e.target.previousSibling.previousSibling.value = "";
            e.target.style.visibility = "hidden";
            bannerUrlMobile[i].readOnly = false;
            let deleteImgElement = document.querySelectorAll(".imgPreviewBodyMobile");
            imgPreviewBodyMobile[i].removeAttribute("src");
            // const elementP = document.createElement("p");
            // elementP.innerHTML = "배너"+(i+1)+"_Mobile";
            // imgPreviewMobile[i].appendChild(elementP)
            // e.target.nextSibling.nextSibling.nextSibling.nextSibling.readOnly = false;
        })
    }
}

/**
* 파일 정보 DB에 저장
*/
const bannerUpdate = () => {
    const bannerUpdateBtn = document.querySelector(".banner-update-btn");
    const fileInputDesktop = document.querySelectorAll(".fileInputDesktop");
    const bannerUrlDesktop = document.querySelectorAll(".bannerUrlDesktop");

    const fileInputMobile = document.querySelectorAll(".fileInputMobile");
    const bannerUrlMobile = document.querySelectorAll(".bannerUrlMobile");

    bannerUpdateBtn.addEventListener("click", () => {
        let formData = new FormData();
        let bannerUrlDesktopList = new Array();
        let bannerUrlMobileList = new Array();

        for(let i = 0; i < fileInputDesktop.length; i++) {
            if(fileInputDesktop[i].files[0] != null) {
                formData.append("fileForDesktop", fileInputDesktop[i].files[0]);
            }
        }
        for(let i = 0; i < bannerUrlDesktop.length; i++) {
            if(bannerUrlDesktop[i].value != "") {
                bannerUrlDesktopList.push(bannerUrlDesktop[i].value);
            }
        }
        for(let i = 0; i < fileInputMobile.length; i++) {
            if(fileInputMobile[i].files[0] != null) {
                formData.append("fileForMobile", fileInputMobile[i].files[0]);
            }
        }
        for(let i = 0; i < bannerUrlMobile.length; i++) {
            if(bannerUrlMobile[i].value != "") {
                bannerUrlMobileList.push(bannerUrlMobile[i].value);
            }
        }

        formData.append("bannerUrlDesktop", bannerUrlDesktopList);
        formData.append("bannerUrlMobile", bannerUrlMobileList);

        fetch("/admin/manage/banner/update", { method: "PUT", body: formData})
            .then(response => response)
            .then(data => console.log(data))

    })
}
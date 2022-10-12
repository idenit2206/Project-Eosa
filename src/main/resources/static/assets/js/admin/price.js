"use strict";

const modifyPrice = () => {
    const modifyPriceBtn = document.querySelector(".price-modify-btn");
    modifyPriceBtn.addEventListener("click", () => {
        const priceIdx = document.querySelector("#priceIdx");
        const bankName = document.querySelector("#priceBankName");
        const bankNumber = document.querySelector("#priceBankNumber");

        let formData = new FormData();
        console.log(`priceIdx: ${priceIdx.getAttribute("class")}, bankName: ${bankName.value}, bankNumber: ${bankNumber.value}`);
        formData.append("priceIdx", priceIdx.getAttribute("class"));
        formData.append("bankName", bankName.value);
        formData.append("bankNumber", bankNumber.value);

        fetch(`/admin/manage/price/updatePrice`, {
            method: "POST",
            body: formData
        })
            .then(response => console.log(response));
    })
}

const regionLock = () => {
    const regionLockBtn = document.querySelector(".region-lock-btn");
    regionLockBtn.addEventListener("click", () => {
        const regionIdxValue = document.querySelectorAll(".regionIdxValue")
        let regionIdxValueList = [];
        for(let i = 0; i < regionIdxValue.length; i++) {
            if(regionIdxValue[i].checked == true) {
                // console.log(regionIdxValue[i].value);
                regionIdxValueList.push(regionIdxValue[i].value);
            }
        }
        let formData = new FormData();
        formData.append("regionIdxList", regionIdxValueList);
        fetch(`/admin/manage/price/lockRegion`, {
            "method": "PUT",
            "body": formData
        })
        .then(result => result)
        .then(data => {
            if(data.status == 200) {
                alert("잠금처리 되었습니다.")
                window.location.reload();
            }
            else {
                alert("다시 시도해주세요.")
                window.location.reload();
            } 
        })       
    });
}

const regionUnlock = () => {
    const regionUnlockBtn = document.querySelector(".region-unlock-btn");
    regionUnlockBtn.addEventListener("click", () => {
        const regionIdxValue = document.querySelectorAll(".regionIdxValue")
        let regionIdxValueList = [];
        for(let i = 0; i < regionIdxValue.length; i++) {
            if(regionIdxValue[i].checked == true) {
                // console.log(regionIdxValue[i].value);
                regionIdxValueList.push(regionIdxValue[i].value);
            }
        }
        let formData = new FormData();
        formData.append("regionIdxList", regionIdxValueList);
        fetch(`/admin/manage/price/unlockRegion`, {
            "method": "PUT",
            "body": formData
        })
        .then(result => result)
        .then(data => {
            if(data.status == 200) {
                alert("잠금해제 처리 되었습니다.")
                window.location.reload();
            }
            else {
                alert("다시 시도해주세요.")
                window.location.reload();
            } 
        })
    });
}

const removeRegionItem = () => {
    const regionRemoveBtn = document.querySelector(".region-remove-btn");
    regionRemoveBtn.addEventListener("click", () => {
        const regionIdxValue = document.querySelectorAll(".regionIdxValue")
        let regionIdxValueList = [];
        for(let i = 0; i < regionIdxValue.length; i++) {
            if(regionIdxValue[i].checked == true) {
                // console.log(regionIdxValue[i].value);
                regionIdxValueList.push(regionIdxValue[i].value);
            }
        }
        let formData = new FormData();
        formData.append("regionIdxList", regionIdxValueList);
        fetch(`/admin/manage/price/deleteRegion`, {
            "method": "DELETE",
            "body": formData
        })
        .then(result => result)
        .then(data => {
            if(data.status == 200) {
                alert("삭제 되었습니다.")
                window.location.reload();
            }
            else {
                alert("다시 시도해주세요.")
                window.location.reload();
            } 
        })
    });
}

const removeCategoryItem = () => {
    const categoryRemoveBtn = document.querySelector(".category-remove-btn");
    categoryRemoveBtn.addEventListener("click", () => {
        const categoryIdxValue = document.querySelectorAll(".categoryIdxValue")
        let categoryIdxValueList = [];
        for(let i = 0; i < categoryIdxValue.length; i++) {
            if(categoryIdxValue[i].checked == true) {
                // console.log(regionIdxValue[i].value);
                categoryIdxValueList.push(categoryIdxValue[i].value);
            }
        }
        let formData = new FormData();
        formData.append("categoryIdxList", categoryIdxValueList);
        fetch(`/admin/manage/price/deleteCategory`, {
            "method": "DELETE",
            "body": formData
        })
        .then(result => result)
        .then(data => {
            if(data.status == 200) {
                alert("삭제 되었습니다.")
                window.location.reload();
            }
            else {
                alert("다시 시도해주세요.")
                window.location.reload();
            } 
        })
    });
}

const addRegionItem = () => {
    const regionAddBtn = document.querySelector(".region-add-btn");
    const regionList = document.querySelector("#regionList");

    regionAddBtn.addEventListener("click", () => {
        const regionTr = document.createElement("tr");
        const regionTdIdx = document.createElement("td");
        const regionTdName = document.createElement("td");
        const regionTdPrice = document.createElement("td");

        const regionInputIdx = document.createElement("input");
        const regionInputName = document.createElement("input");
        const regionInputPrice = document.createElement("input");

        regionTr.setAttribute("class", "regionTR");
        regionTdIdx.setAttribute("class", "regionIdx");
        regionTdName.setAttribute("class", "regionName");
        regionTdPrice.setAttribute("class", "regionPrice");

        regionInputIdx.setAttribute("type", "checkbox");
        regionInputIdx.setAttribute("class", "regionIdxValue");
        regionInputName.setAttribute("type", "text");
        regionInputName.setAttribute("class", "regionNameValue");
        regionInputPrice.setAttribute("type", "text");
        regionInputPrice.setAttribute("class", "regionPriceValue");

        regionTdIdx.appendChild(regionInputIdx);
        regionTdName.appendChild(regionInputName);
        regionTdPrice.appendChild(regionInputPrice);

        regionTr.appendChild(regionTdIdx);
        regionTr.appendChild(regionTdName);
        regionTr.appendChild(regionTdPrice);

        regionList.appendChild(regionTr);
    })
}

const addCategoryItem = () => {
    const categoryAddBtn = document.querySelector(".category-add-btn");
    const categoryList = document.querySelector("#categoryList");

    categoryAddBtn.addEventListener("click", () => {
        const categoryTr = document.createElement("tr");
        const categoryTdIdx = document.createElement("td");
        const categoryTdName = document.createElement("td");
        const categoryTdPrice = document.createElement("td");
        const categoryTdIcon = document.createElement("td");

        const categoryInputIdx = document.createElement("input");
        const categoryInputName = document.createElement("input");
        const categoryInputPrice = document.createElement("input");
        const categoryIconPreview = document.createElement("img");
        const categoryIconInputFile = document.createElement("input");

        categoryTr.setAttribute("class", "categoryTR");
        categoryTdIdx.setAttribute("class", "categoryIdx");
        categoryTdName.setAttribute("class", "categoryName");
        categoryTdPrice.setAttribute("class", "categoryPrice");
        categoryTdIcon.setAttribute("class", "categoryIcon");

        categoryInputIdx.setAttribute("type", "checkbox");
        categoryInputIdx.setAttribute("class", "categoryIdxValue");
        categoryInputName.setAttribute("type", "text");
        categoryInputName.setAttribute("class", "categoryNameValue");
        categoryInputPrice.setAttribute("type", "text");
        categoryInputPrice.setAttribute("class", "categoryPriceValue");
        categoryIconPreview.setAttribute("class", "categoryIconPreview");       
        categoryIconInputFile.setAttribute("class", "categoryIconInputFile");
        categoryIconInputFile.setAttribute("onchange", "categoryIconInputFileChange(this)");
        categoryIconInputFile.setAttribute("type", "file");

        categoryTdIdx.appendChild(categoryInputIdx);
        categoryTdName.appendChild(categoryInputName);
        categoryTdPrice.appendChild(categoryInputPrice);
        categoryTdIcon.appendChild(categoryIconPreview);
        categoryTdIcon.appendChild(categoryIconInputFile);

        categoryTr.appendChild(categoryTdIdx);
        categoryTr.appendChild(categoryTdName);
        categoryTr.appendChild(categoryTdPrice);
        categoryTr.appendChild(categoryTdIcon);

        categoryList.appendChild(categoryTr);
    })
    
}

const modifyRegion = () => {
    const regionModifyBtn = document.querySelector(".region-modify-btn");

    regionModifyBtn.addEventListener("click", () => {
        const regionTR = document.querySelectorAll(".regionTR");
        const regionIdxValue = document.querySelectorAll(".regionIdxValue");
        const regionNameValue = document.querySelectorAll(".regionNameValue");
        const regionPriceValue = document.querySelectorAll(".regionPriceValue");
        const regionSelectableValue = document.querySelectorAll(".regionSelectable");

        let formData = new FormData();

        let regionList = new Array();
        for(let i = 0; i < regionTR.length; i++) {
            let array
            let region = new Object();
           
            region.regionIdx = i + 1;
            region.regionName = regionNameValue[i].value;
            region.regionPrice = regionPriceValue[i].value;
            if(regionSelectableValue[i].innerHTML == "선택 가능") {
                region.regionSelectable = 1;
            }
            else {
                region.regionSelectable = 0;
            }
           
            regionList.push(region);
        }        
        formData.append("region", new Blob([JSON.stringify(regionList)]));
       
        fetch(`/admin/manage/price/updateRegion`, {
            "method": "POST",
            "body": formData
        })
        .then(result => result)
        .then(data => {
            if(data.status == 200) {
                alert("변경 되었습니다.")
                window.location.reload();
            }
            else {
                alert("다시 시도해주세요.")
                window.location.reload();
            } 
        })
    })
}

const FileUpProcess2 = (file, index) => {
    let fileReader = new FileReader();
    fileReader.readAsDataURL(file);
    fileReader.onloadend = function (e) {        
        const categoryIconPreview = document.querySelectorAll(".categoryIconPreview");
        if(categoryIconPreview.length > 0) {
            categoryIconPreview[index].setAttribute("src", e.target.result);
        }
          
    };
}

// const categoryIconInputFileChange = () => {
//     const categoryIconInputFile = document.querySelectorAll(".categoryIconInputFile");
//     for(let i = 0; i < categoryIconInputFile.length; i++) {
//         categoryIconInputFile[i].addEventListener("change", (e) => {
//             FileUpProcess2(categoryIconInputFile[i].files[0], i);
//         })
//     }
// }
const categoryIconInputFileChange = (event) => {
    let id = event.getAttribute("id"); // int | null
    const categoryTR = document.querySelectorAll(".categoryTR");
    // console.log(event.files[0]);
    FileUpProcess2(event.files[0], id == null ? categoryTR.length-1 : id);   
}

const modifyCategory = () => {
    const categoryModifyBtn = document.querySelector(".category-modify-btn");

    categoryModifyBtn.addEventListener("click", () => {
        const categoryTR = document.querySelectorAll(".categoryTR");
        const categoryIdxValue = document.querySelectorAll(".categoryIdxValue");
        const categoryNameValue = document.querySelectorAll(".categoryNameValue");
        const categoryPriceValue = document.querySelectorAll(".categoryPriceValue");
        const categoryIconPreview = document.querySelectorAll(".categoryIconPreview");
        const categoryIcon = document.querySelectorAll(".categoryIconInputFile");

        let formData = new FormData();

        let categoryList = new Array();
        for(let i = 0; i < categoryTR.length; i++) {
            let category = {
                categoryIdx: "",
                categoryName: "",
                categoryPrice: "",
                categoryIcon: "",
                categoryIconName: ""
            };
            category.categoryIdx = i + 1;
            category.categoryName = categoryNameValue[i].value;
            category.categoryPrice = categoryPriceValue[i].value;
            // console.log(`regionName: ${region.regionName}`);
            if(categoryIcon[i].files[0] == null) {
                console.log(categoryIcon[i].files[0]);
                category.categoryIcon = categoryIconPreview[i].getAttribute("src") == null ? "" : categoryIconPreview[i].getAttribute("src");
                category.categoryIconName = "";
            }
            else {
                console.log(categoryIcon[i].files[0]);
                // formData.append("categoryIcon", categoryIcon[i].files[0]);
                formData.append("categoryIcon", categoryIcon[i].files[0]);
            }            

            categoryList.push(category);
        }
        formData.append("category", new Blob([JSON.stringify(categoryList)]));
        // for (let key of formData.keys()) {
        //     console.log(key);
        // }
        // for (let value of formData.values()) {
        //     console.log(value);
        // }

        fetch(`/admin/manage/price/updateCategory`, {
            "method": "POST",
            "body": formData,
        })
        .then(result => result)
        .then(data => {
            if(data.status == 200) {
                alert("변경 되었습니다.")
                window.location.reload();
            }
            else {
                alert("다시 시도해주세요.")
                window.location.reload();
            } 
        })
    })
}
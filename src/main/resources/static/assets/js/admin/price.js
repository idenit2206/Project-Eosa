"use strict";

const modifyPrice = () => {
    const modifyPriceBtn = document.querySelector(".price-modify-btn");
    modifyPriceBtn.addEventListener("click", () => {
        const priceIdx = document.querySelector("#priceIdx");
        const bankName = document.querySelector("#priceBankName");
        const bankNumber = document.querySelector("#priceBankNumber");

        let formData = new FormData();
        let PriceDTO = {
            priceIdx: priceIdx,
            bankName: bankName,
            bankNumber: bankNumber

        }
        formData.append("priceIdx", priceIdx);
        formData.append("bankName", bankName);
        formData.append("bankNumber", bankNumber);

        fetch(`/admin/manage/price/updatePrice`, {
            method: "POST",
            body: formData
        })
            .then(response => console.log(response));
    })
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

        const categoryInputIdx = document.createElement("input");
        const categoryInputName = document.createElement("input");
        const categoryInputPrice = document.createElement("input");

        categoryTr.setAttribute("class", "categoryTR");
        categoryTdIdx.setAttribute("class", "categoryIdx");
        categoryTdName.setAttribute("class", "categoryName");
        categoryTdPrice.setAttribute("class", "categoryPrice");

        categoryInputIdx.setAttribute("type", "checkbox");
        categoryInputIdx.setAttribute("class", "categoryIdxValue");
        categoryInputName.setAttribute("type", "text");
        categoryInputName.setAttribute("class", "categoryNameValue");
        categoryInputPrice.setAttribute("type", "text");
        categoryInputPrice.setAttribute("class", "categoryPriceValue");

        categoryTdIdx.appendChild(categoryInputIdx);
        categoryTdName.appendChild(categoryInputName);
        categoryTdPrice.appendChild(categoryInputPrice);

        categoryTr.appendChild(categoryTdIdx);
        categoryTr.appendChild(categoryTdName);
        categoryTr.appendChild(categoryTdPrice);

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

        let formData = new FormData();

        let regionList = new Array();
        for(let i = 0; i < regionTR.length; i++) {
            let array
            let region = new Object();
           
            region.regionIdx = i + 1;
            region.regionName = regionNameValue[i].value;
            region.regionPrice = regionPriceValue[i].value;            
            
            // formData.append("region", JSON.stringify(region));
            // formData.append("region", region);
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

const modifyCategory = () => {
    const categoryModifyBtn = document.querySelector(".category-modify-btn");

    categoryModifyBtn.addEventListener("click", () => {
        const categoryTR = document.querySelectorAll(".categoryTR");
        const categoryIdxValue = document.querySelectorAll(".categoryIdxValue");
        const categoryNameValue = document.querySelectorAll(".categoryNameValue");
        const categoryPriceValue = document.querySelectorAll(".categoryPriceValue");

        let formData = new FormData();

        let categoryList = new Array();
        for(let i = 0; i < categoryTR.length; i++) {
            let category = {
                categoryIdx: "",
                categoryName: "",
                categoryPrice: ""
            };
            category.categoryIdx = i + 1;
            category.categoryName = categoryNameValue[i].value;
            category.categoryPrice = categoryPriceValue[i].value;
            // console.log(`regionName: ${region.regionName}`);
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
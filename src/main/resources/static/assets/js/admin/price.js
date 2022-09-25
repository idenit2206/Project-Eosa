"use strict";

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
            // let region = {
            //     regionIdx: "",
            //     regionName: "",
            //     regionPrice: ""
            // };
            region.regionIdx = regionIdxValue[i].value;
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
            category.categoryIdx = categoryIdxValue[i].value;
            category.categoryName = categoryNameValue[i].value;
            category.categoryPrice = categoryPriceValue[i].value;
            // console.log(`regionName: ${region.regionName}`);
            categoryList.push(JSON.stringify(category));
        }
        formData.append("category", categoryList);
        // for (let key of formData.keys()) {
        //     console.log(key);
        //   }
        for (let value of formData.values()) {
            console.log(value);
        }

        fetch(`/admin/manage/price/updateCategory`, {
            "method": "POST",
            "body": formData,
        })
    })
}
import {
    CompanysCategory
} from "/assets/js/admin/CompanysCategoryData.js"

const COMPANYS_CATEGORY_CHECKBOX = document.querySelector("#companysCategoryCheckbox");

CompanysCategory.forEach(element => {
    const CATEGORY_VALUE_LABEL = document.createElement("label");
    CATEGORY_VALUE_LABEL.setAttribute("for", element);
    CATEGORY_VALUE_LABEL.textContent = element;
    const CATEGORY_VALUE_CHECKBOX = document.createElement("input");
    CATEGORY_VALUE_CHECKBOX.type = "checkbox";
    CATEGORY_VALUE_CHECKBOX.setAttribute("id", element);
    CATEGORY_VALUE_CHECKBOX.name = "companysCategory";
    CATEGORY_VALUE_CHECKBOX.value = element;

    COMPANYS_CATEGORY_CHECKBOX.appendChild(CATEGORY_VALUE_LABEL);
    COMPANYS_CATEGORY_CHECKBOX.appendChild(CATEGORY_VALUE_CHECKBOX);
})
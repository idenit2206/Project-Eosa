import {
    USERS_REGION1,
} from "/assets/js/AddressData.js";

const COMPANYS_ACTIVE_REGION_CHECKBOX = document.querySelector("#companysActiveRegionCheckbox");

USERS_REGION1.forEach(item => {
    const ACTIVE_REGION_LABEL = document.createElement("label");
    ACTIVE_REGION_LABEL.setAttribute("for", item);
    ACTIVE_REGION_LABEL.textContent = item;
    const ACTIVE_REGION_CHECKBOX = document.createElement("input");
    ACTIVE_REGION_CHECKBOX.type="checkbox";
    ACTIVE_REGION_CHECKBOX.name="companysActiveRegion";
    ACTIVE_REGION_CHECKBOX.id=item;
    ACTIVE_REGION_CHECKBOX.value=item

    COMPANYS_ACTIVE_REGION_CHECKBOX.appendChild(ACTIVE_REGION_LABEL);
    COMPANYS_ACTIVE_REGION_CHECKBOX.appendChild(ACTIVE_REGION_CHECKBOX);
})
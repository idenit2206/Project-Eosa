import { 
    USERS_REGION1,
    SEOUL, INCHEON, GYEONGGIDO, BUSAN, GWANGJU, DAEGU, DAEJEON,
    SAEJONG, GANGWONDO, CHUNGBUK, CHUNGNAM, JEONBUK, JEONNAM,
    GYEONGSANGBUK, GYEONGSANGNAM, ULSAN, 
    JEJU
} from "/assets/js/AddressData.js";

const _USERS_REGION1 = document.querySelector("#usersRegion1");
const _USERS_REGION2 = document.querySelector("#usersRegion2");

const _USERS_NOTICE = document.querySelector("#usersNotice");

USERS_REGION1.forEach(element => {
    const _USERS_REGION1_VALUE_ELE = document.createElement("option");
    _USERS_REGION1_VALUE_ELE.innerHTML = element;
    _USERS_REGION1_VALUE_ELE.value = element;
    _USERS_REGION1.appendChild(_USERS_REGION1_VALUE_ELE);
});

_USERS_REGION1.addEventListener("change", (e) => {
    // console.log(_USERS_REGION1.value);
    let ur1 = e.target.value;
    // console.log(ur1);
    if(ur1.search("서울") != -1) {
        _USERS_REGION2.innerHTML = "";
        SEOUL.sort();
        SEOUL.forEach(element => {
            const _USERS_REGION2_VALUE_ELE = document.createElement("option");
            _USERS_REGION2_VALUE_ELE.innerHTML = element;
            _USERS_REGION2_VALUE_ELE.value = element;
            _USERS_REGION2.appendChild(_USERS_REGION2_VALUE_ELE);
        });
    }

    if(ur1.search("인천") != -1) {
        _USERS_REGION2.innerHTML = "";
        INCHEON.sort();
        INCHEON.forEach(element => {
            const _USERS_REGION2_VALUE_ELE = document.createElement("option");
            _USERS_REGION2_VALUE_ELE.innerHTML = element;
            _USERS_REGION2_VALUE_ELE.value = element;
            _USERS_REGION2.appendChild(_USERS_REGION2_VALUE_ELE);
        });
    }

    if(ur1.search("경기") != -1) {
        _USERS_REGION2.innerHTML = "";
        GYEONGGIDO.sort();
        GYEONGGIDO.forEach(element => {
            const _USERS_REGION2_VALUE_ELE = document.createElement("option");
            _USERS_REGION2_VALUE_ELE.innerHTML = element;
            _USERS_REGION2_VALUE_ELE.value = element;
            _USERS_REGION2.appendChild(_USERS_REGION2_VALUE_ELE);
        });
    }

    if(ur1.search("부산") != -1) {
        _USERS_REGION2.innerHTML = "";
        BUSAN.sort();
        BUSAN.forEach(element => {
            const _USERS_REGION2_VALUE_ELE = document.createElement("option");
            _USERS_REGION2_VALUE_ELE.innerHTML = element;
            _USERS_REGION2_VALUE_ELE.value = element;
            _USERS_REGION2.appendChild(_USERS_REGION2_VALUE_ELE);
        });       
    }

    if(ur1.search("대구") != -1) {
        _USERS_REGION2.innerHTML = "";
        DAEGU.sort();
        DAEGU.forEach(element => {
            const _USERS_REGION2_VALUE_ELE = document.createElement("option");
            _USERS_REGION2_VALUE_ELE.innerHTML = element;
            _USERS_REGION2_VALUE_ELE.value = element;
            _USERS_REGION2.appendChild(_USERS_REGION2_VALUE_ELE);
        });
    }    

    if(ur1.search("광주") != -1) {
        _USERS_REGION2.innerHTML = "";
        GWANGJU.sort();
        GWANGJU.forEach(element => {
            const _USERS_REGION2_VALUE_ELE = document.createElement("option");
            _USERS_REGION2_VALUE_ELE.innerHTML = element;
            _USERS_REGION2_VALUE_ELE.value = element;
            _USERS_REGION2.appendChild(_USERS_REGION2_VALUE_ELE);
        });
    }

    if(ur1.search("대전") != -1) {
        _USERS_REGION2.innerHTML = "";
        DAEJEON.sort();
        DAEJEON.forEach(element => {
            const _USERS_REGION2_VALUE_ELE = document.createElement("option");
            _USERS_REGION2_VALUE_ELE.innerHTML = element;
            _USERS_REGION2_VALUE_ELE.value = element;
            _USERS_REGION2.appendChild(_USERS_REGION2_VALUE_ELE);
        });
    }

    if(ur1.search("세종") != -1) {
        _USERS_REGION2.innerHTML = "";
        SAEJONG.sort();
        SAEJONG.forEach(element => {
            const _USERS_REGION2_VALUE_ELE = document.createElement("option");
            _USERS_REGION2_VALUE_ELE.innerHTML = element;
            _USERS_REGION2_VALUE_ELE.value = element;
            _USERS_REGION2.appendChild(_USERS_REGION2_VALUE_ELE);
        });
    }

    if(ur1.search("강원") != -1) {
        _USERS_REGION2.innerHTML = "";
        GANGWONDO.sort();
        GANGWONDO.forEach(element => {
            const _USERS_REGION2_VALUE_ELE = document.createElement("option");
            _USERS_REGION2_VALUE_ELE.innerHTML = element;
            _USERS_REGION2_VALUE_ELE.value = element;
            _USERS_REGION2.appendChild(_USERS_REGION2_VALUE_ELE);
        });
    }

    if(ur1.search("충청북도") != -1) {
        _USERS_REGION2.innerHTML = "";
        CHUNGBUK.sort();
        CHUNGBUK.forEach(element => {
            const _USERS_REGION2_VALUE_ELE = document.createElement("option");
            _USERS_REGION2_VALUE_ELE.innerHTML = element;
            _USERS_REGION2_VALUE_ELE.value = element;
            _USERS_REGION2.appendChild(_USERS_REGION2_VALUE_ELE);
        });
    }

    if(ur1.search("충청남도") != -1) {
        _USERS_REGION2.innerHTML = "";
        CHUNGNAM.sort();
        CHUNGNAM.forEach(element => {
            const _USERS_REGION2_VALUE_ELE = document.createElement("option");
            _USERS_REGION2_VALUE_ELE.innerHTML = element;
            _USERS_REGION2_VALUE_ELE.value = element;
            _USERS_REGION2.appendChild(_USERS_REGION2_VALUE_ELE);
        });
    }

    if(ur1.search("전라북도") != -1) {
        _USERS_REGION2.innerHTML = "";
        JEONBUK.sort();
        JEONBUK.forEach(element => {
            const _USERS_REGION2_VALUE_ELE = document.createElement("option");
            _USERS_REGION2_VALUE_ELE.innerHTML = element;
            _USERS_REGION2_VALUE_ELE.value = element;
            _USERS_REGION2.appendChild(_USERS_REGION2_VALUE_ELE);
        });
    }

    if(ur1.search("전라남도") != -1) {
        _USERS_REGION2.innerHTML = "";
        JEONNAM.sort();
        JEONNAM.forEach(element => {
            const _USERS_REGION2_VALUE_ELE = document.createElement("option");
            _USERS_REGION2_VALUE_ELE.innerHTML = element;
            _USERS_REGION2_VALUE_ELE.value = element;
            _USERS_REGION2.appendChild(_USERS_REGION2_VALUE_ELE);
        });
    }

    if(ur1.search("경상북도") != -1) {
        _USERS_REGION2.innerHTML = "";
        GYEONGSANGBUK.sort();
        GYEONGSANGBUK.forEach(element => {
            const _USERS_REGION2_VALUE_ELE = document.createElement("option");
            _USERS_REGION2_VALUE_ELE.innerHTML = element;
            _USERS_REGION2_VALUE_ELE.value = element;
            _USERS_REGION2.appendChild(_USERS_REGION2_VALUE_ELE);
        });
    }

    if(ur1.search("경상남도") != -1) {
        _USERS_REGION2.innerHTML = "";
        GYEONGSANGNAM.sort();
        GYEONGSANGNAM.forEach(element => {
            const _USERS_REGION2_VALUE_ELE = document.createElement("option");
            _USERS_REGION2_VALUE_ELE.innerHTML = element;
            _USERS_REGION2_VALUE_ELE.value = element;
            _USERS_REGION2.appendChild(_USERS_REGION2_VALUE_ELE);
        });
    }

    if(ur1.search("울산") != -1) {
        _USERS_REGION2.innerHTML = "";
        ULSAN.sort();
        ULSAN.forEach(element => {
            const _USERS_REGION2_VALUE_ELE = document.createElement("option");
            _USERS_REGION2_VALUE_ELE.innerHTML = element;
            _USERS_REGION2_VALUE_ELE.value = element;
            _USERS_REGION2.appendChild(_USERS_REGION2_VALUE_ELE);
        });
    }

    if(ur1.search("제주") != -1) {
        _USERS_REGION2.innerHTML = "";
        JEJU.forEach(element => {
            const _USERS_REGION2_VALUE_ELE = document.createElement("option");
            _USERS_REGION2_VALUE_ELE.innerHTML = element;
            _USERS_REGION2_VALUE_ELE.value = element;
            _USERS_REGION2.appendChild(_USERS_REGION2_VALUE_ELE);
        });
    }

});

window.onload = () => {
    // console.log(_USERS_NOTICE.value);
    if(_USERS_NOTICE.value == 1) {
        _USERS_NOTICE.checked = true;
    }
}

_USERS_NOTICE.addEventListener("change", (e) => {
    if(e.target.checked == true) {
        e.value = 1;
        // console.log(e.value);
    }
    else {
        e.value = 0;
        // console.log(e.value);
    }
})
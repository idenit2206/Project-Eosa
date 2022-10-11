const removeListItem02 = (url) => {
    const removeBtn = document.querySelector(".btn-delete");
    removeBtn.addEventListener("click", () => {
        const listCheck = document.querySelectorAll(".listCheck")
        let listCheckValueList = [];
        for(let i = 0; i < listCheck.length; i++) {
            if(listCheck[i].checked == true) {
                // console.log(listCheck[i].value);
               listCheckValueList.push(listCheck[i].value);
            }
        }
        if(listCheck.length != 0) {
            let formData = new FormData();
            formData.append("listCheckValueList",listCheckValueList);
            fetch(`${url}`, {
                "method": "PUT",
                "body": formData
            })
            .then(result => result)
            .then(data =>
                {
                    if(data.status == 200) {
                        alert("삭제 되었습니다.")
                        window.location.reload();
                    }
                    else {
                        alert("다시 시도해주세요.")
                        window.location.reload();
                    } 
                }
            )
        }
        else {
            alert("선택된 항목이 없습니다.");
        }
    });
}
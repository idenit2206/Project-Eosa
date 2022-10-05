const removeListItem = () => {
    const removeBtn = document.querySelector(".btn-delete");
    removeBtn.addEventListener("click", () => {
        const companysHiddencamCheck = document.querySelectorAll(".companysHiddencamCheck")
        let companysHiddencamIdxValueList = [];
        for(let i = 0; i < companysHiddencamCheck.length; i++) {
            if(companysHiddencamCheck[i].checked == true) {
                // console.log(companysHiddencamCheck[i].value);
                companysHiddencamIdxValueList.push(companysHiddencamCheck[i].value);
            }
        }
        if(companysHiddencamCheck.length != 0) {
            let formData = new FormData();
            formData.append("companysHiddencamIdxList", companysHiddencamIdxValueList);
            fetch(`/admin/manage/companysHiddencam/delete`, {
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
        }
        else {
            alert("선택된 항목이 없습니다.");
        }
    });
}

const updateHiddencamCompany = () => {
    const companysHiddencamIdx = document.querySelector("#companysHiddencamIdx");
    const companysHiddencamCheckStatus = document.querySelector("#companysHiddencamCheckStatus");

    let formData = new FormData();

    formData.append("companysHiddencamIdx", companysHiddencamIdx.innerHTML);
    formData.append("companysHiddencamCheckStatus", companysHiddencamCheckStatus.value);

    fetch("/admin/manage/companysHiddencam/update", {
        "method": "PUT",
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

    
}
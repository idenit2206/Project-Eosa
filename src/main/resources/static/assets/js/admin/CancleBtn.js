const CANCLE_BTN = document.querySelector("#cancleBtn")

CANCLE_BTN.addEventListener("click", () => {
    let quest = confirm("이전 화면으로 돌아가시겠습니까?");
    if(quest) {
        alert("이전 화면으로 돌아갑니다.")
        history.back();
    }
});
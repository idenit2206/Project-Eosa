"use strict";

/**
 * 공지 상세
 */
function noticeDetailsModal() {
    const modal = document.querySelector(".modal-notice");
    const modalId = modal.querySelector("modal-id");

    const btn = document.querySelectorAll(".notice-btn");    
    const noticeIdx = document.querySelectorAll(".noticeIdx");
    const noticeTitle = document.querySelectorAll(".noticeTitle");
    const noticeContent = document.querySelectorAll(".noticeContent");
    const noticeAuthor = document.querySelectorAll(".noticeAuthor");
    const noticePostDate = document.querySelectorAll(".noticePostDate");

    const modalIdx = document.querySelector("#idx");
    const modalTitle = document.querySelector("#title");
    const modalAuthor = document.querySelector("#author");
    const modalContent = document.querySelector("#content");
    const modalPostDate = document.querySelector("#postDate");
    const modalDelBtn = document.querySelector(".btn-del");

    for(let i = 0; i < btn.length; i++) {
        btn[i].addEventListener("click", () => {
            modalIdx.value = noticeIdx[i].innerText;
            modalTitle.value = noticeTitle[i].innerText;
            modalContent.innerHTML = noticeContent[i].innerText;
            modalAuthor.value = noticeAuthor[i].innerText;
            modalPostDate.value = noticePostDate[i].innerText;
            modalDelBtn.addEventListener("click", () => {
                if(window.confirm("정말로 이 공지사항을 삭제 하시겠습니까?")) {
                    alert("이 공지사항을 삭제합니다.");
                    fetch("/admin/manage/notice/deleteByNoticeIdx?noticeIdx="+noticeIdx[i].innerHTML, {method: "DELETE"})
                        .then(response => response)
                        .then(data => { window.location.href = "list" });
                }                
            })
        })
    }

    new Modal({modal: ".modal-notice", name: "notice"});
}

/**
 * Modal 공지사항 상세보기 수정하기
*/
const updateNoticeByNoticeIdx = () => {
    const modalNoticeIdx = document.querySelector("#idx");
    const modalNoticeTitle = document.querySelector("#title");
    const modalNoticeContent = document.querySelector("#content");
    const modlaModifyBtn = document.querySelector(".btn-prime");

    modlaModifyBtn.addEventListener("click", () => {
        let formData = new FormData();
        formData.append("idx", modalNoticeIdx.value);
        formData.append("title", modalNoticeTitle.value);
        formData.append("content", modalNoticeContent.value);
        fetch("/admin/manage/notice/updateByNoticeIdx", {method: "PUT", body: formData})
            .then(response => response)
            .then(data => {
                window.location.href = "list";
            })
    })
}
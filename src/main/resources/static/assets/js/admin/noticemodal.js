// "use strict";

// /**
//  * 공지 상세 Modal에서 보기
//  */
// function noticeDetailsModal() {
//     const modal = document.querySelector(".modal-notice");
//     const modalId = modal.querySelector("modal-id");

//     const btn = document.querySelectorAll(".notice-btn");
//     const noticeIdx = document.querySelectorAll(".noticeIdx");
//     const noticeTitle = document.querySelectorAll(".noticeTitle");
//     const noticeContent = document.querySelectorAll(".noticeContent");
//     const noticeAuthor = document.querySelectorAll(".noticeAuthor");
//     const noticePostDate = document.querySelectorAll(".noticePostDate");

//     const modalIdx = document.querySelector("#idx");
//     const modalTitle = document.querySelector("#title");
//     const modalAuthor = document.querySelector("#author");
//     const modalContent0 = document.querySelector("#editor");
//     const modalContent = document.querySelector(".ql-editor");
//     const modalPostDate = document.querySelector("#postDate");
//     const modalDelBtn = document.querySelector(".btn-del");

//     for(let i = 0; i < btn.length; i++) {
//         btn[i].addEventListener("click", () => {
//             modalIdx.innerHTML = noticeIdx[i].innerText;
//             modalTitle.value = noticeTitle[i].innerText;         
//             modalAuthor.innerHTML = noticeAuthor[i].innerText;
//             // modalContent0.innerHTML = noticeContent[i].value;
//             // modalContent.nextElementSibling = noticeContent[i].value;
//             modalContent.innerText = noticeContent[i].value;
//             modalDelBtn.addEventListener("click", () => {
//                 alert("이 공지사항을 삭제합니다.");
//                     fetch("/admin/manage/notice/deleteByNoticeIdx?noticeIdx="+noticeIdx[i].innerHTML, {method: "DELETE"})
//                         .then(response => response)
//                         .then(data => { window.location.href = "list" });
//                 // if(window.confirm("정말로 이 공지사항을 삭제 하시겠습니까?")) {
//                 //     alert("이 공지사항을 삭제합니다.");
//                 //     fetch("/admin/manage/notice/deleteByNoticeIdx?noticeIdx="+noticeIdx[i].innerHTML, {method: "DELETE"})
//                 //         .then(response => response)
//                 //         .then(data => { window.location.href = "list" });
//                 // }
//             })
//         })
//     }

//     new Modal({modal: ".modal-notice", name: "notice"});
// }

/**
 * Modal 공지사항 상세보기에서 수정하기
*/
const updateNoticeByNoticeIdx = (editor) => {
    const modalNoticeIdx = document.querySelector("#idx");
    const modalNoticeTitle = document.querySelector("#title");
    // const modalNoticeContent = document.querySelector("#content");
    const modlaModifyBtn = document.querySelector(".btn-prime");

    modlaModifyBtn.addEventListener("click", () => {
        let formData = new FormData();
        formData.append("idx", modalNoticeIdx.value);
        formData.append("title", modalNoticeTitle.value);
        formData.append('content', editor.getHTML());
        fetch("/admin/manage/notice/updateByNoticeIdx", {method: "PUT", body: formData})
            .then(response => response)
            .then(data => {
                window.location.href = "list";
            })
    })
}

// 공지사항 상세 페이지에서 삭제하기
const deleteNoticeByIdx = () => {
    const modalDelBtn = document.querySelector(".btn-del")
    const noticeIdx = document.querySelector("#idx").value; 
    modalDelBtn.addEventListener("click", () => {
        alert("이 공지사항을 삭제합니다.");
            fetch("/admin/manage/notice/deleteByNoticeIdx?noticeIdx="+noticeIdx, {method: "DELETE"})
                .then(response => response)
                .then(data => { window.location.href = "list" });
        // if(window.confirm("정말로 이 공지사항을 삭제 하시겠습니까?")) {
        //     alert("이 공지사항을 삭제합니다.");
        //     fetch("/admin/manage/notice/deleteByNoticeIdx?noticeIdx="+noticeIdx[i].innerHTML, {method: "DELETE"})
        //         .then(response => response)
        //         .then(data => { window.location.href = "list" });
        // }
    })
}


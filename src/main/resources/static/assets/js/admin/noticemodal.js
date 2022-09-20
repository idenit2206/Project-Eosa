/**
 * 공지 상세
 */
function noticeDetailsModal() {
    const btn = document.querySelector(".notice-btn");
    const modal = document.querySelector(".modal-notice");
    const modalId = modal.querySelector("modal-id");
    new Modal({modal: ".modal-notice", name: "notice"});
}

"use strict";

/**
 * 리뷰 상세
 */
function reviewDetailsModal() {
    const btn = document.querySelector('.review-btn');
    const modal = document.querySelector('.modal-review');
    const modalId = modal.querySelector('.modal-id');
    new Modal({modal: '.modal-review', name: 'review'});
};

/**
 * 공지 상세
*/
function noticeDetailsModal() {
    const btn = document.querySelector(".notice-btn");
    const modal = document.querySelector(".modal-notice");
    const modalId = modal.querySelector("modal-id");
    new Modal({modal: ".modal-notice", name: "notice"});
}

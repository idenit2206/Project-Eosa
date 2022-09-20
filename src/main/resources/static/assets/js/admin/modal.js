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

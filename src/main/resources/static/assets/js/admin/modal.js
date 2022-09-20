"use strict";

/**
 * 리뷰 상세
 */
function reviewDetailsModal() {

    const modal = document.querySelector('.modal-review');
    const mId = modal.querySelector('.modal-id');
    const mTitle = modal.querySelector('.modal__title');
    const mAverage = modal.querySelector('.average-score');
    const mResultScore = modal.querySelector('.m-result');
    const mCommunication = modal.querySelector('.m-communication');
    const mProcess = modal.querySelector('.m-process');
    const mSpecial = modal.querySelector('.m-special');
    const mContents = modal.querySelector('.details-contents');
    const mWriter = modal.querySelector('.writer');
    const mDate = modal.querySelector('.date');
    const mBtn = modal.querySelector('.redirect-company');

    const btn = document.querySelectorAll('.review-btn');
    const reviewIdx = document.querySelectorAll('.reviewIdx');
    const companyIdx = document.querySelectorAll('.companyIdx');
    const resultScore = document.querySelectorAll('.resultScore');
    const communicationScore = document.querySelectorAll('.communicationScore');
    const processScore = document.querySelectorAll('.processScore');
    const specialityScore = document.querySelectorAll('.specialityScore');
    const companysName = document.querySelectorAll('.companysName');
    const usersAccount = document.querySelectorAll('.usersAccount');
    const reviewDetail = document.querySelectorAll('.reviewDetail');
    const average = document.querySelectorAll('.average');
    const reviewDate = document.querySelectorAll('.reviewDate');

    for (let i = 0; i < btn.length; i++) {
        btn[i].addEventListener('click', e => {
            mId.value = reviewIdx[i].value;
            mTitle.innerHTML = companysName[i].textContent;
            mAverage.innerHTML = average[i].textContent;
            mResultScore.src = '/assets/images/ico/ico-star' + resultScore[i].value + '.svg';
            mCommunication.src = '/assets/images/ico/ico-star' + communicationScore[i].value + '.svg';
            mProcess.src = '/assets/images/ico/ico-star' + processScore[i].value + '.svg';
            mSpecial.src = '/assets/images/ico/ico-star' + specialityScore[i].value + '.svg';
            mContents.innerHTML = reviewDetail[i].textContent;
            mWriter.innerHTML = usersAccount[i].textContent;
            mDate.innerHTML = reviewDate[i].textContent;
            mBtn.addEventListener('click', e => {
                location.href = '/admin/manage/company/details?companysIdx=' + companyIdx[i].value;
            });
        });
    };

    new Modal({modal: '.modal-review', name: 'review'});
};

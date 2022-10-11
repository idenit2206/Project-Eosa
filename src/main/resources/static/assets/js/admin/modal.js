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

/**
 * 신고 상세
 */
function reportDetailsModal() {

    const modal = document.querySelector('.modal-report');
    const mId = modal.querySelector('.modal-id');
    const mUser = modal.querySelector('.r-user');
    const mCompany = modal.querySelector('.r-name');
    const mState = modal.querySelector('.r-state');
    const mDate = modal.querySelector('.r-date');
    const mDetails = modal.querySelector('.r-details');
    const mBtn = modal.querySelector('.redirect-company');

    const btn = document.querySelectorAll('.report-btn');
    const id = document.querySelectorAll('.reportIdx');
    const cId = document.querySelectorAll('.companyIdx');
    const cName = document.querySelectorAll('.companysName');
    const user = document.querySelectorAll('.usersAccount');
    const details = document.querySelectorAll('.reportDetail');
    const state = document.querySelectorAll('.reportState');
    const checkDate = document.querySelectorAll('.checkDate');
    const reportDate = document.querySelectorAll('.reportDate');

    for (let i = 0; i < btn.length; i++) {
        btn[i].addEventListener('click', e => {
            mId.value = id[i].value;
            mUser.innerHTML = user[i].textContent;
            mCompany.innerHTML = cName[i].textContent;            

            const reportCheckStateFalse = document.querySelector("#reportCheckStateFalse");
            const reportCheckStateTrue = document.querySelector("#reportCheckStateTrue");

            // // 2022.10.05 PARK MINJAE 추가작성
            if (state[i].textContent == 0) {
                // mState.innerHTML = '미처리';
                reportCheckStateFalse.setAttribute("selected", true);
            } else {
                // mState.innerHTML = checkDate[i].textContent;
                reportCheckStateTrue.setAttribute("selected", true);
            }

            while (mDetails.hasChildNodes()) {
                mDetails.removeChild(mDetails.firstChild);
            }

            const detail = details[i].textContent.split('/');
            for (let j = 0; j < detail.length; j++) {
                const span = document.createElement('span');
                span.innerHTML = detail[j];

                mDetails.append(span);
            }

            mDate.innerHTML = reportDate[i].textContent;
            mDetails.value = details[i].textContent;

            mBtn.addEventListener('click', e => {
                location.href = '/admin/manage/company/details?companysIdx=' + cId[i].value;
            });
        });
    };

    new Modal({modal: '.modal-report', name: 'report'});
};
/**
 * 신고 상세에서 처리상태 변경
 */
const onChangeReportProcess = () => {
    const modalId = document.querySelector(".modal-id");
    const reportCheckState = document.querySelector("#reportCheckState");
    reportCheckState.addEventListener("change", () => {
        // alert(reportCheckState.value);
        let formData = new FormData();
        formData.append("idx", modalId.value);
        formData.append("reportCheckState", reportCheckState.value);
        fetch("/admin/manage/report/update", {
            "method": "PUT",
            "body": formData
        })
        .then(response => response.json())       
    });
};
/**
 * 의뢰 상세
 */
function requestDetailsModel() {

    const modal = document.querySelector('.modal-request');
    const mId = modal.querySelector('.modal-id');
    const mUsersIdx = modal.querySelector('.r-usersIdx');
    const mCompanysIdx = modal.querySelector('.r-companysIdx');
    const mDate = modal.querySelector('.r-date');
    const mClient = modal.querySelector('.r-client');
    const mCompany = modal.querySelector('.r-company');
    const mRegion = modal.querySelector('#region01');
    const mCategory = modal.querySelectorAll('input[name=r-category]');
    const mState = modal.querySelector('.r-state');

    const id = document.querySelectorAll('.requestFormIdx');
    const usersIdx = document.querySelectorAll('.usersIdx');
    const companysIdx = document.querySelectorAll('.companysIdx');
    const category = document.querySelectorAll('.requestCategory');
    const company = document.querySelectorAll('.companysName');
    const client = document.querySelectorAll('.usersAccount');
    const region = document.querySelectorAll('.region01');
    const state = document.querySelectorAll('.requestState');
    const date = document.querySelectorAll('.requestDate');
    const btn = document.querySelectorAll('.request-btn');

    for (let i = 0; i < btn.length; i++) {
        btn[i].addEventListener('click', e => {
            mId.value = id[i].value;
            mUsersIdx.innerHTML = usersIdx[i].value;
            mCompanysIdx.innerHTML = companysIdx[i].value;
            mDate.innerHTML = date[i].textContent;
            mClient.innerHTML = client[i].textContent;
            mCompany.innerHTML = company[i].textContent;

            for (let j = 0; j < mRegion.options.length; j++) {
                if (mRegion.options[j].value == region[i].textContent) {
                    mRegion.options[j].selected = true;
                }
            }
            for (let j = 0; j < mState.options.length; j++) {
                if (mState.options[j].value == state[i].textContent) {
                    mState.options[j].selected = true;
                }
            }

            mCategory.forEach(c => {
                c.checked = false;
            })

            const cate = category[i].value.split('|');
            for (let j = 0; j < cate.length; j++) {
                for (let k = 0; k < mCategory.length; k++) {

                    if (mCategory[k].value == cate[j]) {
                        mCategory[k].checked = true;
                    }
                }
            }
        });
    };

    new Modal({modal: '.modal-request', name: 'request'});
};

"use strict";

/**
 * 공용 fetch
 */
function fetchApi(url, method, formData, result, link) {
    fetch(url, {
        method: method,
        body: formData,
    })
        .then(res => res.json())
        .then(data => {
            if (data >= 1) {
                alert(result);
                if (link) location.href = link;
                else location.reload();
            } else {
                alert('다시 시도해 주세요.');
                location.reload();
            }
        })
        .catch(err => {
            console.log(err);
            alert('다시 시도해 주세요.');
            location.reload();
        });
};

/**
 * 필수 값
 */
function alertFocus(msg, el) {
    alert(msg);
    el.focus();
};

/**
 * 아이디 중복체크
 */
function accountCheck() {
    const account = document.querySelector('#userId');
    const wrap = account.parentNode;
    const formData = new FormData();

    formData.set('usersAccount', account.value);

    fetch('/admin/manage/account/check', {
        method: 'post',
        body: formData,
    })
        .then(res => res.json())
        .then(data => {
            if (data == 1) {
                account.value = '';
                account.focus();

                if (wrap.querySelector('.warning') == null) {
                    const warn = document.createElement('i');
                    warn.classList.add('warning');
                    warn.innerText = '현재 사용 중인 아이디입니다.';
                    wrap.append(warn);
                }
            } else {
                if (wrap.querySelector('.warning') != null) {
                    wrap.removeChild(wrap.lastChild);
                }
            }
        })
        .catch(err => {
            console.log(err);
        })
};

/**
 * 연락처 중복체크
 */
function phoneCheck() {
    const tel = document.querySelector('.user-tel');
    const wrap = tel.parentNode;
    const formData = new FormData();

    formData.set('usersPhone', tel.value);

    fetch('/admin/manage/phone/check', {
        method: 'post',
        body: formData,
    })
        .then(res => res.json())
        .then(data => {
            if (data == 1) {
                tel.value = '';
                tel.focus();

                if (wrap.querySelector('.warning') == null) {
                    const warn = document.createElement('i');
                    warn.classList.add('warning');
                    warn.innerText = '현재 사용 중인 연락처입니다.';
                    wrap.append(warn);
                }
            } else {
                if (wrap.querySelector('.warning') != null) {
                    wrap.removeChild(wrap.lastChild);
                }
            }
        })
        .catch(err => {
            console.log(err);
        })
};

/**
 * 관리자 등록
 */
function insertAdmin() {

    const id = document.querySelector('#userId');
    const pwd = document.querySelector('#userPwd01');
    const pwdCheck = document.querySelector('#userPwd02');
    const name = document.querySelector('.user-name');
    const tel = document.querySelector('.user-tel');
    const email = document.querySelector('.user-email');

    if (id.value == '') {
        alertFocus('아이디를 입력해 주세요.', id);
    } else if (pwd.value == '') {
        alertFocus('비밀번호를 입력해 주세요.', pwd);
    } else if (pwdCheck.value == '') {
        alertFocus('비밀번호를 확인해 주세요.', pwdCheck);
    } else if (name.value == '') {
        alertFocus('이름을 입력해 주세요.', name);
    } else if (tel.value == '') {
        alertFocus('연락처를 입력해 주세요.', tel);
    } else if (email.value == '') {
        alertFocus('이메일을 입력해 주세요.', email);
    } else {
        const formData = new FormData();

        formData.set('usersAccount', id.value);
        formData.set('usersPass', pwd.value);
        formData.set('usersName', name.value);
        formData.set('usersPhone', tel.value);
        formData.set('usersEmail', email.value);

        fetchApi('/admin/manage/insert', 'post', formData, '관리자가 등록되었습니다.', '/admin/manage/list');
    };

};

/**
 * 비밀번호 초기화
 */
function resetPwd() {

    const msg = confirm('비밀번호를 초기화하시겠습니까?');
    if (msg) {
        const formData = new FormData();
        formData.set('usersIdx', document.querySelector('.usersIdx').value);
        formData.set('usersAccount', document.querySelector('.user-account').value);

        fetchApi('/admin/manage/reset/password', 'post', formData, '비밀번호가 초기화되었습니다.');
    }

};

/**
 * 관리자 수정
 */
function updateAdmin() {

    const name = document.querySelector('.user-name');
    const tel = document.querySelector('.user-tel');
    const email = document.querySelector('.user-email');

    if (name.value == '') {
        alertFocus('이름을 입력해 주세요.', name);
    } else if (tel.value == '') {
        alertFocus('연락처를 입력해 주세요.', tel);
    } else if (email.value == '') {
        alertFocus('이메일을 입력해 주세요.', email);
    } else {
        const formData = new FormData();

        formData.set('usersIdx', document.querySelector('.usersIdx').value);
        formData.set('usersName', name.value);
        formData.set('usersPhone', tel.value);
        formData.set('usersEmail', email.value);

        fetchApi('/admin/manage/update', 'post', formData, '수정되었습니다.');
    };

};

/**
 * 회원 수정
 */
function updateUser() {

    const idx = document.querySelector('.usersIdx');
    const name = document.querySelector('.user-name');
    const nick = document.querySelector('.user-nick');
    const tel = document.querySelector('.user-tel');
    const email = document.querySelector('.user-email');
    const age = document.querySelector('.user-age');
    const region01 = document.querySelector('#region01');
    const region02 = document.querySelector('#region02');
    const gender = document.querySelector('input[name=gender]:checked');

    if (name.value == '') {
        alertFocus('이름을 입력해 주세요.');
    } else if (nick.value == '') {
        alertFocus('별명을 입력해 주세요.');
    } else if (tel.value == '') {
        alertFocus('연락처를 입력해 주세요.');
    } else if (email.value == '') {
        alertFocus('이메일을 입력해 주세요.');
    } else {
        const formData = new FormData();

        formData.set('usersIdx', idx.value);
        formData.set('usersName', name.value);
        formData.set('usersNick', nick.value);
        formData.set('usersPhone', tel.value);
        formData.set('usersEmail', email.value);
        formData.set('usersAge', age.value);
        formData.set('usersRegion1', region01.value);
        if (region01.value == '서울') {
            formData.set('usersRegion2', region02.value);
        }
        formData.set('usersGender', gender.value);

        fetchApi('/admin/manage/user/update', 'post', formData, '수정되었습니다.');
    }

};

/**
 * 회원 탈퇴
 */
function terminateUser() {

    const formData = new FormData();

    const msg = confirm('회원을 탈퇴합니다.');
    if (msg) {
        formData.set('usersIdx', document.querySelector('.usersIdx').value);
        formData.set('terminateReason', document.querySelector('.terminate-reason').value);

        fetchApi('/admin/manage/user/terminate/insert', 'post', formData, '회원이 탈퇴되었습니다.');
    }

};

/**
 * 회원 탈퇴 취소
 */
function cancelTerminate() {

    const formData = new FormData();

    const msg = confirm('회원 탈퇴를 취소하시겠습니까?');
    if (msg) {
        formData.set('usersIdx', document.querySelector('.usersIdx').value);

        fetchApi('/admin/manage/user/terminate/delete', 'post', formData, '회원 탈퇴가 취소되었습니다.');
    }

};

/**
 * 회원 삭제
 */
function deleteUser() {

    const formData = new FormData();

    const msg = confirm('회원을 즉시 삭제하시겠습니까?');
    if (msg) {
        formData.set('usersIdx', document.querySelector('.usersIdx').value);

        fetchApi('/admin/manage/user/delete', 'post', formData, '회원이 삭제되었습니다.', '/admin/manage/user/terminate/list');
    }

};

/**
 * 비회원 삭제
 */
function deleteTemp() {

    const formData = new FormData();
    const listCheck = document.querySelectorAll('input[name=listCheck]');
    const listChecked = document.querySelectorAll('input[name=listCheck]:checked');
    const tempUserIdx = document.querySelectorAll('.tempUserIdx');

    if (listChecked.length == 0) {
        alert('비회원을 선택해 주세요.');
    } else {
        const msg = confirm('비회원을 삭제하시겠습니까?');
        if (msg) {
            for (let i = 0; i < listCheck.length; i++) {
                if (listCheck[i].checked) {
                    formData.append('tempUserIdx', tempUserIdx[i].value);
                }
            }
            fetchApi('/admin/manage/user/temp/delete', 'post', formData, '삭제되었습니다.');
        }
    }

};

/**
 * 회원 등록
 */
function insertUser() {

    const role = document.querySelector('input[name=role]:checked');
    const account = document.querySelector('#userId');
    const pwd = document.querySelector('#userPwd01');
    const pwdCheck = document.querySelector('#userPwd02');
    const name = document.querySelector('.user-name');
    const nick = document.querySelector('.user-nick');
    const tel = document.querySelector('.user-tel');
    const email = document.querySelector('.user-email');
    const gender = document.querySelector('input[name=gender]:checked');
    const age = document.querySelector('.user-age');
    const region01 = document.querySelector('#region01');
    const region02 = document.querySelector('#region02');

    if (account.value == '') {
        alertFocus('아이디를 입력해 주세요.', account);
    } else if (pwd.value == '') {
        alertFocus('비밀번호를 입력해 주세요.', pwd);
    } else if (pwdCheck.value == '') {
        alertFocus('비밀번호를 확인해 주세요.', pwdCheck);
    } else if (name.value == '') {
        alertFocus('이름을 입력해 주세요.', name);
    } else if (nick.value == '') {
        alertFocus('닉네임을 입력해 주세요.', nick);
    } else if (tel.value == '') {
        alertFocus('연락처를 입력해 주세요.', tel);
    } else if (email.value == '') {
        alertFocus('이메일을 입력해 주세요.', email);
    } else if (age.value == '') {
        alertFocus('연령을 선택해 주세요.');
    } else if (region01.value == '') {
        alertFocus('지역을 선택해 주세요.');
    } else {
        const formData = new FormData();

        formData.set('usersAccount', account.value);
        formData.set('usersPass', pwd.value);
        formData.set('usersName', name.value);
        formData.set('usersNick', nick.value);
        formData.set('usersPhone', tel.value);
        formData.set('usersEmail', email.value);
        formData.set('usersRole', role.value);
        formData.set('usersAge', age.value);
        formData.set('usersRegion1', region01.value);
        if (region01.value == '서울') {
            formData.set('usersRegion2', region02.value);
        }
        formData.set('usersGender', gender.value);

        fetchApi('/admin/manage/user/insert', 'post', formData, '회원이 등록되었습니다.', '/admin/manage/user/list');
    }

};

/**
 * 업체 수정
 */
async function updateCompany() {

    const name = document.querySelector('.c-name');
    const region01 = document.querySelector('#region01');
    const activeRegion = document.querySelectorAll('input[name=area]:checked');
    const category = document.querySelectorAll('input[name=category]:checked');
    const enabled = document.querySelector('.c-enabled');

    const phone = document.querySelector('.c-phone');
    const safety = document.querySelector('.c-safety');

    const formData = new FormData();

    if (name.value == '') {
        alertFocus('업체명을 입력해 주세요.', name);
    } else if (activeRegion.length == 0) {
        alert('활동지역을 선택해 주세요.');
    } else if (category.length == 0) {
        alert('분야를 선택해 주세요.');
    } else {
        const msg = confirm('수정하시겠습니까?');

        formData.set('companysPhone', phone.value);

        if (msg) {
            // 최초 승인 시 안심번호 등록
            if (safety.value == '' && enabled.value == 1) {

                const vn = await fetch('/admin/manage/company/safety', {
                    method: 'post',
                })
                    .then(res => res.text())
                    .then(data => {
                        return data;
                    })
                    .catch(err => console.log(err));

                formData.set('companysDummyPhone', vn);
                formData.set('tkGbn', 1);

                fetch('/admin/manage/company/safety/mapping', {
                    method: 'post',
                    body: formData,
                })
                    .then(res => res.json())
                    .then(data => {
                        console.log(data);
                    })
                    .catch(err => console.log(err));
            } else if (safety.value != '') {
                if (enabled.value == 1) {
                    formData.set('companysDummyPhone', safety.value);
                    formData.set('tkGbn', 1);

                    fetch('/admin/manage/company/safety/mapping', {
                        method: 'post',
                        body: formData,
                    })
                        .then(res => res.json())
                        .then(data => {
                            console.log(data);
                        })
                        .catch(err => console.log(err));
                } else if (enabled.value != 1) {
                    formData.set('companysDummyPhone', safety.value);
                    formData.set('tkGbn', 2);

                    fetch('/admin/manage/company/safety/mapping', {
                        method: 'post',
                        body: formData,
                    })
                        .then(res => res.json())
                        .then(data => {
                            console.log(data);
                        })
                        .catch(err => console.log(err));

                    formData.set('companysDummyPhone', '');
                }
            }

            for (let i = 0; i < activeRegion.length; i++) {
                formData.append('activeRegion', activeRegion[i].value);
            }
            for (let i = 0; i < category.length; i++) {
                formData.append('companysCategoryValue', category[i].value);
            }

            formData.set('companysIdx', document.querySelector('.companysIdx').value);
            formData.set('companysName', name.value);
            formData.set('companysComment', document.querySelector('.c-comment').value);
            formData.set('companysSpec', document.querySelector('#editor .ql-editor').innerHTML);
            formData.set('companysRegion1', region01.value);
            if (region01.value == '서울') formData.set('companysRegion2', document.querySelector('#region02').value);
            formData.set('companysRegion3', document.querySelector('.c-region3').value);
            formData.set('companysBankName', document.querySelector('.c-bank').value);
            formData.set('companysBankNumber', document.querySelector('.c-bank-num').value);
            formData.set('companysEnabled', enabled.value);
            const memo = document.querySelector('#eEditor .ql-editor');
            if (memo.innerText != '\n') formData.set('companysMemo', memo.innerHTML);

            fetchApi('/admin/manage/company/update', 'post', formData, '수정되었습니다.');
        }
    }

};

/**
 * 업체 인증
 */
function updateCheck(sort, num) {
    const sortName = (sort == 0) ? '사업자등록증' : '탐정자격증';
    const message = (num == 1) ? sortName + '을 인증하시겠습니까?' : sortName + ' 인증을 취소하시겠습니까?';
    const result = (num == 1) ? sortName + '이 인증되었습니다.' : sortName + ' 인증이 취소되었습니다.';
    const msg = confirm(message);
    if (msg) {

        const formData = new FormData();
        formData.set('sort', sort);
        formData.set('num', num);
        formData.set('companysIdx', document.querySelector('.companysIdx').value);

        fetchApi('/admin/manage/company/check', 'post', formData, result);
    }

};

/**
 * 프리미엄 신청
 */
function requestPremium() {

    const msg = confirm('프리미엄을 신청하시겠습니까?');
    if (msg) {
        const formData = new FormData();
        formData.set('companysIdx', document.querySelector('.companysIdx').value);
        formData.set('companysName', document.querySelector('.c-name').value);
        formData.set('companysCeoName', document.querySelector('.c-ceo').textContent);

        fetchApi('/admin/manage/company/premium/request', 'post', formData, '프리미엄이 신청되었습니다.');
    }

};

/**
 * 프리미엄 등록
 */
function approvalPremium() {

    const start = document.querySelector('#date01');
    const end = document.querySelector('#date02');

    if (start.value == '') {
        alert('시작일을 입력해 주세요.');
    } else if (end.value == '') {
        alert('종료일을 입력해 주세요.');
    } else {
        const msg = confirm('프리미엄을 등록하시겠습니까?');
        if (msg) {
            const formData = new FormData();

            formData.set('companysIdx', document.querySelector('.companysIdx').value);
            formData.set('premiumStartDate', start.value + ' 00:00:00');
            formData.set('premiumEndDate',  end.value + ' 00:00:00');

            fetchApi('/admin/manage/company/premium/approval', 'post', formData, '프리미엄이 등록되었습니다.');
        }
    }

};

/**
 * 프리미엄 해지
 */
function cancelPremium() {

    const msg = confirm('프리미엄을 해지하시겠습니까?');
    if (msg) {
        const formData = new FormData();

        formData.set('companysIdx', document.querySelector('.companysIdx').value);
        fetchApi('/admin/manage/company/premium/cancel', 'post', formData, '프리미엄이 해지되었습니다.');
    }

};

/**
 * 마패 신청
 */
function requestFlag() {

    const region = document.querySelector('#region05');
    const category = document.querySelectorAll('input[name=f-category]:checked');

    if (region.value == '') {
        alert('광고 지역을 선택해 주세요.');
    } else if (category.length == 0) {
        alert('광고 분야를 선택해 주세요.');
    } else {
        const msg = confirm('마패를 신청하시겠습니까?');
        if (msg) {
            const formData = new FormData();

            formData.set('companysIdx', document.querySelector('.companysIdx').value);
            formData.set('companysName', document.querySelector('.f-name').textContent);
            formData.set('companysCeoName', document.querySelector('.f-ceo').textContent);
            formData.set('companysFlagRegion1', region.value);
            if (region.value == '서울') formData.set('companysFlagRegion2', document.querySelector('#region06').value);

            for (let i = 0; i < category.length; i++) {
                formData.append('companysFlagCategory', category[i].value);
            }

            formData.set('flagPrice', document.querySelector('.f-price').textContent.split(' ')[0]);
            formData.set('flagPriceBank', document.querySelector('.f-bank').textContent);

            fetchApi('/admin/manage/company/flag/request', 'post', formData, '마패가 신청되었습니다.');
        }
    }

};

/**
 * 마패 등록
 */
function approvalFlag() {

    const start = document.querySelector('#date03');
    const end = document.querySelector('#date04');

    if (start.value == '') {
        alert('시작일을 입력해 주세요.');
    } else if (end.value == '') {
        alert('종료일을 입력해 주세요.');
    } else {
        const msg = confirm('마패를 등록하시겠습니까?');
        if (msg) {
            const formData = new FormData();

            formData.set('companysIdx', document.querySelector('.companysIdx').value);
            formData.set('flagStartDate', start.value + ' 00:00:00');
            formData.set('flagEndDate', end.value + ' 00:00:00');

            fetchApi('/admin/manage/company/flag/approval', 'post', formData, '마패가 등록되었습니다.');
        }
    }

};

/**
 * 마패 수정
 */
function modifyFlag() {

    const region = document.querySelector('#region03');
    const category = document.querySelectorAll('input[name=m-category]:checked');

    if (category.length == 0) {
        alert('광고 분야를 선택해 주세요.');
    } else {
        const msg = confirm('수정하시겠습니까?');
        if (msg) {
            const formData = new FormData();

            formData.set('companysFlagIdx', document.querySelector('.companysFlagIdx').value);
            formData.set('companysFlagRegion1', region.value);
            if (region.value == '서울') formData.set('companysFlagRegion2', document.querySelector('#region04').value);

            for (let i = 0; i < category.length; i++) {
                formData.append('companysFlagCategory', category[i].value);
            }

            fetchApi('/admin/manage/company/flag/update', 'post', formData, '신청서가 수정되었습니다.');
        }
    }

};

/**
 * 마패 해지
 */
function cancelFlag() {

    const msg = confirm('마패를 해지하시겠습니까?');
    if (msg) {
        const formData = new FormData();
        formData.set('companysIdx', document.querySelector('.companysIdx').value);

        fetchApi('/admin/manage/company/flag/cancel', 'post', formData, '마패가 해지되었습니다.');
    }

};

/**
 * 정책 수정
 */
function modifyPolicy(num) {

    const policy = (num == 0) ? '이용약관' : '개인정보처리방침';

    const msg = confirm(policy + '을 수정하시겠습니까?');
    if (msg) {
        const formData = new FormData();

        formData.set('policyIdx', document.querySelectorAll('.policyIdx')[num].value);
        formData.set('policyContents', document.querySelectorAll('.policyContents')[num].value);

        fetchApi('/admin/manage/policy/update', 'post', formData, policy + '이 수정되었습니다.');
    }

};

function insertNotice() {
    const title = document.querySelector("#title");
    const author = document.querySelector("#author");
    const content = document.querySelector("#content");

    if(title.value == '') {
        alertFocus('제목을 입력해 주세요.', title);
    }

    else {
        let formData = new FormData();

        formData.set("title", title.value);
        formData.set("content", content.value);
        formData.set("author", author.value);

        fetchApi('/admin/manage/notice/insert', 'post', formData, '공지사항이 등록되었습니다.', '/admin/manage/notice/list')
    }
}
/**
 * 리뷰 삭제
 */
function deleteReview() {

    const msg = confirm('리뷰를 삭제하시겠습니까?');
    if (msg) {
        const formData = new FormData();
        formData.set('idx', document.querySelector('.modal-review .modal-id').value);

        fetchApi('/admin/manage/review/delete', 'post', formData, '리뷰가 삭제되었습니다.');
    }

};

/**
 * 리뷰 다중 삭제
 */
function deleteReviewMulti() {
    const formData = new FormData();
    const listCheck = document.querySelectorAll('input[name=listCheck]');
    const listChecked = document.querySelectorAll('input[name=listCheck]:checked');
    const reviewIdx = document.querySelectorAll('.reviewIdx');

    if (listChecked.length == 0) {
        alert('리뷰를 선택해 주세요.');
    } else {
        const msg = confirm('리뷰를 삭제하시겠습니까?');
        if (msg) {
            for (let i = 0; i < listCheck.length; i++) {
                if (listCheck[i].checked) {
                    formData.append('idx', reviewIdx[i].value);
                }
            }
            fetchApi('/admin/manage/review/delete/multi', 'post', formData, '삭제되었습니다.');
        }
    }
};

/**
 * 신고 삭제
 */
function deleteReport() {

    const msg = confirm('신고 내역을 삭제하시겠습니까?');
    if (msg) {
        const formData = new FormData();
        formData.set('idx', document.querySelector('.modal-report .modal-id').value);

        fetchApi('/admin/manage/report/delete', 'post', formData, '신고 내역이 삭제되었습니다.');
    }

};

/**
 * 신고 다중 삭제
 */
function deleteReportMulti() {
    const formData = new FormData();
    const listCheck = document.querySelectorAll('input[name=listCheck]');
    const listChecked = document.querySelectorAll('input[name=listCheck]:checked');
    const reportIdx = document.querySelectorAll('.reportIdx');

    if (listChecked.length == 0) {
        alert('신고 내역을 선택해 주세요.');
    } else {
        const msg = confirm('신고 내역을 삭제하시겠습니까?');
        if (msg) {
            for (let i = 0; i < listCheck.length; i++) {
                if (listCheck[i].checked) {
                    formData.append('idx', reportIdx[i].value);
                }
            }
            fetchApi('/admin/manage/report/delete/multi', 'post', formData, '삭제되었습니다.');
        }
    }
};

/**
 * 의뢰 삭제
 */
function deleteRequest() {

    const msg = confirm('의뢰 내역을 삭제하시겠습니까?');
    if (msg) {
        const formData = new FormData();
        formData.set('requestFormIdx', document.querySelector('.modal-id').value);

        fetchApi('/admin/manage/request/delete', 'post', formData, '의뢰 내역이 삭제되었습니다.');
    }

};

/**
 * 의뢰 수정
 */
function updateRequest() {

    const check = document.querySelectorAll('input[name=r-category]:checked');

    if (check.length == 0) {
        alert('의뢰 분야를 선택해 주세요.');
    } else {
        const msg = confirm('의뢰 내역을 수정하시겠습니까?');
        if (msg) {
            const formData = new FormData();
            formData.set('requestFormIdx', document.querySelector('.modal-id').value);
            formData.set('requestFormRegion1', document.querySelector('#region01').value);
            formData.set('requestFormStatus', document.querySelector('.r-state').value);

            for (let i = 0; i < check.length; i++) {
                formData.append('requestFormCategoryValue', check[i].value);
            }

            fetchApi('/admin/manage/request/update', 'post', formData, '의뢰 내역이 수정되었습니다.');
        }
    }

};

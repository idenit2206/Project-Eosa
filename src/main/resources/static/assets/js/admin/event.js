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
function updateCompany() {

    const name = document.querySelector('.c-name');
    const region01 = document.querySelector('#region01');
    const activeRegion = document.querySelectorAll('input[name=area]:checked');
    const category = document.querySelectorAll('input[name=category]:checked');

    const formData = new FormData();

    if (name.value == '') {
        alertFocus('업체명을 입력해 주세요.', name);
    } else if (activeRegion.length == 0) {
        alert('활동지역을 선택해 주세요.');
    } else if (category.length == 0) {
        alert('분야를 선택해 주세요.');
    } else {
        const msg = confirm('수정하시겠습니까?');
        if (msg) {
            for (let i = 0; i < activeRegion.length; i++) {
                formData.append('activeRegion', activeRegion[i].value);
            }
            for (let i = 0; i < category.length; i++) {
                formData.append('companysCategoryValue', category[i].value);
            }

            formData.set('companysIdx', document.querySelector('.companysIdx').value);
            formData.set('companysName', name.value);
            formData.set('companysComment', document.querySelector('.c-comment').value);
            formData.set('companysSpec', document.querySelector('.c-spec').value);
            formData.set('companysRegion1', region01.value);
            if (region01.value == '서울') formData.set('companysRegion2', document.querySelector('#region02').value);
            formData.set('companysRegion3', document.querySelector('.c-region3').value);
            formData.set('companysBankName', document.querySelector('.c-bank').value);
            formData.set('companysBankNumber', document.querySelector('.c-bank-num').value);
            formData.set('companysEnabled', document.querySelector('.c-enabled').value);
            formData.set('companysMemo', document.querySelector('.c-memo').value);

            fetchApi('/admin/manage/company/update', 'post', formData, '수정되었습니다.');
        }
    }

};

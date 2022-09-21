"use strict";

function _createForOfIteratorHelper(o, allowArrayLike) { var it = typeof Symbol !== "undefined" && o[Symbol.iterator] || o["@@iterator"]; if (!it) { if (Array.isArray(o) || (it = _unsupportedIterableToArray(o)) || allowArrayLike && o && typeof o.length === "number") { if (it) o = it; var i = 0; var F = function F() {}; return { s: F, n: function n() { if (i >= o.length) return { done: true }; return { done: false, value: o[i++] }; }, e: function e(_e) { throw _e; }, f: F }; } throw new TypeError("Invalid attempt to iterate non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method."); } var normalCompletion = true, didErr = false, err; return { s: function s() { it = it.call(o); }, n: function n() { var step = it.next(); normalCompletion = step.done; return step; }, e: function e(_e2) { didErr = true; err = _e2; }, f: function f() { try { if (!normalCompletion && it["return"] != null) it["return"](); } finally { if (didErr) throw err; } } }; }

function _unsupportedIterableToArray(o, minLen) { if (!o) return; if (typeof o === "string") return _arrayLikeToArray(o, minLen); var n = Object.prototype.toString.call(o).slice(8, -1); if (n === "Object" && o.constructor) n = o.constructor.name; if (n === "Map" || n === "Set") return Array.from(o); if (n === "Arguments" || /^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)) return _arrayLikeToArray(o, minLen); }

function _arrayLikeToArray(arr, len) { if (len == null || len > arr.length) len = arr.length; for (var i = 0, arr2 = new Array(len); i < len; i++) { arr2[i] = arr[i]; } return arr2; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); Object.defineProperty(Constructor, "prototype", { writable: false }); return Constructor; }

/* ==============================
    TAB
============================== */
const tabSetting = {
  tab: '.tab',
  tabBtns: '.tabBtn',
  tabItems: '.tabItem'
};

class Tab {
  constructor(settings) {
    this.settings = Object.assign(tabSetting, settings);
    this.tab = document.querySelector(this.settings.tab);
    this.tabBtns = this.tab.querySelectorAll(this.settings.tabBtns);
    this.tabItems = this.tab.querySelectorAll(this.settings.tabItems);
    this.activClass = 'activate';
    this.onClass = 'on';
    this.activIdx = 0;
    this.init();
  }

  init() {
    this.listenTabBtns();
    this.tabBtns[this.activIdx].classList.add(this.activClass);
    this.tabItems[this.activIdx].classList.add(this.activClass);
  }

  listenTabBtns() {
    for (let i = 0; i < this.tabBtns.length; i++) {
      this.tabBtns[i].addEventListener('click', (e) => {
        if (this.tabBtns.length > 1) {
          this.toggleTab(i);
        } else if (this.tabBtns.length == 1) {
          if (this.tabBtns[0].classList.contains('on')) {
            this.offTab();
          } else {
            this.onTab();
          }
        }
      });
    }
  }

  toggleTab(newIdx) {
    this.tabBtns[this.activIdx].classList.remove(this.activClass);
    this.tabBtns[this.activIdx].classList.remove(this.onClass);
    this.tabItems[this.activIdx].classList.remove(this.activClass);

    this.tabBtns[newIdx].classList.add(this.activClass);
    this.tabBtns[newIdx].classList.add(this.onClass);
    this.tabItems[newIdx].classList.add(this.activClass);

    this.activIdx = newIdx;
  }

  onTab() {
    this.tabItems[0].classList.remove(this.activClass);

    this.tabBtns[0].classList.add(this.activClass);
    this.tabBtns[0].classList.add(this.onClass);
    this.tabItems[1].classList.add(this.activClass);
  }

  offTab() {
    this.tabBtns[0].classList.remove(this.activClass);
    this.tabBtns[0].classList.remove(this.onClass);
    this.tabItems[1].classList.remove(this.activClass);

    this.tabItems[0].classList.add(this.activClass);
  }
};

function btnNone() {
  const tabBtn = document.querySelector('.tabBtn');
  const chartTab = document.querySelector('.chart-tab');
  const formAction = document.querySelector('.form__action');

  tabBtn.addEventListener('click', e => {
    if (chartTab.classList.contains('activate')) {
      formAction.style.display = 'block';
    } else {
      formAction.style.display = 'none';
    }
  });
};
/* ==============================
    DROPDOWN
============================== */


var dropdownSettings = {
  dropdown: '.js-dropdown',
  button: '.js-dropdown__btn',
  contents: '.js-dropdown__cont'
};

var Dropdown = /*#__PURE__*/function () {
  function Dropdown(settings) {
    _classCallCheck(this, Dropdown);

    this.settings = Object.assign({}, dropdownSettings, settings);
    this.dropdown = document.querySelector(this.settings.dropdown);
    this.dropdownBtn = this.dropdown.querySelector(this.settings.button);
    this.dropdownCont = this.dropdown.querySelector(this.settings.contents);
    this.contHeight = this.dropdownCont.getClientRects()[0].height;
    this.activClass = 'on';
    this.init();
  }

  _createClass(Dropdown, [{
    key: "init",
    value: function init() {
      this.slideUp();
      this.listenClick();
    }
  }, {
    key: "listenClick",
    value: function listenClick() {
      var _this2 = this;

      this.dropdownBtn.addEventListener('click', function (e) {
        e.preventDefault();
        if (_this2.dropdown.classList.contains(_this2.activClass)) _this2.slideUp();else _this2.slideDown();
      });
    }
  }, {
    key: "slideDown",
    value: function slideDown() {
      this.dropdown.classList.add(this.activClass);
      this.dropdownCont.style = 'height:' + this.contHeight + 'px; overflow: show;';
    }
  }, {
    key: "slideUp",
    value: function slideUp() {
      this.dropdownCont.style = 'height: 0px; overflow: hidden;';
      this.dropdown.classList.remove(this.activClass);
    }
  }]);

  return Dropdown;
}();
/* ==============================
    MULTIPLE DROPDOWN
============================== */


var multiDropdownSettings = {
  group: '.js-dropdown-group',
  dropdown: '.js-dropdown',
  button: '.js-dropdown__btn',
  contents: '.js-dropdown__cont'
};

var MultipleDropdown = /*#__PURE__*/function () {
  function MultipleDropdown(settings) {
    _classCallCheck(this, MultipleDropdown);

    this.settings = Object.assign({}, multiDropdownSettings, settings);
    this.dropdownGroup = document.querySelector(this.settings.group);
    this.dropdowns = this.dropdownGroup.querySelectorAll(this.settings.dropdown);
    this.dropdownBtns = this.dropdownGroup.querySelectorAll(this.settings.button);
    this.dropdownConts = this.dropdownGroup.querySelectorAll(this.settings.contents);
    this.contHeights = [];
    this.activClass = 'on';
    this.activatedIdx = -1;
    this.init();
  }

  _createClass(MultipleDropdown, [{
    key: "init",
    value: function init() {
      for (var i = 0; i < this.dropdowns.length; i++) {
        this.pushContsHeight(i);
        this.slideUp(i);
        this.listenClick(i);
      }

      this.dropdownGroup.classList.remove('loading');
    }
  }, {
    key: "pushContsHeight",
    value: function pushContsHeight(index) {
      this.contHeights[index] = this.dropdownConts[index].getClientRects()[0].height;
    }
  }, {
    key: "listenClick",
    value: function listenClick(index) {
      var _this3 = this;

      this.dropdownBtns[index].addEventListener('click', function (e) {
        e.preventDefault();
        if (_this3.dropdowns[index].classList.contains(_this3.activClass)) _this3.slideUp(index);else _this3.slideDown(index);
      });
    }
  }, {
    key: "slideDown",
    value: function slideDown(index) {
      if (this.activatedIdx >= 0) this.slideUp(this.activatedIdx);
      this.dropdowns[index].classList.add(this.activClass);
      this.dropdownConts[index].style = 'height:' + this.contHeights[index] + 'px; overflow: show;';
      this.activatedIdx = index;
    }
  }, {
    key: "slideUp",
    value: function slideUp(index) {
      this.dropdownConts[index].style = 'height: 0px; overflow: hidden;';
      this.dropdowns[index].classList.remove(this.activClass);
      this.activatedIdx = -1;
    }
  }]);

  return MultipleDropdown;
}();
/* ==============================
    MODAL
============================== */


var ModalSettings = {
  modal: '.modal',
  button: '.js-modalShow',
  close: '.js-modalClose',
  name: ''
};

var Modal = /*#__PURE__*/function () {
  function Modal(settings) {
    _classCallCheck(this, Modal);

    this.container = document.querySelector('body');
    this.settings = Object.assign({}, ModalSettings, settings);
    this.modal = document.querySelector(this.settings.modal + '[data-modal="' + this.settings.name + '"]');
    this.modalBtns = document.querySelectorAll(this.settings.button + '[data-modal="' + this.settings.name + '"]');
    this.modalCloses = this.modal.querySelectorAll(this.settings.close);
    this.activate = 'activate';
    this.handleWindowClick = null;
    this.init();
  }

  _createClass(Modal, [{
    key: "init",
    value: function init() {
      this.listenClick();
    }
  }, {
    key: "listenClick",
    value: function listenClick() {
      var _iterator = _createForOfIteratorHelper(this.modalBtns),
          _step;

      try {
        for (_iterator.s(); !(_step = _iterator.n()).done;) {
          var btn = _step.value;
          btn.addEventListener('click', this.activateModal.bind(this));
        }
      } catch (err) {
        _iterator.e(err);
      } finally {
        _iterator.f();
      }

      var _iterator2 = _createForOfIteratorHelper(this.modalCloses),
          _step2;

      try {
        for (_iterator2.s(); !(_step2 = _iterator2.n()).done;) {
          var _btn = _step2.value;

          _btn.addEventListener('click', this.deactivateModal.bind(this));
        }
      } catch (err) {
        _iterator2.e(err);
      } finally {
        _iterator2.f();
      }

      this.handleWindowClick = this.listenWindowClick.bind(this);
    }
  }, {
    key: "activateModal",
    value: function activateModal(event) {
      if (event) event.preventDefault();
      this.cleanActivated();
      this.container.classList.add('modal-overlayed');
      this.modal.classList.add(this.activate);
      window.addEventListener('click', this.handleWindowClick);
    }
  }, {
    key: "deactivateModal",
    value: function deactivateModal() {
      this.container.classList.remove('modal-overlayed');
      this.modal.classList.remove(this.activate);
      window.removeEventListener('click', this.handleWindowClick);
      window.removeEventListener('resize', this.deactivateModal.bind(this));
    }
  }, {
    key: "cleanActivated",
    value: function cleanActivated() {
      var activated = document.querySelector('.modal.activate');
      if (activated) activated.classList.remove(this.activate);
    }
  }, {
    key: "listenWindowClick",
    value: function listenWindowClick(event) {
      if (event.target.closest(this.settings.button) || event.target.classList.contains(this.settings.button.substring(1))) return;
      if (event.target.closest('.modal') || event.target.classList.contains('modal')) return;
      if (event.target.closest('button') || event.target.classList.contains('button')) return;
      if (event.target.closest('input') || event.target.classList.contains('input')) return;
      if (event.target.closest('.special-del') || event.target.classList.contains('special-del')) return;
      this.deactivateModal();
    }
  }]);

  return Modal;
}();
/* ==============================
    TABLE
============================== */


var Table = /*#__PURE__*/function () {
  function Table(tableEl) {
    var option = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : null;

    _classCallCheck(this, Table);

    this.table = tableEl;
    this.checkControll = this.table.querySelector('thead input[type="checkbox"]');
    this.checkEls = this.table.querySelectorAll('tbody input[type="checkbox"]');
    this.activClass = 'selected';
    this.checkCnt = 0;
    this.orderSelect = this.table.querySelectorAll('thead select.order');
    this.sorts = this.table.querySelectorAll('thead .sort');
    this.init();
  }

  _createClass(Table, [{
    key: "init",
    value: function init() {
      if (this.orderSelect) this.listenOrderSelect();
      if (this.sorts) this.listenSort();
    }
  },{
    key: "countCheck",
    value: function countCheck(event) {
      var row = event.currentTarget.closest('tr');

      if (event.currentTarget.checked) {
        row.classList.add(this.activClass);
        this.checkCnt++;
      } else {
        row.classList.remove(this.activClass);
        if (this.checkCnt == 1 && this.checkControll.checked) this.checkControll.checked = false;
        this.checkCnt--;
      }
    }
  }, {
    key: "activateChecks",
    value: function activateChecks() {
      for (var i = 0; i < this.checkEls.length; i++) {
        this.checkEls[i].checked = true;
        this.checkEls[i].closest('tr').classList.add(this.activClass);
      }

      this.checkCnt = this.checkEls.length;
    }
  }, {
    key: "deactivateChecks",
    value: function deactivateChecks() {
      for (var i = 0; i < this.checkEls.length; i++) {
        this.checkEls[i].checked = false;
        this.checkEls[i].closest('tr').classList.remove(this.activClass);
      }

      this.checkCnt = 0;
    }
  }, {
    key: "listenOrderSelect",
    value: function listenOrderSelect() {
      var _this5 = this;

      var _iterator4 = _createForOfIteratorHelper(this.orderSelect),
          _step4;

      try {
        for (_iterator4.s(); !(_step4 = _iterator4.n()).done;) {
          var orderSel = _step4.value;
          orderSel.value = null;
          orderSel.addEventListener('change', function (e) {
            var order = e.currentTarget.value;
            if (order.length > 0) _this5.activateOrder(e.currentTarget.previousElementSibling, order);
          });
        }
      } catch (err) {
        _iterator4.e(err);
      } finally {
        _iterator4.f();
      }
    }
  }, {
    key: "activateOrder",
    value: function activateOrder(el, order) {
      el.className = order;
    }
  }, {
    key: "listenSort",
    value: function listenSort() {
      var _this6 = this;

      var _iterator5 = _createForOfIteratorHelper(this.sorts),
          _step5;

      try {
        for (_iterator5.s(); !(_step5 = _iterator5.n()).done;) {
          var sort = _step5.value;
          sort.addEventListener('click', function (e) {
            _this6.activateSort(e.currentTarget);
          });
        }
      } catch (err) {
        _iterator5.e(err);
      } finally {
        _iterator5.f();
      }
    }
  }, {
    key: "activateSort",
    value: function activateSort(clicked) {
      if (clicked.classList.contains('ascending')) {
        clicked.classList.remove('ascending');
        clicked.classList.add('descending');
      } else if (clicked.classList.contains('descending')) {
        clicked.classList.remove('descending');
        clicked.classList.add('ascending');
      } else {
        clicked.classList.add('ascending');
      }
    }
  }]);

  return Table;
}();
/* ==============================
    PASSWORD VALIDATION
============================== */


var pwdSettings = {
  pwd01: '.js-pwd01',
  pwd02: '.js-pwd02'
};

var PwdValidation = /*#__PURE__*/function () {
  function PwdValidation(settings) {
    _classCallCheck(this, PwdValidation);

    this.settings = Object.assign({}, pwdSettings, settings);
    this.pwd = document.querySelector(this.settings.pwd01);
    this.pwdCf = document.querySelector(this.settings.pwd02);
    this.init();
  }

  _createClass(PwdValidation, [{
    key: "init",
    value: function init() {
      this.validationFn();
      if (this.pwdCf) this.matchFn();
    }
  }, {
    key: "validationFn",
    value: function validationFn() {
      var _this7 = this;

      this.pwd.addEventListener('change', function (e) {
        var result = _this7.isValid(e.currentTarget.value);

        if (_this7.pwdCf && !result.valid) _this7.pwdCf.value = '';

        _this7.writeWarning(_this7.pwd, result.warning);
      });
    }
  }, {
    key: "isValid",
    value: function isValid(pwdText) {
      var regEx = /^(?=.*?[0-9])(?=.*?[a-zA-Z]).{8,16}$/;
      var result = {
        valid: false,
        warning: ''
      };

      if (pwdText.length <= 0) {
        result.warning = '비밀번호를 입력해 주십시오.';
        return result;
      } else {
        if (regEx.test(pwdText)) {
          result.valid = true;
        } else {
          this.pwd.value = '';
          this.pwd.focus();
          result.warning = '비밀번호는 문자와 숫자를 포함하여 8~16자리로 입력해 주세요.';
        }
      }

      return result;
    }
  }, {
    key: "matchFn",
    value: function matchFn() {
      var _this8 = this;

      this.pwdCf.addEventListener('change', function (e) {
        var result = _this8.isMatch(e.currentTarget.value);

        if (!result.match) {
          e.currentTarget.value = '';
        }

        _this8.writeWarning(_this8.pwdCf, result.warning);
      });
    }
  }, {
    key: "isMatch",
    value: function isMatch(pwdText) {
      var pwdVal = this.pwd.value;
      var pwdValid = this.isValid(pwdVal);
      var result = {
        match: false,
        warning: ''
      };

      if (!pwdValid.valid) {
        this.writeWarning(this.pwd, pwdValid.warning);
        this.pwd.focus();
      } else {
        if (pwdVal == pwdText) {
          result.match = true;
          result.warning = '';
        } else {
          result.warning = '비밀번호가 일치하지 않습니다.';
        }
      }

      return result;
    }
  }, {
    key: "writeWarning",
    value: function writeWarning(el, text) {
      if (text.length <= 0) {
        var warnEl = el.nextElementSibling;
        if (warnEl && warnEl.classList.contains('warning')) warnEl.remove();
      } else {
        var nextEl = el.nextElementSibling;

        if (nextEl && nextEl.classList.contains('warning')) {
          nextEl.innerText = text;
        } else {
          var _warnEl = document.createElement('i');

          _warnEl.classList.add('warning');

          _warnEl.innerText = text;
          if (nextEl) el.parentNode.insertBefore(_warnEl, nextEl);else el.parentNode.append(_warnEl);
        }
      }
    }
  }]);

  return PwdValidation;
}();
/* ==============================
    DATE-PICKER
============================== */
var DatePicker = /*#__PURE__*/function () {
  function DatePicker(selectedEl) {
    _classCallCheck(this, DatePicker);

    this.datepicker = selectedEl || document.querySelector('.datepicker');
    this.input = this.datepicker.querySelector('input');
    this.label = this.datepicker.querySelector('label');
    this.init();
  }

  _createClass(DatePicker, [{
    key: "init",
    value: function init() {
      this.setPlaceholder();
      this.listenChange();
    }
  }, {
    key: "listenChange",
    value: function listenChange() {
      var _this10 = this;

      this.input.addEventListener('change', function (e) {
        _this10.writeDate(_this10.input.value);
      });
    }
  }, {
    key: "setPlaceholder",
    value: function setPlaceholder() {
      this.label.dataset.placeholder = this.label.innerText;
    }
  }, {
    key: "writeDate",
    value: function writeDate(value) {
      if (value.length <= 0) this.label.innerText = this.input.dataset.placeholder;else this.label.innerText = value;
    }
  }]);

  return DatePicker;
}();
/* 테이블 클릭 스크립트 */
function linkTo(event, addr) {
  var preventClicks = ['input', 'a', 'button', 'select', 'option'];

  var isPreventClick = function isPreventClick(clicked) {
    var isPrevent = false;
    var clickedTag = clicked.tagName.toLowerCase();

    var _iterator6 = _createForOfIteratorHelper(preventClicks),
        _step6;

    try {
      for (_iterator6.s(); !(_step6 = _iterator6.n()).done;) {
        var prevent = _step6.value;

        if (clickedTag == prevent) {
          isPrevent = true;
          break;
        }
      }
    } catch (err) {
      _iterator6.e(err);
    } finally {
      _iterator6.f();
    }

    return isPrevent;
  };

  if (!isPreventClick(event.target) && addr.length > 0) window.location.href = addr;
}

/* 전화번호 포맷 */
function telFormat() {
  const contacts = document.querySelectorAll('.contact');
  for (const contact of contacts) {
    contact.textContent = contact.textContent.replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
  }
};

/* 안심번호 포맷 */
function safetyFormat() {
  const safeties = document.querySelectorAll('.safety');
  for (const safety of safeties) {
    safety.textContent = safety.textContent.replace(/([\d|*]{4})([\d|*]{4})([\d|*]{4})/, `$1-$2-$3`);
  }
};

/* 숫자만 사용 */
function onlyNum() {
  const num = document.querySelectorAll('.onlyNum');

  for (let i = 0; i < num.length; i++) {
    num[i].addEventListener('input', e => {
      num[i].value = num[i].value.replace(/[^0-9]/g, '');
    });
  }
};

/* 소수점 찍기 */
function decimalPoint() {
  const num = document.querySelectorAll('.decimal');

  for (let i = 0; i < num.length; i++) {
    num[i].addEventListener('keydown', e => {
      if (num[i].value.includes('.')) {
        if (e.key == '.') {
          e.preventDefault();
        }
      }
    });

    num[i].addEventListener('input', e => {
      num[i].value = num[i].value.replace(/[^0-9.]/g, '');

      if (num[i].value.includes('.') == false && num[i].value.replace('.', '').length > 2) {
        num[i].value = num[i].value.replace(/(.{2})/g, '$1.');
      }
    });
  };
};

/* 주소 이동 */
function goTo(url) {
  location.href = url;
};

/* 페이지 표시 */
function pageOn() {
  const btn = document.querySelectorAll('.pageNum');

  const url = location.search;
  const param = new URLSearchParams(url);
  let page = param.get('page');

  if (page === null) page = 1;

  for (let i = 0; i < btn.length; i++) {
    if (btn[i].textContent == page) {
      btn[i].classList.add('on');
    }
  }
};

/* 리스트 체크 */
function listCheck() {
  const all = document.querySelector('#checkAll');
  const listCheck = document.querySelectorAll('input[name=listCheck]');

  for (let i = 0; i < listCheck.length; i++) {
    all.addEventListener('change', e => {
        if (all.checked) {
          listCheck[i].checked = true;
          listCheck[i].parentElement.parentElement.classList.add('selected');
        } else {
          listCheck[i].checked = false;
          listCheck[i].parentElement.parentElement.classList.remove('selected');
        }
    });

    listCheck[i].addEventListener('change', e => {
      const listChecked = document.querySelectorAll('input[name=listCheck]:checked');
      if (listChecked.length == 10) {
        all.checked = true;
      } else {
        all.checked = false;
      }
      if (listCheck[i].checked) {
        listCheck[i].parentElement.parentElement.classList.add('selected');
      } else {
        listCheck[i].parentElement.parentElement.classList.remove('selected');
      }
    });
  }
};

/* 필터 전체버튼 */
function filterAll() {
  const div = document.querySelector('.filter-wrap');
  const btn = div.querySelector('#lo00');
  const check = div.querySelectorAll('input[type=checkbox]');

  btn.addEventListener('change', e => {
    for (let i = 1; i < check.length; i++) {
      check[i].checked = false;
    }
  });

  for (let i = 1; i < check.length; i++) {
    check[i].addEventListener('change', e => {
      if (btn.checked) btn.checked = false;

      const checkNum = div.querySelectorAll('input[type=checkbox]:checked');
      if (checkNum.length < 1) btn.checked = true;
    });
  }
};

/* 회원 탭 on */
function userTab() {
  const urlParams = new URL(location.href).searchParams;
  const role = urlParams.get('role');

  if (role == null || role == 'DETECTIVE') {
    document.querySelector('.tab-detective').classList.add('on');
  } else if (role == 'CLIENT') {
    document.querySelector('.tab-client').classList.add('on');
  }
};

/* 업체 탭 on */
function companyTab() {
  const urlParams = new URL(location.href).searchParams;
  const enabled = urlParams.get('enabled');

  if (enabled == null || enabled == 1) {
    document.querySelector('.enabled1').classList.add('on');
  } else if (enabled == 0) {
    document.querySelector('.enabled0').classList.add('on');
  } else if (enabled == -1) {
    document.querySelector('.enabled-1').classList.add('on');
  }
};

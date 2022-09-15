"use strict";

function layoutFn() {
  // mobile menu
  var header = document.querySelector('.header');
  var aside = document.querySelector('.aside');
  var asideBtn = aside.querySelector('.aside__btn');
  asideBtn.addEventListener('click', function (e) {
    e.preventDefault();
    if (aside.classList.contains('on')) aside.classList.remove('on');else aside.classList.add('on');
  }); // dropdown menu

  new MultipleDropdown({
    group: '.nav-main'
  });
  new Dropdown({
    dropdown: '.header__nav'
  });

  // var path = window.location.pathname.split('/');
  // var depth01 = aside.querySelector("a[data-category=\"".concat(path[path.length - 2], "\"]"));
  // var depth02 = depth01.closest('li').querySelector("a[data-category=\"".concat(path[path.length - 1].split('.')[0], "\"]"));
  // if (depth01) depth01.classList.add('page');
  // if (depth02) depth02.classList.add('page');
}

function highlightMenu(subCate) {
  const category = document.querySelector('#main').dataset.category;
  const aside = document.querySelector('.aside');
  const menu = aside.querySelector('a[data-category='+ category +']');
  const menuLi = menu.closest('li');

  menu.classList.add('page');

  if(subCate != null) {
    const sub = menuLi.querySelector('a[data-category='+ subCate +']');
    sub.classList.add('page');
  }

}

window.addEventListener('load', function () {
  layoutFn();
});
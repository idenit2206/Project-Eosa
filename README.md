# Project-Eosa
<b>프로젝트 어사</b>  
<b><i>시작일: </i></b> 22.06.21  
<b><i>종료일: </i></b>  
<b><i>Backend Developer: </i></b> 박민재, 김지훈, 김혁

### 기타사항
```
Backend DB테이블의 필수 레코드(row)
1. 테이블명: Users
2. 레코드 값
INSERT INTO `Users` (`usersIdx`, `usersAccount`, `usersPass`, `usersName`, `usersNick`, `usersPhone`, `usersEmail`, `usersRole`, `usersAge`, `usersRegion1`, `usersRegion2`, `usersGender`, `usersJoinDate`, `usersNotice`, `provider`, `providerId`, `token`, `device`, `usersProfile`, `usersEnabled`, `usersDelete`) VALUES (0, 'admin', '$2a$10$swSu9kT34LxuN1sPDug00ekd.ZDVSz9rbMMG6UkEhcf.7TY2OAgDa', 'superadmin001', 'superadmin001', '01009021001', 'superadmin001@email.com', 'SUPER_ADMIN', 20, '서울', '중구', 0, '2022-09-02 11:34:01', 0, 'local', NULL, NULL, NULL, NULL, 1, 0);

usersIdx, usersAccount, usersPass 속성의 값은 변경하시면 안됩니다.

usersName, usersPhone, usersEmail, usersRegion1, usersRegion2, usersGender, usersJoinDate 등의
속성 값은 사용자의 임의로 설정이 가능합니다.
```

<table caption="Users (1 rows)">
    <thead>
    <tr>
        <th class="col0">usersIdx</th>
        <th class="col1">usersAccount</th>
        <th class="col2">usersPass</th>
        <th class="col3">usersName</th>
        <th class="col4">usersNick</th>
        <th class="col5">usersPhone</th>
        <th class="col6">usersEmail</th>
        <th class="col7">usersRole</th>
        <th class="col8">usersAge</th>
        <th class="col9">usersRegion1</th>
        <th class="col10">usersRegion2</th>
        <th class="col11">usersGender</th>
        <th class="col12">usersJoinDate</th>
        <th class="col13">usersNotice</th>
        <th class="col14">provider</th>
        <th class="col15">providerId</th>
        <th class="col16">token</th>
        <th class="col17">device</th>
        <th class="col18">usersProfile</th>
        <th class="col19">usersEnabled</th>
        <th class="col20">usersDelete</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td class="col0">0</td>
        <td class="col1">admin</td>
        <td class="col2">$2a$10$swSu9kT34LxuN1sPDug00ekd.ZDVSz9rbMMG6UkEhcf.7TY2OAgDa</td>
        <td class="col3">superadmin001</td>
        <td class="col4">superadmin001</td>
        <td class="col5">01009021001</td>
        <td class="col6">superadmin001@email.com</td>
        <td class="col7">SUPER_ADMIN</td>
        <td class="col8">20</td>
        <td class="col9">서울</td>
        <td class="col10">중구</td>
        <td class="col11">0</td>
        <td class="col12">2022-09-02 11:34:01</td>
        <td class="col13">0</td>
        <td class="col14">local</td>
        <td class="col15"></td>
        <td class="col16"></td>
        <td class="col17"></td>
        <td class="col18"></td>
        <td class="col19">1</td>
        <td class="col20">0</td>
    </tr>
    </tbody>
</table>


```
Backend DB테이블의 필수 레코드(row)
1. 테이블명: Companys
2. 레코드 값
INSERT INTO `Companys` (`companysIdx`, `companysName`, `companysCeoIdx`, `companysCeoName`, `companysComment`, `companysSpec`, `companysPhone`, `companysDummyPhone`, `companysMemo`, `companysRegion1`, `companysRegion2`, `companysRegion3`, `companysRegistCerti`, `companysRegistCertiDate`, `companysRegistCertiName`, `companysRegistCertiCheck`, `companysLicense`, `companysLicenseName`, `companysLicenseCheck`, `companysProfileImage`, `companysProfileImageName`, `companysBankName`, `companysBankNumber`, `companysRegistDate`, `companysPremium`, `companysLocalPremium`, `companysEnabled`, `companysDelete`) VALUES (0, 'eosa', 0, 'superadmin001', '어사 관리자 입니다(절대 지우면 안됩니다.)', '<h1>어사 관리자</h1><p><br></p><p><b>반드시 필요한 rows 입니다.</b></p>', NULL, NULL, NULL, '서울', '', '', NULL, NULL, NULL, 0, NULL, NULL, 0, NULL, NULL, NULL, NULL, '2022-09-26 15:36:02', 0, 0, 0, 2);


companysIdx, companysCeoIdx 속성의 값은 변경하시면 안됩니다.
```
<table caption="Companys (1 rows)">
    <thead>
    <tr>
        <th class="col0">companysIdx</th>
        <th class="col1">companysName</th>
        <th class="col2">companysCeoIdx</th>
        <th class="col3">companysCeoName</th>
        <th class="col4">companysComment</th>
        <th class="col5">companysSpec</th>
        <th class="col6">companysPhone</th>
        <th class="col7">companysDummyPhone</th>
        <th class="col8">companysMemo</th>
        <th class="col9">companysRegion1</th>
        <th class="col10">companysRegion2</th>
        <th class="col11">companysRegion3</th>
        <th class="col12">companysRegistCerti</th>
        <th class="col13">companysRegistCertiDate</th>
        <th class="col14">companysRegistCertiName</th>
        <th class="col15">companysRegistCertiCheck</th>
        <th class="col16">companysLicense</th>
        <th class="col17">companysLicenseName</th>
        <th class="col18">companysLicenseCheck</th>
        <th class="col19">companysProfileImage</th>
        <th class="col20">companysProfileImageName</th>
        <th class="col21">companysBankName</th>
        <th class="col22">companysBankNumber</th>
        <th class="col23">companysRegistDate</th>
        <th class="col24">companysPremium</th>
        <th class="col25">companysLocalPremium</th>
        <th class="col26">companysEnabled</th>
        <th class="col27">companysDelete</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td class="col0">0</td>
        <td class="col1">eosa</td>
        <td class="col2">0</td>
        <td class="col3">superadmin001</td>
        <td class="col4">어사 관리자 입니다(절대 지우면 안됩니다.)</td>
        <td class="col5">&lt;h1&gt;어사 관리자&lt;/h1&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&lt;b&gt;반드시 필요한 rows 입니다.&lt;/b&gt;&lt;/p&gt;</td>
        <td class="col6"></td>
        <td class="col7"></td>
        <td class="col8"></td>
        <td class="col9">서울</td>
        <td class="col10"></td>
        <td class="col11"></td>
        <td class="col12"></td>
        <td class="col13"></td>
        <td class="col14"></td>
        <td class="col15">0</td>
        <td class="col16"></td>
        <td class="col17"></td>
        <td class="col18">0</td>
        <td class="col19"></td>
        <td class="col20"></td>
        <td class="col21"></td>
        <td class="col22"></td>
        <td class="col23">2022-09-26 15:36:02</td>
        <td class="col24">0</td>
        <td class="col25">0</td>
        <td class="col26">0</td>
        <td class="col27">2</td>
    </tr>
    </tbody>
</table>

```
Detective 회원이 정상적으로 자신의 업체를 등록하기 위해서는 관리자 페이지에서 업체 관리 > 금액 관리 > 지역과 분야 데이터를 미리 추가해 주셔야 합니다.
```
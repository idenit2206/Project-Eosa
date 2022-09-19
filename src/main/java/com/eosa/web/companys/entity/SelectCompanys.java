package com.eosa.web.companys.entity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Companys, CompanysActiveRegion, CompanysCategory 3개의 테이블을 JOIN한 결과 조회
 */
public interface SelectCompanys {
    Long getCompanysIdx();
    String getCompanysName();
    Long getCompanysCeoIdx();
    String getCompanysCeoName();
    String getCompanysComment();
    String getCompanysSpec();
    String getCompanysPhone();
    String getCompanysDummyPhone();
    String getCompanysMemo();
    String getCompanysRegion1();
    String getCompanysRegion2();
    String getCompanysRegion3();
    String getCompanysRegistCerti();
    String getCompanysRegistCertiName();
    String getCompanysLicense();
    String getCompanysLicenseName();
    String getCompanysProfileImage();
    String getCompanysProfileImageName();
    String getCompanysBankName();
    String getCompanysBankNumber();
    int getCompanysPremium();
    int getCompanysLocalPremium();
    LocalDateTime getCompanysRegistDate();
    int getCompanysEnabled();
    int getCompanysDelete();
    List<String> getActiveRegion();
    List<String> getCompanysCategoryValue();
}

package com.eosa.web.companys.entity;

import java.time.LocalDateTime;
import java.util.List;

public interface SelectCompanysUserLikeCompanyEnable {
    Long getCompanysIdx();
    String getCompanysName();
    Long getCompanysCeoIdx();
    String getCompanysCeoName();
    String getCompanysComment();
    String getCompanysSpec();
    String getCompanysPhone();
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
    int getUserLikeCompanyEnable();
}

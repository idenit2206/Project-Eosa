package com.eosa.web.companys.entity;

import java.util.List;

public interface SelectCompanyInfoByUsersIdx {
    Long getCompanysIdx();
    String getCompanysName();
    String getCompanysComment();
    String getCompanysSpec();
    String getCompanysRegion1();
    String getCompanysRegion2();
    String getCompanysRegion3();
    String getCompanysRegistCerti();
    String getCompanysRegistCertiDate();
    String getCompanysProfileImage();
    List<String> getCompanysCategory();
    List<String> getCompanysActiveRegion();
    List<String> getCompanysLicenseName();
}

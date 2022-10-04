package com.eosa.web.companys.entity;

import java.time.LocalDateTime;
import java.util.List;

public interface SelectAllCompanysList {
    
    Long getCompanysIdx();
    String getCompanysName();
    Long geCompanysCeoIdx();
    String getCompanysCeoName();
    String getCompanysPhone();
    String getCompanysDummyPhone();
    String getCompanysComment();
    String getCompanysSpec();
    LocalDateTime getCompanysRegistDate();
    String getCompanysRegion1();
    String getCompanysRegion3();
    String getCompanysRegistCerti();
    String getCompanysRegistCertiDate();
    String getCompanysRegistCertiName();
    String getCompanysLicense();
    String getCompanysLicenseName();
    String getCompanysProfileImage();
    String getCompanysProfileImageName();
    int getCompanysEnabled();
    int getCompanysPremium();
    int getCompanysLocalPremium();
    List<String> getCompanysCategory();
//    int getUserLikeCompanyEnable();


}
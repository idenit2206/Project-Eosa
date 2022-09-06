package com.eosa.web.companys.entity;

import java.time.LocalDateTime;
import java.util.List;

public interface SelectAllCompanysList {
    
    Long getCompanysIdx();
    String getCompanysName();
    Long geCompanysCeoIdx();
    String getCompanysCeoName();
    String getCompanysPhone();
    String getCompanysComment();
    String getCompanysSpec();
    LocalDateTime getCompanysRegistDate();
    String getCompanysRegion1();
    String getCompanyProfileImage();
    int getCompanysEnabled();
    int getCompanysPremium();
    int getCompanysLocalPremium();
    int getUserLikeCompanyEnable();
    List<String> getCompanysCategory();


}
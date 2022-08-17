package com.eosa.admin.companysmanager.entity;

import java.time.LocalDateTime;

public interface GetCompanysList {
    
    Long getCompanysIdx();
    String getCompanysName();
    String getUsersName();
    String getCompanysPhone();
    // String getCompanysCategory();
    String getComapnysRegion1();
    int getCompanysEnabled();
    int getCompanysPremium();
    int getCompanysLocalPremium();
    LocalDateTime getCompanysRegistDate();

}
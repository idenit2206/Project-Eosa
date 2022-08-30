package com.eosa.admin.companysmanage.entity;

import java.time.LocalDateTime;
import java.util.List;

public interface SelectCompanysList {
    
    Long getCompanysIdx();
    String getCompanysName();
    Long geCompanysCeoIdx();
    String getCompanysCeoName();
    String getCompanysPhone();
    List<String> getCompanysCategory();
    String getCompanysRegion1();
    int getCompanysEnabled();
    int getCompanysPremium();
    int getCompanysLocalPremium();
    LocalDateTime getCompanysRegistDate();

}
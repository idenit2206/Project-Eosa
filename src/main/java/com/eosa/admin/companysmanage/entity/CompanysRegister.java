package com.eosa.admin.companysmanage.entity;

import java.util.List;

public interface CompanysRegister {
    Long getCompanysidx();
    String getCompanysName();
    Long getCompanysCeoIdx();
    String getCompanysCeoName();
    String getCompanysComment();
    String getCompanysSpec();
    String getCompanysPhone();
    String getCompanysRegion1();
    String getCompanysRegion2();
    String getCompanysRegion3();
    List<String> getCompanysCategory();
    List<String> getCompanysActiveRegion();
    int getCompanysLocalPremium();
}
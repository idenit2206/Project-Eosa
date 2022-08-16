package com.eosa.admin.companysmanager;

import java.util.List;

public interface CompanysRegister {
    Long getCompanysidx();
    String getCompanysName();
    Long getCompanysCeoIdx();
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

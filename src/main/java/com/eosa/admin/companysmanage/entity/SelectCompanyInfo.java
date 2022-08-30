package com.eosa.admin.companysmanage.entity;

import java.util.List;

public interface SelectCompanyInfo {
    Long getCompanysIdx();
    String getCompanysCeoName();
    String getCompanysName();
    int getCompanysEnabled();
    String getCompanysComment();
    String getCompanysSpec();
    List<String> getCompanysCategory();
    List<String> getCompanysActiveRegion();
}

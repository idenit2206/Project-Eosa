package com.eosa.admin.companysmanage;

public interface GetByCompanysName {
    Long getCompanysIdx(); 
    Long getCompanysCeoIdx();   
    String getCompanysName();    
    String getCompanysRegion1();
    String getCompanysRegion2();
    String getCompanysRegion3();   
    String getCompanysComment();
    String getCompanysSpec();
    int getCompanysEnabled();
}

package com.eosa.web.companys.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public interface SelectAllCompanysForNormal {

    String getCompanysName();
    Long getCompanysIdx();
    Long getCompanysCeoIdx();
    String getCompanysCeoName();
    String getCompanysPhone();
    String getCompanysComment();
    String getCompanysSpec();
    String getCompanysRegion1();
    String getCompanysRegistCerti();
    String getCompanysRegistCertiName();
    int getCompanysRegistCertiCheck();
    String getCompanysLicense();
    String getCompanysLicenseName();
    int getCompanysLicenseCheck();
    String getCompanysProfileImage();
    String getCompanysProfileImageName();
    LocalDateTime getCompanysRegistDate();
    boolean getCompanysEnabled();
    boolean getCompanysPremium();
    boolean getCompanysLocalPremium();
    List<String> getCompanysCategory();

}

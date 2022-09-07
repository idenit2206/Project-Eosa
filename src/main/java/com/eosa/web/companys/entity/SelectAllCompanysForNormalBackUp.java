package com.eosa.web.companys.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class SelectAllCompanysForNormalBackUp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long selectAllCompanysForNormalIdx;
    @Transient private String companysName;
    @Transient private Long companysIdx;
    @Transient private Long companysCeoIdx;
    @Transient private String companysCeoName;
    @Transient private String companysPhone;
    @Transient private String companysComment;
    @Transient private String companysSpec;
    @Transient private String companysRegion1;
    @Transient private String companysRegistCerti;
    @Transient private String companysLicense;
    @Transient private String companysProfileImage;
    @Transient LocalDateTime companysRegistDate;
    @Transient private int companysEnabled;
    @Transient private int companysPremium;
    @Transient private int companysLocalPremium;
    @Transient private List<String> companysCategory;

}

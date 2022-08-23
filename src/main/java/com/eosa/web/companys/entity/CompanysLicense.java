package com.eosa.web.companys.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="CompanysLicense")
public class CompanysLicense {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long idx;

    @Column Long companysIdx;
    @Column String companysLicenseName;
    @Column String companysLicenseValue;
    @Column LocalDateTime insertDate;

    @ManyToOne(targetEntity=Companys.class, fetch=FetchType.EAGER)
    private Companys companys;

}

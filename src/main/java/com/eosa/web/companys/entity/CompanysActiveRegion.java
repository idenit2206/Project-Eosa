package com.eosa.web.companys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="CompanysActiveRegion")
public class CompanysActiveRegion {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long comapnysActiveRegionIdx;

    @Column private Long companysIdx;
    @Column private String activeRegion;

    // @ManyToOne(targetEntity=Companys.class, fetch=FetchType.EAGER)
    @ManyToOne
    @JoinColumn(name="Companys_companysIdx")
    private Companys companys;

}

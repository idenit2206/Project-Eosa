package com.eosa.web.companys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="CompanysCategory")
public class CompanysCategory {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long companysCategoryIdx;

    @Column private Long companysIdx;
    @Column private String companysCategoryValue;

    @ManyToOne(targetEntity=Companys.class, fetch=FetchType.EAGER)
    private Companys companys;

}

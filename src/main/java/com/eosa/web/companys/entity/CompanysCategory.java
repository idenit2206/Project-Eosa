package com.eosa.web.companys.entity;

import javax.persistence.*;

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

//  version1
//  @ManyToOne(targetEntity=Companys.class, fetch=FetchType.LAZY)

//  version2
    @ManyToOne
    @JoinColumn(name="CompanysIdx")
    private Companys companys;

}

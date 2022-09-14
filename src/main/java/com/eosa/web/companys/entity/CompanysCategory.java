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

    @ManyToOne(targetEntity = Companys.class, fetch = FetchType.LAZY)
    private Companys companys;

}

package com.eosa.web.companys.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="CompanysFlagCategory")
public class CompanysFlagCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companysFlagCategoryIdx;
    @Column(nullable = false) private Long companysFlagIdx;
    @Column(nullable = false) private String companysFlagCategory;

}

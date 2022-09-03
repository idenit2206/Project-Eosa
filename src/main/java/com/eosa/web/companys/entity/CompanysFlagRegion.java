package com.eosa.web.companys.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="CompanysFlagRegion")
public class CompanysFlagRegion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companysFlagRegionIdx;
    @Column(nullable = false) private Long companysFlagIdx;
    @Column(nullable = false) private String companysFlagRegion1;
    @Column(nullable = true) private String companysFlagRegion2;

}

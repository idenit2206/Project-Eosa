package com.eosa.web.companys.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="CompanysPremium")
public class CompanysPremium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Column(nullable = false) private Long companysIdx;
    @Column private String companysName;
    @Column private String companysCeoName;
    @Column(nullable = false) private LocalDateTime premiumReqDate;
    @Column(nullable = false) private LocalDateTime premiumStartDate;
    @Column(nullable = false) private int companysPremiumEnabled;

}

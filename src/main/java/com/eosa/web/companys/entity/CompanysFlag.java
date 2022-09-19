package com.eosa.web.companys.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="CompanysFlag")
public class CompanysFlag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companysFlagIdx;
    @Column(nullable = true) private Long companysIdx;
    @Column(nullable = false) private String companysName;
    @Column(nullable = false) private String companysCeoName;
    @Column(nullable = false) private LocalDateTime flagReqDate;
    @Column(nullable = false) private LocalDateTime flagStartDate;

    @Transient private String flagRegion1;
    @Transient private String flagRegion2;
//    @Transient private String flagCategory;

    @Column(nullable = true) private Long flagPrice;
    @Column(nullable = true) private String flagPriceBank;
    @Column(nullable = false) private int companysFlagEnabled;

}

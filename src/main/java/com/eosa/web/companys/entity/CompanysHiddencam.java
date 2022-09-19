package com.eosa.web.companys.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="CompanysHiddencam")
public class CompanysHiddencam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companysHiddencamIdx;
    @Column private String companysHiddencamRegion1;
    @Column private String companysHiddencamRegion2;
    @Column private String companysHiddencamName;
    @Column private LocalDateTime companysHiddencamRequestDate;
    @Column private int companysHiddencamCheckStatus;

}

package com.eosa.admin.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CompanysHiddencamDTO {

    private Long companysHiddencamIdx;
    private String companysHiddencamRegion1;
    private String companysHiddencamRegion2;
    private String companysHiddencamName;
    private LocalDateTime companysHiddencamRequestDate;
    private int companysHiddencamCheckStatus;
    
}

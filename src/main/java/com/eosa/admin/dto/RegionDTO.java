package com.eosa.admin.dto;

import lombok.Data;

@Data
public class RegionDTO {
    
    private Long regionIdx;
    private String regionName;
    private int regionPrice;

    public RegionDTO(String regionName, int regionPrice) {
        this.regionName = regionName;
        this.regionPrice = regionPrice;
    }
    
    public RegionDTO(Long regionIdx, String regionName, int regionPrice) {
        this.regionIdx = regionIdx;
        this.regionName = regionName;
        this.regionPrice = regionPrice;
    }

}

package com.eosa.admin.dto;

import lombok.Data;

@Data
public class BannerDTO {

    private int idx;
    private String bannerTag;
    private String bannerFileName;
    private String bannerFileLink;
    private String bannerHref;

    public BannerDTO() {
    }

    public BannerDTO(int idx, String bannerTag, String bannerFileName, String bannerFileLink, String bannerHref) {
        this.idx = idx;
        this.bannerTag = bannerTag;
        this.bannerFileName = bannerFileName;
        this.bannerFileLink = bannerFileLink;
        this.bannerHref = bannerHref;
    }
    
}

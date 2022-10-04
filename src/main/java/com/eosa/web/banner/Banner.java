package com.eosa.web.banner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="AdminBannerManage")
public class Banner {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;
    @Column private String bannerTag;
    @Column private String bannerFileName;
    @Column private String bannerFileLink;
    @Column private String bannerHref;

}

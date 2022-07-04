package com.sherlockk.demo.mainbanner;

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
public class MainBanner {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;
    
    @Column(length=255)
    private String bannerImageLink;
    
    @Column(length=255)
    private String bannerFileLink;
    
}

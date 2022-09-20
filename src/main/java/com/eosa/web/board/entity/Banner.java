package com.eosa.web.board.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "Banner")
@Table(name = "AdminBannerManage")
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Column private String bannerTag;
    @Column private String bannerFileName;
    @Column private String bannerFileLink;

}

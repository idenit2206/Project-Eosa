package com.eosa.web.banner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/** 
 * 배너에 사용하는 Entity
 */
@Data
@Entity
@Table(name="AdminBannerManage")
public class Banner {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;
    @Column private String bannerTag;           // 메인페이지, 탐정검색 페이지 중 어느곳에 들어가는 배너인지 구분하는 값
    @Column private String bannerFileName;      // 배너파일 이름
    @Column private String bannerFileLink;      // S3에 업로드 된 배너파일의 URL
    @Column private String bannerHref;          

}

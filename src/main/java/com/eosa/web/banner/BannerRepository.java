package com.eosa.web.banner;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {
 
    /**
     * 메인 페이지에 사용할 모든 배너 Entity를 조회하는 레퍼지토리
     * @return
     */
    @Query(value="SELECT * FROM AdminBannerManage", nativeQuery = true)
    List<Banner> findAllMain();

    /**
     * 탐정찾기 페이지에 사용할 모든 배너 Entity를 찾는 레퍼지토리
     * @return
     */
    @Query(value="SELECT * FROM AdminDetectiveBannerManage", nativeQuery = true)
    List<Banner> findAllDetective();

}

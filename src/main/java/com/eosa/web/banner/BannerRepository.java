package com.eosa.web.banner;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {
 
    @Query(value="SELECT * FROM AdminBannerManage", nativeQuery = true)
    List<Banner> findAllMain();

    @Query(value="SELECT * FROM AdminDetectiveBannerManage", nativeQuery = true)
    List<Banner> findAllDetective();

}

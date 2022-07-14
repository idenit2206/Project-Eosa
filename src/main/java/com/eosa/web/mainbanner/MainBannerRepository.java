package com.eosa.web.mainbanner;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MainBannerRepository extends JpaRepository<MainBanner, Integer> {

    @Query(
        value="SELECT * FROM AdminBannerManage ORDER BY idx ASC LIMIT 5", 
        nativeQuery=true
    )
    List<MainBanner> findListFive();
    
}

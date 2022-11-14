package com.eosa.web.companys.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eosa.web.companys.entity.CompanysActiveRegion;

import java.util.List;

@Repository
public interface CompanysActiveRegionRepository extends JpaRepository<CompanysActiveRegion, Long> {

    /**
     * 새로운 companysActiveRegion 을 저장하는 레포지터리
     * @param entity
     */
    @Transactional
    @Modifying
    @Query(
        value="INSERT INTO CompanysActiveRegion " +
        "(companysIdx, activeRegion) " +
        "VALUES(:#{#CompanysActiveRegion.companysIdx}, :#{#CompanysActiveRegion.activeRegion})",
        nativeQuery=true
    )
    void insertCompanysActiveRegion(@Param(value="CompanysActiveRegion") CompanysActiveRegion entity);

    /**
     * companysIdx와 일치하는 activeRegion 값을 조회하는 레포지터리
     * @param companysIdx
     * @return
     */
    @Query(value="SELECT activeRegion FROM CompanysActiveRegion WHERE companysIdx = ?1", nativeQuery = true)
    List<String> selectByCompanysIdx(Long companysIdx);

    /**
     * companysIdx와 일치하는 CompanysActiveRegion 을 삭제하는 레포지터리
     * @param companysIdx
     * @return
     */
    @Transactional
    @Modifying
    @Query(value="DELETE FROM CompanysActiveRegion WHERE companysIdx = ?1", nativeQuery = true)
    int deleteActiveRegionByCompanysIdx(Long companysIdx);
    
}

package com.eosa.web.companys.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eosa.web.companys.entity.CompanysActiveRegion;

@Repository
public interface CompanysActiveRegionRepository extends JpaRepository<CompanysActiveRegion, Long> {

    @Transactional
    @Modifying
    @Query(
        value="INSERT INTO CompanysActiveRegion " +
        "(companysIdx, activeRegion) " +
        "VALUES(:#{#CompanysActiveRegion.companysIdx}, :#{#CompanysActiveRegion.activeRegion})",
        nativeQuery=true
    )
    void insertCompanysActiveRegion(@Param(value="CompanysActiveRegion") CompanysActiveRegion entity);
    
}

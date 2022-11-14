package com.eosa.web.companys.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eosa.web.companys.entity.CompanysLicense;

@Repository
public interface CompanysLicenseRepository extends JpaRepository<CompanysLicense, Long> {

    /**
     * CompanysLicense를 저장하는 레포지터리
     * @param entity
     */
    @Transactional
    @Modifying
    @Query(
        value="INSERT INTO CompanysLicense" + 
        "(companysIdx, companysLicenseName, companysLicenseValue, insertDate) " +
        "VALUES(:#{#CompanysLicense.companysIdx}, :#{#CompanysLicense.companysLicenseName}, " +
        ":#{#CompanysLicense.companysLicenseValue}, :#{#CompanysLicense.insertDate})",
        nativeQuery=true
    )
    void insertCompanysLicense(@Param("CompanysLicense") CompanysLicense entity);
}

package com.eosa.web.companys.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eosa.web.companys.entity.CompanysCategory;

@Repository
public interface CompanysCategoryRepository extends JpaRepository<CompanysCategory, Long>{
    
    @Transactional
    @Modifying
    @Query(
        value="INSERT INTO CompanysCategory " +
        "(companysIdx, companysCategoryValue) " +
        "VALUES(:#{#CompanysCategory.companysIdx}, :#{#CompanysCategory.companysCategoryValue})",
        nativeQuery=true
    )
    void insertCompanysCategory(@Param(value="CompanysCategory") CompanysCategory entity);

}

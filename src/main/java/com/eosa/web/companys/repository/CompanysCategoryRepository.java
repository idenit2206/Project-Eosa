package com.eosa.web.companys.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eosa.web.companys.entity.CompanysCategory;

import java.util.List;

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

    @Query(value="SELECT companysCategoryValue FROM CompanysCategory WHERE companysIdx = ?1", nativeQuery = true)
    List<String> selectByCompanysIdx(Long companysIdx);

    @Transactional
    @Modifying
    @Query(value="DELETE FROM CompanysCategory WHERE companysIdx = ?1", nativeQuery = true)
    int deleteCategoryByCompanysIdx(Long companysIdx);

}

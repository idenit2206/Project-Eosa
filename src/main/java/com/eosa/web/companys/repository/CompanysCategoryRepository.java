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
    
    /**
     * 새로운 CompanysCategory를 저장하는 레포지터리
     * @param entity
     */
    @Transactional
    @Modifying
    @Query(
        value="INSERT INTO CompanysCategory " +
        "(companysIdx, companysCategoryValue) " +
        "VALUES(:#{#CompanysCategory.companysIdx}, :#{#CompanysCategory.companysCategoryValue})",
        nativeQuery=true
    )
    void insertCompanysCategory(@Param(value="CompanysCategory") CompanysCategory entity);

    /**
     * companysIdx와 일치하는 companysCategoryValue를 출력하는 레포지터리
     * @param companysIdx
     * @return List
     */
    @Query(value="SELECT companysCategoryValue FROM CompanysCategory WHERE companysIdx = ?1", nativeQuery = true)
    List<String> selectByCompanysIdx(Long companysIdx);

    /**
     * companysIdx와 일치하는 CompanysCategory를 삭제하는 레포지터리
     * @param companysIdx
     * @return
     */
    @Transactional
    @Modifying
    @Query(value="DELETE FROM CompanysCategory WHERE companysIdx = ?1", nativeQuery = true)
    int deleteCategoryByCompanysIdx(Long companysIdx);

}

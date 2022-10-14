package com.eosa.web.requestform.repository.tablebackup;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eosa.web.requestform.entity.RequestFormCategory;

@Repository
public interface RequestFormCategoryBackupRepository extends JpaRepository<RequestFormCategory, Long> {
    
    @Transactional
    @Modifying
    @Query(
        value="INSERT INTO RequestFormCategoryBackup" + 
        "(requestFormIdx, requestFormCategoryValue) " +
        "VALUES(:#{#RequestFormCategory.requestFormIdx}, :#{#RequestFormCategory.requestFormCategoryValue})",
        nativeQuery=true
    )
    int insertRequestFormCategory(@Param("RequestFormCategory") RequestFormCategory entity);
}

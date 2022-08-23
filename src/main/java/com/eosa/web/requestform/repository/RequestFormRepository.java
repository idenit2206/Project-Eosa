package com.eosa.web.requestform.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eosa.web.requestform.entity.RequestForm;
import com.eosa.web.requestform.entity.SelectRequestFormList;

@Repository
public interface RequestFormRepository extends JpaRepository<RequestForm, Long> {

    @Modifying
    @Transactional
    @Query(value="INSERT INTO RequestForm(usersIdx, companysIdx, " +
        "requestFormRegion1, requestFormRegion2, requestFormStatus, requestFormDate)" +
        "VALUES(" +
        ":#{#RequestForm.usersIdx}, :#{#RequestForm.companysIdx}, " +
        ":#{#RequestForm.requestFormRegion1}, :#{#RequestForm.requestFormRegion2}," +
        ":#{#RequestForm.requestFormStatus}, :#{#RequestForm.requestFormDate})"
    ,nativeQuery=true)
    int requestFormRegister(@Param("RequestForm") RequestForm entity);

    @Query(
        value="SELECT * FROM RequestForm " + 
        "WHERE detectiveIdx = ?1 " +
        "ORDER BY requestFormDate ASC ",
        // "LIMIT ?2, ?3",
        nativeQuery=true
    )
    List<RequestForm> findByDetectiveIdx(Long detectiveidx);

    @Query(
        value="SELECT " + 
        "RequestForm.requestFormIdx, RequestForm.usersIdx, RequestForm.companysIdx, " +
        "RequestForm.requestFormRegion1, RequestForm.requestFormRegion2, " +
        "RequestForm.requestFormStatus, RequestForm.requestConsultDate, RequestForm.requestFormDate, " +
        "RequestForm.requestFormAcceptDate, RequestForm.requestFormCompDate, RequestForm.requestFormRejectMessage, " +
        "GROUP_CONCAT(RequestFormCategory.requestFormCategoryValue) AS requestFormCategory " +
        "FROM RequestForm INNER JOIN RequestFormCategory ON RequestForm.requestFormIdx = RequestFormCategory.requestFormIdx " +
        "GROUP BY RequestForm.RequestFormIdx",
        nativeQuery=true
    )
    List<SelectRequestFormList> selectAllRequestFormList();

    @Query(
        value="SELECT " + 
        "RequestForm.requestFormIdx, RequestForm.usersIdx, RequestForm.companysIdx, " +
        "RequestForm.requestFormRegion1, RequestForm.requestFormRegion2, " +
        "RequestForm.requestFormStatus, RequestForm.requestConsultDate, RequestForm.requestFormDate, " +
        "RequestForm.requestFormAcceptDate, RequestForm.requestFormCompDate, RequestForm.requestFormRejectMessage, " +
        "GROUP_CONCAT(RequestFormCategory.requestFormCategoryValue) AS requestFormCategory " +
        "FROM RequestForm INNER JOIN RequestFormCategory ON RequestForm.requestFormIdx = RequestFormCategory.requestFormIdx " +
        "WHERE usersIdx = ?1 " +
        "GROUP BY RequestForm.RequestFormIdx",
        nativeQuery=true
    )
    List<SelectRequestFormList> selectAllRequestFormListByUsersIdx(Long usersIdx);

}

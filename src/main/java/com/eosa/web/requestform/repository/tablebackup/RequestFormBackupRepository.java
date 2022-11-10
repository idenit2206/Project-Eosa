package com.eosa.web.requestform.repository.tablebackup;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eosa.web.requestform.entity.RequestFormBackup;
import com.eosa.web.requestform.entity.SelectRequestFormList;

@Repository
public interface RequestFormBackupRepository extends JpaRepository<RequestFormBackup, Long> {

    @Modifying
    @Transactional
    @Query(value="INSERT INTO RequestFormBackup(usersIdx, companysIdx, " +
        "requestFormRegion1, requestFormRegion2, requestFormStatus, requestFormDate)" +
        "VALUES(" +
        ":#{#RequestFormBackup.usersIdx}, :#{#RequestFormBackup.companysIdx}, " +
        ":#{#RequestFormBackup.requestFormRegion1}, :#{#RequestFormBackup.requestFormRegion2}," +
        ":#{#RequestFormBackup.requestFormStatus}, :#{#RequestFormBackup.requestFormDate})"
    ,nativeQuery=true)
    int requestFormRegister(@Param("RequestFormBackup") RequestFormBackup entity);

    @Query(
        value="SELECT " +
            "R.requestFormIdx, R.usersIdx, R.companysIdx, " +
            "R.requestFormRegion1, R.requestFormRegion2, " +
            "R.requestFormStatus, R.requestConsultDate, R.requestFormDate, " +
            "R.requestFormAcceptDate, R.requestFormCompDate, R.requestFormRejectMessage, " +
            "GROUP_CONCAT(RFC.requestFormCategoryValue) AS requestFormCategory " +
            "FROM RequestFormBackup R INNER JOIN RequestFormCategoryBackup RFC ON R.requestFormIdx = RFC.requestFormIdx " +
            "WHERE R.companysIdx = ?1 " +
            "GROUP BY R.RequestFormIdx",
        nativeQuery=true
    )
    List<SelectRequestFormList> findByCompanysIdxIdx(Long companysIdx);

    @Query(
        value=
        "SELECT " +
        "R.requestFormIdx, R.usersIdx, " +
        "U.usersAge, U.usersGender, R.companysIdx, " +
        "C.companysPremium, " +
        "GROUP_CONCAT(RFC.requestFormCategoryValue) AS RequestFormCategory, " +
        "R.requestFormRegion1, R.requestFormRegion2, " +
        "R.requestFormStatus, R.requestConsultDate, R.requestFormDate, " +
        "R.requestFormAcceptDate, R.requestFormCompDate, R.requestFormRejectMessage " +
        "FROM RequestFormBackup R " +
        "LEFT JOIN Users U ON U.usersIdx = R.usersIdx " +
        "LEFT JOIN Companys C on R.companysIdx = C.companysIdx " +
        "LEFT JOIN RequestFormCategoryBackup RFC ON R.requestFormBackupIdx = RFC.requestFormBackupIdx " +
        "GROUP BY " +
        "R.requestFormIdx, R.usersIdx, " +
        "U.usersAge, U.usersGender, R.companysIdx, " +
        "C.companysPremium, " +
        "R.requestFormRegion1, R.requestFormRegion2, " +
        "R.requestFormStatus, R.requestConsultDate, R.requestFormDate, " +
        "R.requestFormAcceptDate, R.requestFormCompDate, R.requestFormRejectMessage ",
        nativeQuery=true
    )
    List<SelectRequestFormList> selectAllRequestFormList();

    @Query(
        value="SELECT " + 
        "R.requestFormIdx, R.usersIdx, R.companysIdx, " +
        "R.requestFormRegion1, R.requestFormRegion2, " +
        "R.requestFormStatus, R.requestConsultDate, R.requestFormDate, " +
        "R.requestFormAcceptDate, R.requestFormCompDate, R.requestFormRejectMessage, " +
        "GROUP_CONCAT(RFC.requestFormCategoryValue) AS requestFormCategory " +
        "FROM RequestFormBackup R INNER JOIN RequestFormCategoryBackup RFC ON R.requestFormIdx = RFC.requestFormIdx " +
        "WHERE R.usersIdx = ?1 " +
        "GROUP BY R.RequestFormIdx",
        nativeQuery=true
    )
    List<SelectRequestFormList> selectAllRequestFormListByUsersIdx(Long usersIdx);

    @Query(
    value=
    "SELECT " +
    // -- R.requestFormBackupIdx, 
    "R.requestFormIdx, R.usersIdx, " +
    "R.companysIdx, C.companysName, C.companysPremium, " +
    "GROUP_CONCAT(RFC.requestFormCategoryValue SEPARATOR ',') AS RequestFormCategory, " +
    "R.requestFormRegion1, R.requestFormRegion2, " +
    "R.requestFormStatus, R.requestConsultDate, R.requestFormDate, " +
    "R.requestFormAcceptDate, R.requestFormCompDate, R.requestFormRejectMessage " +
    "FROM RequestFormBackup R " +
    "LEFT JOIN Users U ON R.usersIdx = U.usersIdx " +
    "LEFT JOIN Companys C ON R.companysIdx = C.companysIdx " +
    "LEFT JOIN RequestFormCategoryBackup RFC ON R.requestFormBackupIdx = RFC.requestFormBackupIdx " +
    "WHERE R.usersIdx = ?1 " +
    "GROUP BY " +
    "R.requestFormIdx, R.usersIdx, " +
    "R.companysIdx, " +
    "R.usersIdx, " +
    "C.companysName, C.companysPremium, " +
    "R.requestFormRegion1, R.requestFormRegion2, " +
    "R.requestFormStatus, R.requestConsultDate, R.requestFormDate, " +
    "R.requestFormAcceptDate, R.requestFormCompDate, R.requestFormRejectMessage " +
    "ORDER BY R.requestFormDate DESC",
    nativeQuery=true
    )
    List<SelectRequestFormList> selectAllRequestFormListByUsersIdxOrderByRequestFormDateDESC(Long usersIdx);

    @Query(value = "SELECT * FROM RequestFormBackup R WHERE R.requestFormIdx = ?1", nativeQuery = true)
    RequestFormBackup selectOneRequestFormByRequsetFormIdx(Long requestFormIdx);

    @Query(value =
        "SELECT R.requestFormIdx, R.usersIdx, R.companysIdx, R.requestFormRegion1, R.requestFormChannel, R.requestFormStatus, " +
        "R.requestFormStatusChangeDate, " +
        "R.requestFormDate, R.requestConsultDate, R.requestFormAcceptDate, R.requestFormCompDate, R.requestFormRejectMessage, " +
        "R.requestFormClientReadState, R.requestFormClientReadDate, " +
        "R.requestFormDetectiveReadState, R.requestFormDetectiveReadDate, " +
        "GROUP_CONCAT(RFC.requestFormCategoryValue) AS requestFormCategory " +
        "FROM RequestFormBackup R " +
        "LEFT JOIN RequestFormCategoryBackup RFC on R.requestFormIdx = RFC.requestFormIdx " +
        "LEFT JOIN Users U ON R.usersIdx = U.usersIdx " +
        // "WHERE R.usersIdx = ?1 AND R.requestFormClientReadState = 0 " +
        "WHERE U.usersIdx = ?1 " +
        "GROUP BY R.requestFormIdx, R.usersIdx, R.companysIdx, R.requestFormRegion1, R.requestFormChannel, R.requestFormStatus, R.requestFormDate, R.requestConsultDate, R.requestFormAcceptDate, R.requestFormCompDate, R.requestFormRejectMessage " +
        "ORDER BY R.requestFormStatusChangeDate DESC"
    ,nativeQuery = true)
    List<RequestFormBackup> selectRequestFormByUsersIdx(Long usersIdx);

    @Query(value =
        "SELECT R.requestFormIdx, R.usersIdx, R.companysIdx, R.requestFormRegion1, R.requestFormChannel, R.requestFormStatus, " +
        "R.requestFormStatusChangeDate, " +
        "R.requestFormDate, R.requestConsultDate, R.requestFormAcceptDate, R.requestFormCompDate, R.requestFormRejectMessage, " +
        "R.requestFormClientReadState, R.requestFormClientReadDate, " +
        "R.requestFormDetectiveReadState, R.requestFormDetectiveReadDate, " +
        "GROUP_CONCAT(RFC.requestFormCategoryValue) AS requestFormCategory " +
        "FROM RequestFormBackup R " +
        "LEFT JOIN RequestFormCategoryBackup RFC on R.requestFormIdx = RFC.requestFormIdx " +
        "WHERE R.companysIdx = ?1 " +
        "GROUP BY R.requestFormIdx, R.usersIdx, R.companysIdx, R.requestFormRegion1, R.requestFormChannel, R.requestFormStatus, R.requestFormDate, R.requestConsultDate, R.requestFormAcceptDate, R.requestFormCompDate, R.requestFormRejectMessage " +
        "ORDER BY R.requestFormStatusChangeDate DESC"
    ,nativeQuery = true)
    List<RequestFormBackup> selectPushNotificationForDetective(Long companysIdx);

    @Query(value =
        "SELECT R.requestFormIdx, R.usersIdx, R.companysIdx, R.requestFormRegion1, R.requestFormChannel, R.requestFormStatus, " +
        "R.requestFormDate, R.requestConsultDate, R.requestFormAcceptDate, R.requestFormCompDate, R.requestFormRejectMessage, " +
        "R.requestFormClientReadState, R.requestFormClientReadDate, " +
        "R.requestFormDetectiveReadState, R.requestFormDetectiveReadDate, " +
        "GROUP_CONCAT(RFC.requestFormCategoryValue) AS requestFormCategory " +
        "FROM RequestFormBackup R " +
        "LEFT JOIN RequestFormCategoryBackup RFC on R.requestFormIdx = RFC.requestFormIdx " +
        "WHERE R.companysIdx = ?1 " +
        "GROUP BY R.requestFormIdx, R.usersIdx, R.companysIdx, R.requestFormRegion1, R.requestFormChannel, R.requestFormStatus, R.requestFormDate, R.requestConsultDate, R.requestFormAcceptDate, R.requestFormCompDate, R.requestFormRejectMessage " +
        "ORDER BY R.requestFormClientReadDate DESC"
    ,nativeQuery = true)
    List<RequestFormBackup> selectRequestFormByCompanysIdx(Long companysIdx);

    @Transactional
    @Modifying
    @Query(value = 
        "UPDATE RequestFormBackup R " + 
        "SET R.requestFormClientReadState = 1, R.requestFormClientReadDate = now() " + 
        "WHERE R.requestFormIdx = ?1"
        ,nativeQuery = true
    )
    int updateReadStateRead(Long requestFormIdx);

    @Transactional
    @Modifying
    @Query(value = 
        "UPDATE RequestFormBackup R " + 
        "SET R.requestFormDetectiveReadState = 1, R.requestFormDetectiveReadDate = now() " + 
        "WHERE R.requestFormIdx = ?1 AND R.companysIdx = ?2"
        ,nativeQuery = true
    )
    int updateReadStateReadDetective(Long requestFormIdx, Long companysIdx);

    /**
     * CLIENT 회원의 의뢰 임무 계약서 작성을 위한 정보 업데이트 레포지터리(백업 데이터)
     * @param requestForm
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = 
        "UPDATE RequestFormBackup R " +
        "SET R.requestFormContractClientDelegate = :#{#RequestForm.requestFormContractClientDelegate}, " +
        "R.requestFormContractClientAddress = :#{#RequestForm.requestFormContractClientAddress}, " +
        "R.requestFormContractClientBirth = :#{#RequestForm.requestFormContractClientBirth}, " +
        "R.requestFormContractClientContact = :#{#RequestForm.requestFormContractClientContact} " +
        "WHERE R.requestFormIdx = :#{#RequestForm.requestFormIdx} ",
        nativeQuery = true
    )
    int updateRequestFormContractData(@Param("RequestForm") RequestFormBackup requestForm);

}

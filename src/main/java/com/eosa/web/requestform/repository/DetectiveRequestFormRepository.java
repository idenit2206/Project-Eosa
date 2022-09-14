package com.eosa.web.requestform.repository;

import com.eosa.web.requestform.entity.RequestForm;
import com.eosa.web.requestform.entity.SelectRequestFormList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DetectiveRequestFormRepository extends JpaRepository<RequestForm, Long> {

    @Query(
            value="SELECT " +
                    "RequestForm.requestFormIdx, RequestForm.usersIdx, Users.usersAccount, RequestForm.companysIdx, " +
                    "RequestForm.requestFormRegion1, RequestForm.requestFormRegion2, " +
                    "RequestForm.requestFormStatus, RequestForm.requestConsultDate, RequestForm.requestFormDate, " +
                    "RequestForm.requestFormAcceptDate, RequestForm.requestFormCompDate, RequestForm.requestFormRejectMessage, " +
                    "GROUP_CONCAT(RequestFormCategory.requestFormCategoryValue) AS requestFormCategory " +
                    "FROM RequestForm INNER JOIN RequestFormCategory ON RequestForm.requestFormIdx = RequestFormCategory.requestFormIdx " +
                    "INNER JOIN Users ON RequestForm.usersIdx = Users.usersIdx " +
                    "WHERE RequestForm.companysIdx = ?1 " +
                    "GROUP BY RequestForm.RequestFormIdx",
            nativeQuery=true
    )
    List<SelectRequestFormList> selectAllDetectiveRequestFormListByCompanysIdx(Long companysIdx);

    @Query(
            value="SELECT " +
                    "RequestForm.requestFormIdx, RequestForm.usersIdx, Users.usersAccount, Users.usersAge, Users.usersGender, RequestForm.companysIdx, " +
                    "RequestForm.requestFormRegion1, RequestForm.requestFormRegion2, " +
                    "RequestForm.requestFormStatus, RequestForm.requestConsultDate, RequestForm.requestFormDate, " +
                    "RequestForm.requestFormAcceptDate, RequestForm.requestFormCompDate, RequestForm.requestFormRejectMessage, " +
                    "GROUP_CONCAT(RequestFormCategory.requestFormCategoryValue) AS requestFormCategory " +
                    "FROM RequestForm INNER JOIN RequestFormCategory ON RequestForm.requestFormIdx = RequestFormCategory.requestFormIdx " +
                    "INNER JOIN Users ON RequestForm.usersIdx = Users.usersIdx " +
                    "WHERE RequestForm.companysIdx = ?1 " +
                    "GROUP BY RequestForm.RequestFormIdx " +
                    "ORDER BY RequestForm.requestFormDate DESC",
            nativeQuery=true
    )
    List<SelectRequestFormList> selectAllDetectiveRequestFormListByCompanysIdxOrderByDESC(Long companysIdx);

    @Query(
            value="SELECT " +
                    "RequestForm.requestFormIdx, RequestForm.usersIdx, Users.usersAccount, Users.usersAge, Users.usersGender, RequestForm.companysIdx, " +
                    "RequestForm.requestFormRegion1, RequestForm.requestFormRegion2, " +
                    "RequestForm.requestFormStatus, RequestForm.requestConsultDate, RequestForm.requestFormDate, " +
                    "RequestForm.requestFormAcceptDate, RequestForm.requestFormCompDate, RequestForm.requestFormRejectMessage, " +
                    "GROUP_CONCAT(RequestFormCategory.requestFormCategoryValue) AS requestFormCategory " +
                    "FROM RequestForm INNER JOIN RequestFormCategory ON RequestForm.requestFormIdx = RequestFormCategory.requestFormIdx " +
                    "INNER JOIN Users ON RequestForm.usersIdx = Users.usersIdx " +
                    "WHERE RequestForm.companysIdx = ?1 " +
                    "GROUP BY RequestForm.RequestFormIdx " +
                    "ORDER BY RequestForm.requestFormDate ASC",
            nativeQuery=true
    )
    List<SelectRequestFormList> selectAllDetectiveRequestFormListByCompanysIdxOrderByASC(Long companysIdx);

    @Query(value=
            "SELECT * FROM RequestForm " +
            "WHERE requestFormIdx = ?1",
            nativeQuery = true
    )
    RequestForm selectDetectiveRequestFormInfoByRequestFormIdx(Long requestFormIdx);

    @Transactional
    @Modifying
    @Query(value=
        "UPDATE RequestForm " +
        "SET " +
        "requestFormStatus = :requestFormStatus, requestFormRejectMessage = :requestFormRejectMessage " +
        "WHERE requestFormIdx = :requestFormIdx",
         nativeQuery = true
    )
    int updateRequestFormStatusByRequestFormIdx(
        @Param("requestFormIdx") Long requestFormIdx,
        @Param("requestFormStatus") String requestFormStatus,
        @Param("requestFormRejectMessage") String requestFormRejectMessage
    );

    @Transactional
    @Modifying
    @Query(value=
        "UPDATE RequestForm " +
        "SET " +
        "requestConsultDate = :requestFormAcceptDate, " +
        "requestFormStatus = :requestFormStatus, requestFormRejectMessage = :requestFormRejectMessage " +
        "WHERE requestFormIdx = :requestFormIdx",
        nativeQuery = true
    )
    int updateRequestFormStatusByRequestFormIdxCaseConsultComplete(
            @Param("requestFormIdx") Long requestFormIdx,
            @Param("requestFormAcceptDate") LocalDateTime requestFormAcceptDate,
            @Param("requestFormStatus") String requestFormStatus,
            @Param("requestFormRejectMessage") String requestFormRejectMessage
    );

    @Transactional
    @Modifying
    @Query(value=
            "UPDATE RequestForm " +
                    "SET " +
                    "requestFormCompDate = :requestFormAcceptDate, " +
                    "requestFormStatus = :requestFormStatus, requestFormRejectMessage = :requestFormRejectMessage " +
                    "WHERE requestFormIdx = :requestFormIdx",
            nativeQuery = true
    )
    int updateRequestFormStatusByRequestFormIdxCaseMissionComplete(
            @Param("requestFormIdx") Long requestFormIdx,
            @Param("requestFormAcceptDate") LocalDateTime requestCompAcceptDate,
            @Param("requestFormStatus") String requestFormStatus,
            @Param("requestFormRejectMessage") String requestFormRejectMessage
    );
}

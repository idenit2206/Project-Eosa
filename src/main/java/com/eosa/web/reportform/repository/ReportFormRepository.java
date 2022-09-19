package com.eosa.web.reportform.repository;

import com.eosa.web.reportform.entity.ReportForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ReportFormRepository extends JpaRepository<ReportForm, Long> {

    @Transactional
    @Modifying
    @Query(value=
        "UPDATE ReportForm R " +
        "SET R.reportCheckDate = :#{#ReportForm.reportCheckDate}, " +
        "R.reportCheckState = :#{#ReportForm.reportCheckState} " +
        "WHERE R.idx = :#{#ReportForm.idx}",
        nativeQuery = true
    )
    int updateReportFormStatus(@Param("ReportForm") ReportForm entity);

}

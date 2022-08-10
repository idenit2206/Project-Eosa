package com.eosa.web.requestform;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestFormRepository extends JpaRepository<RequestForm, Long> {

    @Modifying
    @Transactional
    @Query(value="INSERT INTO RequestForm(usersIdx, detectiveIdx, requestFormCategory," +
        "requestFormRegion1, requestFormRegion2, requestFormStatus, requestFormDate)" +
        "VALUES(" +
            "(SELECT Users.usersIdx FROM Users WHERE Users.usersIdx=:#{#RequestForm.usersIdx})," +
            "(SELECT Companys.companysCeoIdx FROM Companys WHERE Companys.companysCeoIdx=:#{#RequestForm.detectiveIdx})," +
            ":#{#RequestForm.requestFormCategory}, :#{#RequestForm.requestFormRegion1}, :#{#RequestForm.requestFormRegion2}," +
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

}

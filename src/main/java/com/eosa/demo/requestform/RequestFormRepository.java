package com.eosa.demo.requestform;

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
    @Query(value="INSERT INTO RequestForm(usersIdx, companysIdx, requestFormCategory," +
        "requestFormRegion1, requestFormRegion2, requestFormStatus, requestFormDate)" +
        "VALUES(" +
            "(SELECT Users.usersIdx FROM Users WHERE Users.usersIdx=:#{#RequestForm.usersIdx})," +
            "(SELECT Companys.companysIdx FROM Companys WHERE Companys.companysIdx=:#{#RequestForm.companysIdx})," +
            ":#{#RequestForm.requestFormCategory}, :#{#RequestForm.requestFormRegion1}, :#{#RequestForm.requestFormRegion2}," +
            ":#{#RequestForm.requestFormStatus}, :#{#RequestForm.requestFormDate})"
    ,nativeQuery=true)
    int requestFormSave(@Param("RequestForm") RequestForm entity);

}

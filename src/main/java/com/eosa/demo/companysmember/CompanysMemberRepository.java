package com.eosa.demo.companysmember;

import java.sql.SQLException;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanysMemberRepository extends JpaRepository<CompanysMember, Long> {

    @Modifying
    @Transactional
    @Query(value="INSERT INTO CompanysMember(usersIdx, companysIdx, registDate)" +
    "VALUES(" +
        "(SELECT Users.usersIdx FROM Users WHERE Users.usersIdx = :#{#CompanysMember.usersIdx} AND Users.usersRole = 'DETECTIVE'),"+
        "(SELECT Companys.companysIdx FROM Companys WHERE Companys.companysIdx = :#{#CompanysMember.companysIdx})," +
        "NOW())",     
    nativeQuery=true)
    // @Query(value="SELECT COUNT('*') FROM CompanysMember", nativeQuery=true)
    int customValdSave(@Param("CompanysMember") CompanysMember entity) throws SQLException;

    @Modifying
    @Transactional
    @Query(
        value="UPDATE CompanysMember " +
        "SET statusValue=0 " +
        "WHERE usersIdx=:usersIdx AND companysIdx=:companysIdx"
    )
    int deleteDetective(@Param("usersIdx") Long usersIdx, @Param("companysIdx") Long companysIdx);

}

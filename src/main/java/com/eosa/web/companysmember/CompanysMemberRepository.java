package com.eosa.web.companysmember;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eosa.web.users.FindByUsersAccount;

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

    @Query(
        value="SELECT usersAccount, usersName, usersNick, usersPhone, " +
            "usersEmail, usersRole, usersAge, " +
            "usersRegion1, usersRegion2, usersGender " +
            "FROM Users WHERE usersIdx = (" +
                "SELECT usersIdx FROM CompanysMember WHERE companysIdx=?1)"
        ,nativeQuery=true
    )
    FindByUsersAccount selectDetectiveInCompany(Long companysIdx);

    @Modifying
    @Transactional
    @Query(
        value="UPDATE CompanysMember " +
        "SET statusValue=0 " +
        "WHERE usersIdx=:usersIdx AND companysIdx=:companysIdx"
    )
    int deleteDetective(@Param("usersIdx") Long usersIdx, @Param("companysIdx") Long companysIdx);   

}

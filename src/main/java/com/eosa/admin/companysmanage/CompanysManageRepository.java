package com.eosa.admin.companysmanage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eosa.web.companys.Companys;

@Repository
public interface CompanysManageRepository extends JpaRepository<Companys, Long> {

    @Query(
        value="SELECT * FROM Companys WHERE " +
        "companysEnabled=1 AND companysDelete=0 " +  
        "ORDER BY companysIdx DESC LIMIT ?1, ?2",
        nativeQuery=true
    )
    List<Companys> findAllDetective(int currentStartPost, int postCount);

    @Query(
        value="SELECT COUNT(*) FROM Companys WHERE " +
        "companysEnabled=1 AND companysDelete=0",
        nativeQuery=true
    )
    int findAllDetectiveCount();

    @Query(
        value="SELECT * FROM Companys WHERE companysCeoAccount LIKE CONCAT('%',?1,'%') " +
        "LIMIT ?2, ?3",
        nativeQuery=true
    )
    List<Companys> findByCompanysCeoAccount(String companysCeoAccount, int currentPageStartPost, int POST_COUNT);

    @Query(
        value="SELECT COUNT(*) FROM Companys WHERE companysCeoAccount LIKE CONCAT('%',?1,'%')",
        nativeQuery=true
    )
    int findByCompanysCeoAccountCount(String companysCeoAccount);

    @Query(
        // value="SELECT " + 
        // "companysIdx, companysCeoIdx, companysName, " +
        // "companysRegion1, companysRegion2,companysRegion3, " +
        // "companysComment, companysSpec, companysEnabled" +
        // "FROM Companys " +
        // "WHERE Companys.companysName=?1",
        value="SELECT * FROM Companys WHERE companysName=?1",
        nativeQuery=true
    )
    GetByCompanysName getByCompanysName(@Param("Companys") String companysName);

    @Query(
        value="SELECT usersName, usersPhone FROM Users INNER JOIN Companys WHERE usersIdx=("+
        "SELECT companysCeoIdx FROM Companys WHERE companysName=?1)",
        nativeQuery = true
    )
    GetUserNamePhone getUserNamePhone(String companysName);

}

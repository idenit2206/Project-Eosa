package com.eosa.admin.companysmanager;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eosa.web.companys.Companys;

@Repository
public interface CompanysManagerRepository extends JpaRepository<Companys, Long> {

    @Query(
        value="SELECT * FROM Companys WHERE " +
        "companysEnabled=1 AND companysDelete=0 " +  
        "ORDER BY companysIdx DESC LIMIT ?1, ?2",
        nativeQuery=true
    )
    List<Companys> findAllCompany(int currentStartPost, int postCount);

    @Query(
        value="SELECT Companys.companysIdx, Companys.companysName, " + 
        "Users.usersName, Companys.companysPhone, " +
        "Companys.companysRegion1, Companys.companysEnabled, Companys.companysPremium, " +
        "Companys.companysLocalPremium, Companys.companysRegistDate " +
        "FROM Companys INNER JOIN Users ON Companys.companysCeoIdx = Users.usersIdx",
        nativeQuery = true
    )
    List<GetCompanysList> viewFindAll();

}

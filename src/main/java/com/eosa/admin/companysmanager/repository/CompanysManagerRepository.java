package com.eosa.admin.companysmanager.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eosa.admin.companysmanager.entity.GetCompanysList;
import com.eosa.web.companys.entity.Companys;

@Repository
public interface CompanysManagerRepository extends JpaRepository<Companys, Long> {

    @Query(
        value="SELECT companysIdx FROM Companys WHERE companysCeoIdx=?1",
        nativeQuery=true
    )
    Long findCompanysIdxByCeoIdx(Long companysCeoIdx);

    @Modifying
    @Transactional
    @Query(
        value="INSERT INTO CompanysCategory(companysIdx, companysCategoryValue) " +
        "VALUES(?1, ?2)",
        nativeQuery=true
    )
    void insertCompanysCategory(Long companysIdx, String string);

    @Modifying
    @Transactional
    @Query(
        value="INSERT INTO CompanysActiveRegion(companysIdx, activeRegion) " +
        "VALUES(?1, ?2)",
        nativeQuery=true
    )
    void insertCompanysActiveRegion(Long companysIdx, String string);

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

    @Query(
        value="SELECT * FROM Companys " +
        "WHERE companysIdx = ?1",
        nativeQuery=true
    )
    Companys findByCompanysIdx(Long companysIdx);

}

package com.eosa.web.companys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eosa.web.companys.entity.CompanysMember;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CompanysMemberRepository extends JpaRepository<CompanysMember, Long> {

    @Transactional
    @Modifying
    @Query(value="INSERT INTO CompanysMember" +
            "(usersIdx, companysIdx, statusValue, registDate) " +
            "VALUES(:#{#CompanysMember.usersIdx}, :#{#CompanysMember.companysIdx}, :#{#CompanysMember.statusValue}, :#{#CompanysMember.registDate})",
            nativeQuery = true
    )
    int insertCompanysMember(@Param("CompanysMember")CompanysMember entity);
    
}

package com.eosa.web.companys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eosa.web.companys.entity.CompanysMember;

@Repository
public interface CompanysMemberRepository extends JpaRepository<CompanysMember, Long> {
    
}

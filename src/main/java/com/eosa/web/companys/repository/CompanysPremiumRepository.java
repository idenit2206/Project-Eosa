package com.eosa.web.companys.repository;

import com.eosa.web.companys.entity.CompanysPremium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanysPremiumRepository extends JpaRepository<CompanysPremium, Long> {
}

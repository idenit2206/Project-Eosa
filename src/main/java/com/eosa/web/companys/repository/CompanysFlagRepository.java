package com.eosa.web.companys.repository;

import com.eosa.web.companys.entity.CompanysFlag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanysFlagRepository extends JpaRepository<CompanysFlag, Long> {
}

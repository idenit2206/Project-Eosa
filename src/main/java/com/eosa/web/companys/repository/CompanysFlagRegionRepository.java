package com.eosa.web.companys.repository;

import com.eosa.web.companys.entity.CompanysFlagRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanysFlagRegionRepository extends JpaRepository<CompanysFlagRegion, Long> {
}

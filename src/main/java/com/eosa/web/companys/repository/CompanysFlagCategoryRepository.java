package com.eosa.web.companys.repository;

import com.eosa.web.companys.entity.CompanysFlagCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanysFlagCategoryRepository extends JpaRepository<CompanysFlagCategory, Long> {
}

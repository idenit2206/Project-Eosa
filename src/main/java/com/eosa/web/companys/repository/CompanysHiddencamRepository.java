package com.eosa.web.companys.repository;

import com.eosa.web.companys.entity.CompanysHiddencam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanysHiddencamRepository extends JpaRepository<CompanysHiddencam, Long> {
}

package com.eosa.web.companys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanysRepository extends JpaRepository<Companys, Long> {

    @Query(
        value="SELECT 1 FROM Companys WHERE companysIdx = ?1",
        nativeQuery=true
    )
    int findByCompanysIdx(Long companysIdx);

}
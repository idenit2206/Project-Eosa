package com.eosa.admin.companysmanage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eosa.web.companys.Companys;

@Repository
public interface CompanysManageRepository extends JpaRepository<Companys, Long> {

    @Query(
        value="SELECT * FROM Companys WHERE " +
        "companysEnabled=1 AND companysDelete=0 " +  
        "ORDER BY companysIdx DESC LIMIT ?1, ?2",
        nativeQuery=true
    )
    List<Companys> findAllDetective(int currentStartPost, int postCount);

    @Query(
        value="SELECT COUNT(*) FROM Companys WHERE " +
        "companysEnabled=1 AND companysDelete=0",
        nativeQuery=true
    )
    int findAllDetectiveCount();

}

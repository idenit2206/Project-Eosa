package com.eosa.admin.requestmanage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eosa.web.requestform.entity.RequestForm;

@Repository
public interface RequestFormManageRepository extends JpaRepository<RequestForm, Long> {

    @Query(value="SELECT * FROM RequestForm WHERE requestFormRegion1 LIKE CONCAT('%',?1,'%')", nativeQuery = true)
    List<RequestForm> testRequestFormByLocation(String keyword);
    
}

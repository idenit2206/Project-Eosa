package com.eosa.web.requestcontract.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eosa.web.requestcontract.entity.RequestContract;

@Repository
public interface RequestContractRepository extends JpaRepository<RequestContract, Long> {
    
}

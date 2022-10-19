package com.eosa.web.requestcontract.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eosa.web.requestcontract.entity.RequestContract;

@Repository
public interface RequestContractRepository extends JpaRepository<RequestContract, Long> {

    @Query(value=
        "SELECT * FROM RequestContract R WHERE R.requestFormIdx = ?1", nativeQuery = true
    )
    RequestContract selectRequestContractByRequestFormIdx(Long requestFormIdx);

    @Transactional
    @Modifying
    @Query(value = 
        "UPDATE RequestContract " +
        "SET requestContractContractId = :requestContractContractId, " +
        "requestContractTurn = 1 " +
        "WHERE requestFormIdx = :requestFormIdx"
    ,nativeQuery = true)
    int updateRequestContract(
        @Param("requestFormIdx") Long requestFormIdx,
        @Param("requestContractContractId") String requestContractContractId        
    );
}

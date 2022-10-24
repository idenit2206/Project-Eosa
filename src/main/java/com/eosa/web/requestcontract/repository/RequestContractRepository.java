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

    /**
     * requestFormIdx 가 일치하는 RequestContract를 조회하는 레포지터리
     * @param requestFormIdx
     * @return
     */
    @Query(value=
        "SELECT * FROM RequestContract R WHERE R.requestFormIdx = ?1", nativeQuery = true
    )
    RequestContract selectRequestContractByRequestFormIdx(Long requestFormIdx);

    /**
     * requestFromIdx 가 일치하는 RequestContract를 수정하는 레포지터리
     * @param requestFormIdx
     * @param requestContractContractId
     * @param companysIdx
     * @param usersIdx
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = 
        "UPDATE RequestContract " +
        "SET requestContractContractId = :requestContractContractId, " +
        "companysIdx = :companysIdx, " +
        "usersIdx = :usersIdx, " +
        "requestContractTurn = 1 " +
        "WHERE requestFormIdx = :requestFormIdx"
    ,nativeQuery = true)
    int updateRequestContract(
        @Param("requestFormIdx") Long requestFormIdx,
        @Param("requestContractContractId") String requestContractContractId,
        @Param("companysIdx") String companysIdx,
        @Param("usersIdx") String usersIdx       
    );


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM RequestContract r WHERE r.requestFormIdx = ?1", nativeQuery = true)
    int deleteByRequestFormIdx(Long requestFormIdx);
}

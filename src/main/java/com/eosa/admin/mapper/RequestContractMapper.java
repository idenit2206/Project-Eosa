package com.eosa.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.eosa.admin.dto.RequestContractDTO;

@Mapper
public interface RequestContractMapper {
    
    /**
     * requestFormIdx와 일치하는 RequestContract를 조회하는 매퍼
     * @param requestFormIdx
     * @return
     */
    RequestContractDTO selectRequestContract(Long requestFormIdx);

}

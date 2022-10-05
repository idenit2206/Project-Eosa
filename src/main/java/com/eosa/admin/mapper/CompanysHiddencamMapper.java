package com.eosa.admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.eosa.admin.dto.CompanysHiddencamDTO;

@Mapper
public interface CompanysHiddencamMapper {

    int countCompanysHiddencamList();

    List<CompanysHiddencamDTO> selectCompanysHiddencamList(Map<String, Object> map);

    CompanysHiddencamDTO selectCompanysHiddencamDetails(long companysHiddencamIdx);

    int deleteHiddencamList(Long companysHiddencamIdx);
    
}

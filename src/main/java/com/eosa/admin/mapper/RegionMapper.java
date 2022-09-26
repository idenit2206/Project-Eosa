package com.eosa.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.eosa.admin.dto.RegionDTO;

@Mapper
public interface RegionMapper {

    List<RegionDTO> selectRegion();

    int priceUpdateRegion(RegionDTO regionDTO);

    int deleteRegion(Long regionIdx);

}

package com.eosa.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.eosa.admin.dto.PriceDTO;

@Mapper
public interface PriceMapper {

    PriceDTO selectPrice();

    int updatePrice(PriceDTO priceDTO);

}

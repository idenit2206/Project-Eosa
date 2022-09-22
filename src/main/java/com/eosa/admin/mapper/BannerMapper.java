package com.eosa.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.eosa.admin.dto.BannerDTO;

@Mapper
public interface BannerMapper {

    int bannerUpdate(BannerDTO bannerDTO);

    int bannerTruncate();

    List<BannerDTO> bannerList();

}

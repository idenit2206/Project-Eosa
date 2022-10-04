package com.eosa.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.eosa.admin.dto.BannerDTO;

@Mapper
public interface BannerMapper {

    int mainBannerDelete();

    int bannerUpdate(BannerDTO bannerDTO);    

    List<BannerDTO> bannerList();

    int detectiveBannerDelete();

    int detectiveBannerUpdate(BannerDTO bannerDTO);

    List<BannerDTO> detectivePageBannerList();

}

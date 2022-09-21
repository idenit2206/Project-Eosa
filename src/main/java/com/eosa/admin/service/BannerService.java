package com.eosa.admin.service;

import com.eosa.admin.dto.BannerDTO;
import com.eosa.admin.mapper.BannerMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannerService {

    @Autowired private BannerMapper bannerMapper;

    public int insertBanner(BannerDTO bannerDTO) {
        return bannerMapper.insertBanner(bannerDTO);
    }

    public List<BannerDTO> bannerList() {
        return bannerMapper.bannerList();
    }

}

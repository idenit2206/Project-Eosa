package com.eosa.admin.service;

import com.eosa.admin.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannerService {

    @Autowired private BannerMapper bannerMapper;

}

package com.eosa.web.board.controller;

import com.eosa.web.board.entity.Banner;
import com.eosa.web.board.service.BannerWebService;
import com.eosa.web.util.CustomResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/banner")
public class BannerWebController {

    @Autowired private BannerWebService bannerWebService;

    @GetMapping("/selectMainBanner")
    public CustomResponseData selectMainBanner() {
        CustomResponseData result = new CustomResponseData();
        List<Banner> items = bannerWebService.selectAllMainBanner();

        if(items != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(items);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(items);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

}

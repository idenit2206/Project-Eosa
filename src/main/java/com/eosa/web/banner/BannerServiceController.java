package com.eosa.web.banner;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eosa.web.util.CustomResponseData;

@RestController
@RequestMapping("/api/banner")
public class BannerServiceController {

    @Autowired private BannerServiceService bannerService;

    
    /** 
     * 메인 페이지에 사용할 모든 배너 Entity를 조회하는 컨트롤러
     * @return CustomResponseData
     */
    @GetMapping("/selectAllBanner")
    public CustomResponseData selectAllBanner() {
        CustomResponseData result = new CustomResponseData();
        List<Banner> items = bannerService.findAllMain();

        if(items != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(items);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    
    /** 
     * 탐정찾기 페이지에 사용할 모든 배너 Entity를 찾는 레퍼지토리
     * @return CustomResponseData
     */
    @GetMapping("/selectAllDetectiveBanner")
    public CustomResponseData selectAllDetectiveBanner() {
        CustomResponseData result = new CustomResponseData();
        List<Banner> items = bannerService.findAllDetective();

        if(items != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(items);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }
    
}

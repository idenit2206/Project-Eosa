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

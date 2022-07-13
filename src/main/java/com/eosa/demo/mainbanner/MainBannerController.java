package com.eosa.demo.mainbanner;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eosa.demo.util.CustomResponseData;

@RestController
@RequestMapping(value="/api/main")
public class MainBannerController {

    @Autowired
    private MainBannerService mainBannerService;
    
    @GetMapping(value="/bannerlist")
    public CustomResponseData MainBanner() {
        CustomResponseData result = new CustomResponseData();        
        LocalDateTime currentTime = LocalDateTime.now();

        int code = HttpStatus.OK.value();
        Map<String, List<MainBanner>> item = new HashMap<>();

        List<MainBanner> list = mainBannerService.findListFive();
        item.put("list", list);
        
        result.setStatusCode(code);
        result.setResultItem(item);
        result.setResponseDateTime(currentTime); 

        return result;
    }

    @PostMapping(value="/registbanner")
    public CustomResponseData RegistBanner(MainBanner param) {
        CustomResponseData result = new CustomResponseData();        
        LocalDateTime currentTime = LocalDateTime.now();

        int code = HttpStatus.FAILED_DEPENDENCY.value();
        Map<String, String> item = new HashMap<>();

        MainBanner transaction = mainBannerService.save(param);

        item.put("result", Integer.toString(transaction.getIdx()));

        result.setStatusCode(code);
        result.setResultItem(item);
        result.setResponseDateTime(currentTime);
        
        return result;
    }

}

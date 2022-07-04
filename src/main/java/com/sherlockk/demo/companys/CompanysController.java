package com.sherlockk.demo.companys;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sherlockk.demo.util.CustomResponseData;

@RestController
@RequestMapping(value="/api/companys")
public class CompanysController {

    @Autowired
    private CompanysService companysService; 
    
    @PostMapping("/registcompanys")
    public CustomResponseData RegistCompanys(
      Companys param  
    ) {
        CustomResponseData result = new CustomResponseData();
        LocalDateTime currentTime = LocalDateTime.now();
        int code = HttpStatus.FAILED_DEPENDENCY.value();
        Map<String, String> items = new HashMap<>();

        Companys transaction = companysService.save(param);
        items.put("item", transaction.getCompanysName());

        result.setStatusCode(code);
        result.setResultItem(items);
        result.setResponseDateTime(currentTime);

        return result;
    }

}

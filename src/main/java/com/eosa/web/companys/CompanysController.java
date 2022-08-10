package com.eosa.web.companys;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eosa.web.util.CustomResponseData;
import com.eosa.web.util.NullCheck;

@RestController
@RequestMapping(value="/api/companys")
public class CompanysController {

    private Logger logger = LoggerFactory.getLogger(CompanysController.class);

    @Autowired
    private CompanysService companysService; 
    
    /**
     * 탐정으로 등록하는 메서드입니다.
     * @param param
     * @return
     */
    @PostMapping("/registCompanys")
    public CustomResponseData RegistCompanys(
      Companys param  
    ) {
        CustomResponseData result = new CustomResponseData();
        LocalDateTime currentTime = LocalDateTime.now();       

        String[] targets = {
          "companysCeoAccount", "companysRegion1", 
          "companysRegion2", "companysRegion3", "companysRegistCerti"
        };

        NullCheck nullcheck = new NullCheck();
        Map<String, Object> items = nullcheck.ObjectNullCheck(param, targets);

        if(items.get("result") == "SUCCESS") {          
          Companys transaction = companysService.save(param);
          if(transaction != null) {
            logger.info("[SUCCESS] {} register Success", param.getCompanysCeoAccount());
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(items);
            result.setResponseDateTime(currentTime);
          }
          else {
            result.setStatusCode(HttpStatus.BAD_REQUEST.value());
            result.setResultItem("SQL ERROR /registcompany");
            result.setResponseDateTime(currentTime);
          }         
        }
        else {
          logger.error("[Failure] {} register Fail", param.getCompanysCeoAccount());
          result.setStatusCode(HttpStatus.BAD_REQUEST.value());
          result.setResultItem(items);
          result.setResponseDateTime(currentTime);
        }        

        return result;
    }

    
    @GetMapping("/selectAllCategory")
    public CustomResponseData selectAllCategory() {
      CustomResponseData result = new CustomResponseData();

      List<String> items = companysService.selectAllCategory();

      return result;
    }

}

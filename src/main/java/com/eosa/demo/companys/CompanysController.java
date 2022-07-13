package com.eosa.demo.companys;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eosa.demo.util.CustomResponseData;
import com.eosa.demo.util.NullCheck;

@RestController
@RequestMapping(value="/api/companys")
public class CompanysController {

    private Logger logger = LoggerFactory.getLogger(CompanysController.class);

    @Autowired
    private CompanysService companysService; 
    
    @PostMapping("/registcompanys")
    public CustomResponseData RegistCompanys(
      Companys param  
    ) {
        CustomResponseData result = new CustomResponseData();
        LocalDateTime currentTime = LocalDateTime.now();       

        String[] targets = {
          "companysName", "companysCeo", "companysRegion1", 
          "companysRegion2", "companysRegion3", "companysRegistCerti"
        };

        NullCheck nullcheck = new NullCheck();
        Map<String, Object> items = nullcheck.ObjectNullCheck(param, targets);

        if(items.get("result") == "SUCCESS") {          
          Companys transaction = companysService.save(param);
          if(transaction != null) {
            logger.info("Success " + param.getCompanysName() + " has Registed");
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
          logger.error("Failure " + param.getCompanysName() + " has Registed");
          result.setStatusCode(HttpStatus.BAD_REQUEST.value());
          result.setResultItem(items);
          result.setResponseDateTime(currentTime);
        }        

        return result;
    }

}

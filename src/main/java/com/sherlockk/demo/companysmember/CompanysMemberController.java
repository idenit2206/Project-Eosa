package com.sherlockk.demo.companysmember;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sherlockk.demo.util.CustomResponseData;
import com.sherlockk.demo.util.NullCheck;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/companysmember")
public class CompanysMemberController {

    private Logger logger = LoggerFactory.getLogger(CompanysMemberController.class);
    private NullCheck nullCheck = new NullCheck();

    @Autowired
    private CompanysMemberService companysMemberService;

    /**
     * @param CompanysMember 업체에 등록할 회원의 색인번호화 참가할 업체의 색인번호로 구성
     */
    @Operation(summary="탐정회원 업체 등록", description="탐정회원을 업체에 등록합니다.")
    @PostMapping("/registdetective")
    public CustomResponseData RegistDetective(
        CompanysMember param
    ) {
        CustomResponseData result = new CustomResponseData();        
        LocalDateTime currentTime = LocalDateTime.now();        
        
        String[] targets = { "usersIdx", "companysIdx" };

        Map<String, Object> items = nullCheck.ObjectNullCheck(param, targets);
       
        if(items.get("result") == "SUCCESS") {
            int transaction = companysMemberService.customValdSave(param);
            if(transaction == 1) {
                logger.info("Success " + param.getUsersIdx() + " Detective join into " + param.getCompanysIdx());
                result.setStatusCode(HttpStatus.OK.value());
                result.setResultItem(items);
                result.setResponseDateTime(currentTime);
            }
            else {
                result.setStatusCode(HttpStatus.BAD_REQUEST.value());
                result.setResultItem("SQL ERROR /registdetective");
                result.setResponseDateTime(currentTime);
            }
        }
        else {
            logger.error("Failure " + param.getUsersIdx() + " Detective join into " + param.getCompanysIdx());
            result.setStatusCode(HttpStatus.BAD_REQUEST.value());
            result.setResultItem(items);
            result.setResponseDateTime(currentTime);
        }
        return result;
    }
    
    @PutMapping("/deleteDetective")
    public CustomResponseData deleteDetective(
        @RequestParam("usersIdx") Long usersIdx,
        @RequestParam("companysIdx") Long companysIdx
    ) {
        CustomResponseData result = new CustomResponseData();
        return result;
    }
    
}

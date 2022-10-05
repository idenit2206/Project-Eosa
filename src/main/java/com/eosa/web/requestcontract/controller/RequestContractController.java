package com.eosa.web.requestcontract.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eosa.web.companys.entity.Companys;
import com.eosa.web.companys.entity.SelectAllCompanysList;
import com.eosa.web.companys.service.CompanysService;
import com.eosa.web.requestcontract.entity.RequestContract;
import com.eosa.web.requestcontract.service.RequestContractService;
import com.eosa.web.requestform.entity.RequestForm;
import com.eosa.web.requestform.service.DetectiveRequestFormService;
import com.eosa.web.util.CustomResponseData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/requestContract")
public class RequestContractController {
    
    @Autowired private RequestContractService requestContractService;
    @Autowired private DetectiveRequestFormService detectiveRequestFormService;
    @Autowired private CompanysService companysService;

    /**
     * companysIdx
     * @param companysIdx로 계약서에 기입할 업체 정보 조회
     * @return
     */
    @GetMapping("/selectCompanysByCompanysIdxUseRequestContractController")
    public CustomResponseData selectCompanysByCompanysIdxUseRequestContractController(
        @RequestParam("companysIdx") Long companysIdx
    ) {
        CustomResponseData result = new CustomResponseData();

        SelectAllCompanysList selectResult = companysService.selectCompanysByCompanysIdx(companysIdx);

        if(selectResult != null) {
            Map<String, Object> item = new HashMap<>();
            item.put("companysName", selectResult.getCompanysName());
            item.put("companysAddress", selectResult.getCompanysRegion1() + " " + selectResult.getCompanysRegion3());
            item.put("companysPhone", selectResult.getCompanysPhone());
            item.put("companysRegistCertiDate", selectResult.getCompanysRegistCertiDate());
            item.put("companysBankName", selectResult.getCompanysBankName());
            item.put("companysBankNumber", selectResult.getCompanysBankNumber());

            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(item);
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
     * requestFormIdx에 해당하는 계약서 정보 가져오기
     * @param requestContract
     * @return
     */
    @GetMapping("/selectRequestContractByRequestFormIdx")
    public CustomResponseData selectRequestContractByRequestFormIdx(@RequestParam("requestFormIdx") Long reqeustFormIdx) {
        CustomResponseData result = new CustomResponseData();
        
        RequestContract select = requestContractService.selectRequestContractByRequestFormIdx(reqeustFormIdx);

        if(select != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(select);
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
     * 계약서 저장
     * @param requestContract
     * @return
     */
    @PostMapping("/insertRequestContract")
    public CustomResponseData insertRequestContract(RequestContract requestContract) {
        CustomResponseData result = new CustomResponseData();
        // log.debug(requestContract.toString());

        RequestContract saveEntity = requestContractService.save(requestContract);

        if(saveEntity != null) {
            RequestForm rf = detectiveRequestFormService.selectRequestFormByRequestFormIdx(saveEntity.getRequestFormIdx());
            rf.setRequestFormStatus("계약진행");
            detectiveRequestFormService.updateRequestFormByEntity(rf);
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(true);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(false);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

}

package com.eosa.web.requestform;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eosa.web.util.CustomResponseData;
import com.eosa.web.util.NullCheck;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/requestForm")
public class RequestFormController {

    @Autowired private RequestFormService requestFormService;

    final int POST_COUNT = 10;
    final int BLOCK_COUNT = 10;

    /**
     * 의뢰를 신청하는 메서드
     * @param param
     * @return
     */
    @PostMapping("/requestFormRegister")
    public CustomResponseData requestFormRegister(
        RequestForm param
    ) {
        CustomResponseData result = new CustomResponseData();
        LocalDateTime currentTime = LocalDateTime.now();

        String[] targets = {
            "usersIdx", "detectiveIdx", "requestFormCategory", "requestFormRegion1", "requestFormRegion2"
        };

        NullCheck nullcheck = new NullCheck();
        Map<String, Object> items = nullcheck.ObjectNullCheck(param, targets);

        if(items.get("result") == "SUCCESS") {
            int transaction = requestFormService.requestFormRegister(param);
            if(transaction == 1) {
                log.info("[Success] {} request registrer", param.getIdx());
                result.setStatusCode(HttpStatus.OK.value());
                result.setResultItem(items);
                result.setResponseDateTime(currentTime);
            }
            else {
                result.setStatusCode(HttpStatus.BAD_REQUEST.value());
                result.setResultItem("SQL ERROR /request");
                result.setResponseDateTime(currentTime);
            }
        }
        else {
            log.error("Failure " + param.getIdx() + " has /request");
            result.setStatusCode(HttpStatus.BAD_REQUEST.value());
            result.setResultItem(items);
            result.setResponseDateTime(currentTime);
        }
        return result;
    }

    /**
     * 모든 의뢰신청 내역 조회
     * @return
     */
    @GetMapping("/getAllRequestForm")
    public CustomResponseData getAllRequestForm() {
        CustomResponseData result = new CustomResponseData();
        Map<String, Object> items = new HashMap<>();

        List<RequestForm> list = requestFormService.findAll();

        if(list != null) {
            items.put("item", list);
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(items);
            result.setResponseDateTime(LocalDateTime.now());
        }       
        else {
            result.setStatusCode(HttpStatus.EXPECTATION_FAILED.value());
            result.setResultItem("No Result");
            result.setResponseDateTime(LocalDateTime.now());
        }       

        return result;
    }

    @GetMapping("/findByDetectiveIdx")
    public CustomResponseData findByDetectiveIdx(
        @RequestParam("detectiveIdx") Long detectiveidx
    ) {
        CustomResponseData result = new CustomResponseData();
        
        Map<String, Object> items = new HashMap<>();

        List<RequestForm> list = requestFormService.findByDetectiveIdx(detectiveidx);

        if(list != null) {
            items.put("item", list);
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(items);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.EXPECTATION_FAILED.value());
            result.setResultItem("no result");
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }
}

package com.eosa.web.requestform.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eosa.security.CustomPrincipalDetails;
import com.eosa.web.requestform.entity.RequestForm;
import com.eosa.web.requestform.entity.RequestFormCategory;
import com.eosa.web.requestform.entity.SelectRequestFormList;
import com.eosa.web.requestform.repository.RequestFormCategoryRepository;
import com.eosa.web.requestform.service.RequestFormService;
import com.eosa.web.util.CustomResponseData;
import com.eosa.web.util.NullCheck;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nimbusds.jose.shaded.json.parser.ParseException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/requestForm")
public class RequestFormController {

    @Autowired private RequestFormService requestFormService;
    @Autowired private RequestFormCategoryRepository requestFormCategoryRepository;

    final int POST_COUNT = 10;
    final int BLOCK_COUNT = 10;

    /**
     * 의뢰를 신청하는 메서드
     * @param param
     * @return
     */
    @PostMapping("/requestFormRegister")
    public CustomResponseData requestFormRegister(
        @RequestBody String param
    )throws JSONException, ParseException {
        CustomResponseData result = new CustomResponseData();
        JsonObject jsonObject = JsonParser.parseString(param).getAsJsonObject();
        log.debug("param: {}", jsonObject.toString());

        RequestForm entity = new RequestForm();
            entity.setUsersIdx(jsonObject.get("usersIdx").getAsLong());
            entity.setCompanysIdx(jsonObject.get("companysIdx").getAsLong());
            entity.setRequestFormRegion1(jsonObject.get("requestFormRegion1").getAsString());
            entity.setRequestFormRegion2(jsonObject.get("requestFormRegion2").getAsString());
            entity.setRequestFormStatus("의뢰대기");
            entity.setRequestFormDate(LocalDateTime.now());
        RequestForm step1 = requestFormService.save(entity);
        // log.debug("step1: {}", step1.toString());

        if(step1 != null) {
            Long requestFormIdx = step1.getRequestFormIdx();
            JsonArray requestFormCategorys = jsonObject.get("requestFormCategory").getAsJsonArray();  
            for(int i = 0; i < requestFormCategorys.size(); i++) {
                RequestFormCategory entity2 = new RequestFormCategory();
                String requestFormCategoryValue = requestFormCategorys.get(i).getAsString();
                log.debug("String: {}", requestFormCategoryValue);
                entity2.setRequestFormIdx(requestFormIdx);
                entity2.setRequestFormCategoryValue(requestFormCategoryValue);
                int step2 = requestFormCategoryRepository.insertRequestFormCategory(entity2);
            }
        } 

        return result;
    }

    /**
     * 모든 의뢰신청 내역 조회
     * @return
    */
    @GetMapping("/selectAllRequestFormList")
    public CustomResponseData selectAllRequestFormList() {
        CustomResponseData result = new CustomResponseData();
        Map<String, Object> items = new HashMap<>();

        // List<RequestForm> list = requestFormService.findAll();
        List<SelectRequestFormList> list = requestFormService.selectAllRequestFormList();

        if(list != null) {
            // log.debug("list: {}", list.get(0).toString());
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(list);
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

    /**
     * CLIENT의 의뢰신청 내역 조회
     * 
     */
    @GetMapping("/selectAllRequestFormListByUsersIdx")
    public CustomResponseData selectAllRequestFormListByUsersIdx(
        @RequestParam("usersIdx") Long usersIdx
    ) {
        log.debug("usersIdx: {}", usersIdx);
        CustomResponseData result = new CustomResponseData();
        List<SelectRequestFormList> list = requestFormService.selectAllRequestFormListByUsersIdx(usersIdx);

        if(list.size() != 0) {
            log.debug("Client List: {}", list.toString());
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(list);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.BAD_REQUEST.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }
        
        return result;
    }
}

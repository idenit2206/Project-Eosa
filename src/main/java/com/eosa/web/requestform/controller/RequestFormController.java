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
import org.springframework.web.bind.annotation.*;

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

    /**
     * 의뢰를 신청하는 메서드
     * @param param
     * @return
     */
    @PostMapping("/requestFormRegister")
    public CustomResponseData requestFormRegister(
          @RequestPart("RequestForm") RequestForm param,
          @RequestPart("RequestFormCategory") List<String> requestFormCategory
    )throws JSONException, ParseException {
        CustomResponseData result = new CustomResponseData();
        log.debug(param.toString());
        log.debug(requestFormCategory.toString());

        RequestForm entity = new RequestForm();
            entity.setUsersIdx(param.getUsersIdx());
            entity.setCompanysIdx(param.getCompanysIdx());
            entity.setRequestFormRegion1(param.getRequestFormRegion1());
            entity.setRequestFormRegion2(param.getRequestFormRegion2());
            entity.setRequestFormChannel("의뢰");
            entity.setRequestFormStatus("의뢰대기");
            entity.setRequestFormDate(LocalDateTime.now());
        RequestForm step1 = requestFormService.save(entity);
        log.debug("step1: {}", step1.toString());

        if(step1 != null) {
            Long requestFormIdx = step1.getRequestFormIdx();
            for(int i = 0; i < requestFormCategory.size(); i++) {
                RequestFormCategory entity2 = new RequestFormCategory();
                String requestFormCategoryValue = requestFormCategory.get(i);
                log.debug("String: {}", requestFormCategoryValue);
                entity2.setRequestFormIdx(requestFormIdx);
                entity2.setRequestFormCategoryValue(requestFormCategoryValue);
                int step2 = requestFormCategoryRepository.insertRequestFormCategory(entity2);
            }
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem("SUCCESS");
            result.setResponseDateTime(LocalDateTime.now());
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

    /**
     * companysIdx와 일치하는 업체의 의뢰내역 조회
     * @param companysIdx
     * @return List<RequestForm>
     */
    @GetMapping("/findByCompanysIdx")
    public CustomResponseData findByCompanysIdx(
        @RequestParam("companysIdx") String companysIdx
    ) {
        CustomResponseData result = new CustomResponseData();
        
        Map<String, Object> items = new HashMap<>();

        List<SelectRequestFormList> list = requestFormService.findByCompanysIdxIdx(Long.parseLong(companysIdx));

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
     * CLIENT의 의뢰신청 내역 조회     *
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
    @GetMapping("/selectAllRequestFormListByUsersIdxOrderByDESC")
    public CustomResponseData selectAllRequestFormListByUsersIdxOrderByDESC(
            @RequestParam("usersIdx") Long usersIdx
    ) {
        log.debug("usersIdx: {}", usersIdx);
        CustomResponseData result = new CustomResponseData();
        List<SelectRequestFormList> list = requestFormService.selectAllRequestFormListByUsersIdxOrderByDESC(usersIdx);

        if(list.size() != 0) {
//            log.debug("Client List: {}", list.toString());
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
    @GetMapping("/selectAllRequestFormListByUsersIdxOrderByASC")
    public CustomResponseData selectAllRequestFormListByUsersIdxOrderByASC(
            @RequestParam("usersIdx") Long usersIdx
    ) {
//        log.debug("usersIdx: {}", usersIdx);
        CustomResponseData result = new CustomResponseData();
        List<SelectRequestFormList> list = requestFormService.selectAllRequestFormListByUsersIdxOrderByASC(usersIdx);

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

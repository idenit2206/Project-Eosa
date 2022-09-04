package com.eosa.web.requestform.controller;

import com.eosa.web.requestform.entity.RequestForm;
import com.eosa.web.requestform.entity.SelectRequestFormList;
import com.eosa.web.requestform.repository.RequestFormCategoryRepository;
import com.eosa.web.requestform.service.DetectiveRequestFormService;
import com.eosa.web.requestform.service.RequestFormService;
import com.eosa.web.util.CustomResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/requestForm")
public class DetectiveRequestFormController {

    @Autowired private DetectiveRequestFormService detectiveRequestFormService;
    @Autowired private RequestFormCategoryRepository requestFormCategoryRepository;

    /**
     * CompanysIdx가 일치하는 모든 RequestForm 조회
     * @param companysIdx
     * @return
     */
    @GetMapping("/selectDetectiveAllRequestFormListByCompanysIdx")
    public CustomResponseData selectAllDetectiveRequestFormListByCompanysIdx(
            @RequestParam("companysIdx") Long companysIdx
    ) {
//        log.debug("usersIdx: {}", companysIdx);
        CustomResponseData result = new CustomResponseData();
        List<SelectRequestFormList> list = detectiveRequestFormService.selectAllDetectiveRequestFormListByCompanysIdx(companysIdx);

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

    /**
     * CompanysIdx가 일치하는 모든 RequestForm 조회 날짜기준 내림차순 정렬
     * @param companysIdx
     * @return
     */
    @GetMapping("/selectDetectiveAllRequestFormListByCompanysIdxOrderByDESC")
    public CustomResponseData selectAllDetectiveRequestFormListByCompanysIdxOrderByDESC(
            @RequestParam("companysIdx") Long companysIdx
    ) {
//        log.debug("usersIdx: {}", companysIdx);
        CustomResponseData result = new CustomResponseData();
        List<SelectRequestFormList> list = detectiveRequestFormService.selectAllDetectiveRequestFormListByCompanysIdxOrderByDESC(companysIdx);

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
    /**
     * CompanysIdx가 일치하는 모든 RequestForm 조회 날짜기준 오름차순 정렬
     * @param companysIdx
     * @return
     */
    @GetMapping("/selectDetectiveAllRequestFormListByCompanysIdxOrderByASC")
    public CustomResponseData selectAllDetectiveRequestFormListByCompanysIdxOrderByASC(
            @RequestParam("companysIdx") Long companysIdx
    ) {
//        log.debug("usersIdx: {}", companysIdx);
        CustomResponseData result = new CustomResponseData();
        List<SelectRequestFormList> list = detectiveRequestFormService.selectAllDetectiveRequestFormListByCompanysIdxOrderByASC(companysIdx);

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

    /**
     * requestFormIdx가 일치하는 requestForm 상세보기
     * @param requestFormIdx
     * @return
     */
    @GetMapping("/selectDetectiveRequestFormInfoByRequestFormIdx")
    public CustomResponseData selectDetectiveRequestFormInfoByRequestFormIdx(
            @RequestParam("requestFormIdx") Long requestFormIdx)
    {
        CustomResponseData result = new CustomResponseData();
        RequestForm entity = detectiveRequestFormService.selectDetectiveRequestFormInfoByRequestFormIdx(requestFormIdx);

        if(entity != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(entity);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    @PostMapping("/updateRequestFormStatusWhereRequestFormIdx")
    public CustomResponseData updateRequestFormStatusWhereRequestFormIdx(
            @RequestParam(name="requestFormIdx") Long requestFormIdx,
            @RequestParam(name="requestFormStatus") String requestFormStatus) {
        CustomResponseData result = new CustomResponseData();

        int updateRows = detectiveRequestFormService.updateRequestFormStatusWhereRequestFormIdx(requestFormIdx, requestFormStatus);

        if(updateRows == 1) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem("TRUE");
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem("FALSE");
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

}

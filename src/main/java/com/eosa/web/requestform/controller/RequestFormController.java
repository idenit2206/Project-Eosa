package com.eosa.web.requestform.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.eosa.web.requestform.entity.RequestForm;
import com.eosa.web.requestform.entity.RequestFormBackup;
import com.eosa.web.requestform.entity.RequestFormCategory;
import com.eosa.web.requestform.entity.RequestFormCategoryBackup;
import com.eosa.web.requestform.entity.SelectRequestFormList;
import com.eosa.web.requestform.repository.RequestFormCategoryRepository;
import com.eosa.web.requestform.repository.tablebackup.RequestFormCategoryBackupRepository;
import com.eosa.web.requestform.service.RequestFormService;
import com.eosa.web.requestform.service.tablebackup.RequestFormBackupService;
import com.eosa.web.util.CustomResponseData;
import com.nimbusds.jose.shaded.json.parser.ParseException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/requestForm")
public class RequestFormController {

    @Autowired private RequestFormService requestFormService;
    @Autowired private RequestFormBackupService requestFormBackupService;
    @Autowired private RequestFormCategoryRepository requestFormCategoryRepository;
    @Autowired private RequestFormCategoryBackupRepository requestFormCategoryBackupRepository;

    /**
     * CLIENT회원의 의뢰하기 버튼을 통한 의뢰 신청 컨트롤러
     * @param param RequestForm
     * @return
     */
    @PostMapping("/requestFormRegister")
    public CustomResponseData requestFormRegister(
          @RequestPart("RequestForm") RequestForm param,
          @RequestPart("RequestFormCategory") List<String> requestFormCategory
    )throws JSONException, ParseException {
        CustomResponseData result = new CustomResponseData();
        log.debug("[requestFormRegister] {}", param.toString());
        log.debug("[requestFormRegister] {}", requestFormCategory.toString());

        RequestForm entity = new RequestForm();
            entity.setUsersIdx(param.getUsersIdx());
            entity.setCompanysIdx(param.getCompanysIdx());
            entity.setRequestFormRegion1(param.getRequestFormRegion1());
            // entity.setRequestFormRegion2(param.getRequestFormRegion2());
            entity.setRequestFormChannel("의뢰");
            entity.setRequestFormStatus("상담대기");
            entity.setRequestFormDate(LocalDateTime.now());
        RequestForm step1 = requestFormService.save(entity);       

        if(step1 != null) {
            log.info("[requestFormRegister] step1: {}", step1.toString());
            Long requestFormIdx = step1.getRequestFormIdx();
            
            RequestFormBackup entityBackup = new RequestFormBackup();
            entityBackup.setRequestFormIdx(requestFormIdx);
            entityBackup.setUsersIdx(param.getUsersIdx());
            entityBackup.setCompanysIdx(param.getCompanysIdx());
            entityBackup.setRequestFormRegion1(param.getRequestFormRegion1());
            // entity.setRequestFormRegion2(param.getRequestFormRegion2());
            entityBackup.setRequestFormChannel("의뢰");
            entityBackup.setRequestFormStatus("상담대기");
            entityBackup.setRequestFormDate(LocalDateTime.now());
            RequestFormBackup step1Backup = requestFormBackupService.save(entityBackup);

            Long requestFormBackupIdx = step1Backup.getRequestFormBackupIdx();
            log.info("requestFormRegist] step1Backup Idx: {}", String.valueOf(requestFormBackupIdx));

            for(int i = 0; i < requestFormCategory.size(); i++) {
                RequestFormCategory entity2 = new RequestFormCategory();
                String requestFormCategoryValue = requestFormCategory.get(i);
                // log.debug("String: {}", requestFormCategoryValue);
                entity2.setRequestFormIdx(requestFormIdx);
                entity2.setRequestFormCategoryValue(requestFormCategoryValue);
                int step2 = requestFormCategoryRepository.insertRequestFormCategory(entity2);

                RequestFormCategoryBackup entity2Backup = new RequestFormCategoryBackup();
                String requestFormCategoryValueBackup = requestFormCategory.get(i);
                // entity2Backup.setRequestFormIdx(requestFormBackupIdx);
                entity2Backup.setRequestFormBackupIdx(requestFormBackupIdx);
                entity2Backup.setRequestFormCategoryValue(requestFormCategoryValue);
                // log.info(entity2Backup.toString());
                int step2Backup = requestFormCategoryBackupRepository.insertRequestFormCategory(entity2Backup);
            }
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem("SUCCESS");
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    /**
     * CLIENT회원의 전화상담 버튼을 통한 의뢰신청 컨트롤러
     * @param param
     * @param requestFormCategory
     * @return
     * @throws JSONException
     * @throws ParseException
     */
    @PostMapping("/requestFormRegisterChannelPhone")
    public CustomResponseData insertFormRegisterChannelPhone(
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
        // entity.setRequestFormRegion2(param.getRequestFormRegion2());
        entity.setRequestFormChannel("전화");
        entity.setRequestFormStatus("상담대기");
        entity.setRequestFormDate(LocalDateTime.now());

        RequestForm step1 = requestFormService.save(entity);
        
        log.debug("step1: {}", step1.toString());

        if(step1 != null) {            
            Long requestFormIdx = step1.getRequestFormIdx();

            for(int i = 0; i < requestFormCategory.size(); i++) {
                RequestFormCategory entity2 = new RequestFormCategory();
                String requestFormCategoryValue = requestFormCategory.get(i);
                log.info("의뢰내역서 의뢰지역: {}", requestFormCategoryValue);
                entity2.setRequestFormIdx(requestFormIdx);
                entity2.setRequestFormCategoryValue(requestFormCategoryValue);
                int step2 = requestFormCategoryRepository.insertRequestFormCategory(entity2);
            }
        }

        if(step1 != null) {            
            // 의뢰내역서(백업) 저장   
            RequestFormBackup entityBackup = new RequestFormBackup();
            entityBackup.setRequestFormIdx(step1.getRequestFormIdx());
            entityBackup.setUsersIdx(param.getUsersIdx());
            entityBackup.setCompanysIdx(param.getCompanysIdx());
            entityBackup.setRequestFormRegion1(param.getRequestFormRegion1());
            // entity.setRequestFormRegion2(param.getRequestFormRegion2());
            entityBackup.setRequestFormChannel("전화");
            entityBackup.setRequestFormStatus("상담대기");
            entityBackup.setRequestFormDate(LocalDateTime.now());
            RequestFormBackup step1Backup = requestFormBackupService.save(entityBackup);
         
            RequestFormCategoryBackup entity2Backup = new RequestFormCategoryBackup();
            for(int i = 0; i < requestFormCategory.size(); i++) {
                String requestFormCategoryValueBackup = requestFormCategory.get(i);
                log.info("의뢰내역서(백업) 의뢰지역: {}", requestFormCategoryValueBackup);
                entity2Backup.setRequestFormBackupIdx(step1Backup.getRequestFormBackupIdx());
                entity2Backup.setRequestFormCategoryValue(requestFormCategory.get(i));
                int step2Backup = requestFormCategoryBackupRepository.insertRequestFormCategory(entity2Backup);
    
            }
           
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem("SUCCESS");
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
     * CLIENT회원의 채팅상담 버튼을 통한 의뢰신청
     * @param param
     * @param requestFormCategory
     * @return
     * @throws JSONException
     * @throws ParseException
     */
    @PostMapping("/requestFormRegisterChannelChatting")
    public CustomResponseData insertFormRegisterChannelChatting(
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
        // entity.setRequestFormRegion2(param.getRequestFormRegion2());
        entity.setRequestFormChannel("채팅");
        entity.setRequestFormStatus("상담대기");
        entity.setRequestFormDate(LocalDateTime.now());
        RequestForm step1 = requestFormService.save(entity);
        
        log.debug("step1: {}", step1.toString());

        if(step1 != null) {
            Long requestFormIdx = step1.getRequestFormIdx();

            RequestFormBackup entityBackup = new RequestFormBackup();
            entityBackup.setRequestFormIdx(requestFormIdx);
            entityBackup.setUsersIdx(param.getUsersIdx());
            entityBackup.setCompanysIdx(param.getCompanysIdx());
            entityBackup.setRequestFormRegion1(param.getRequestFormRegion1());
            // entity.setRequestFormRegion2(param.getRequestFormRegion2());
            entityBackup.setRequestFormChannel("채팅");
            entityBackup.setRequestFormStatus("상담대기");
            entityBackup.setRequestFormDate(LocalDateTime.now());
            RequestFormBackup step1Backup = requestFormBackupService.save(entityBackup);
            Long requestFormBackupIdx = step1Backup.getRequestFormBackupIdx();

            for(int i = 0; i < requestFormCategory.size(); i++) {
                RequestFormCategory entity2 = new RequestFormCategory();
                String requestFormCategoryValue = requestFormCategory.get(i);
                log.debug("String: {}", requestFormCategoryValue);
                entity2.setRequestFormIdx(requestFormIdx);
                entity2.setRequestFormCategoryValue(requestFormCategoryValue);
                int step2 = requestFormCategoryRepository.insertRequestFormCategory(entity2);

                RequestFormCategoryBackup entity2Backup = new RequestFormCategoryBackup();
                log.debug("String: {}", requestFormCategoryValue);
                entity2Backup.setRequestFormBackupIdx(requestFormBackupIdx);
                entity2Backup.setRequestFormCategoryValue(requestFormCategoryValue);
                int step2Backup = requestFormCategoryBackupRepository.insertRequestFormCategory(entity2Backup);
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
        // List<SelectRequestFormList> list = requestFormBackupService.selectAllRequestFormList();

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
     * 모든 의뢰신청 내역 조회 통계용 백업 데이터를 조회
     * @return
    */
    @GetMapping("/selectAllRequestFormListbackup")
    public CustomResponseData selectAllRequestFormListbackup() {
        CustomResponseData result = new CustomResponseData();
        Map<String, Object> items = new HashMap<>();

        // List<RequestForm> list = requestFormService.findAll();
        // List<SelectRequestFormList> list = requestFormService.selectAllRequestFormList();
        List<SelectRequestFormList> list = requestFormBackupService.selectAllRequestFormList();

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
     * CLIENT의 usersIdx와 일치하는 모든 의뢰신청 내역 조회
     * @param
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

    /**
     * CLIENT의 usersIdx와 일치하는 모든 의뢰신청 내역 조회(의뢰 신청날짜 내림차순)
     * @param usersIdx
     * @return
     */
    @GetMapping("/selectAllRequestFormListByUsersIdxOrderByDESC")
    public CustomResponseData selectAllRequestFormListByUsersIdxOrderByDESC(
            @RequestParam("usersIdx") Long usersIdx
    ) {
        log.info("[selectAllRequestFormListByUsersIdxOrderByDESC] usersIdx: {}", usersIdx);
        CustomResponseData result = new CustomResponseData();
        List<SelectRequestFormList> list = requestFormService.selectAllRequestFormListByUsersIdxOrderByRequestFormDateDESC(usersIdx);
        // List<SelectRequestFormList> list = requestFormBackupService.selectAllRequestFormListByUsersIdxOrderByRequestFormDateDESC(usersIdx);


        if(list.size() != 0) {
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
     * 통계용를 위해 백업해놓은 데이터를 조회하는 컨트롤러
     * @param usersIdx
     * @return
     */
    @GetMapping("/selectAllRequestFormListByUsersIdxOrderByDESCbackup")
    public CustomResponseData selectAllRequestFormListByUsersIdxOrderByDESCbackup(
            @RequestParam("usersIdx") Long usersIdx
    ) {
        log.info("[selectAllRequestFormListByUsersIdxOrderByDESC] usersIdx: {}", usersIdx);
        CustomResponseData result = new CustomResponseData();
        // List<SelectRequestFormList> list = requestFormService.selectAllRequestFormListByUsersIdxOrderByRequestFormDateDESC(usersIdx);
        List<SelectRequestFormList> list = requestFormBackupService.selectAllRequestFormListByUsersIdxOrderByRequestFormDateDESC(usersIdx);


        if(list.size() != 0) {
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
     * CLIENT의 usersIdx와 일치하는 모든 의뢰신청 내역 조회(의뢰신청날짜 오름차순)
     * @param usersIdx
     * @return
     */
    @GetMapping("/selectAllRequestFormListByUsersIdxOrderByASC")
    public CustomResponseData selectAllRequestFormListByUsersIdxOrderByASC(
            @RequestParam("usersIdx") Long usersIdx
    ) {
//        log.debug("usersIdx: {}", usersIdx);
        CustomResponseData result = new CustomResponseData();
        List<SelectRequestFormList> list = requestFormService.selectAllRequestFormListByUsersIdxOrderByRequestFormDateASC(usersIdx);

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
     * requestFormIdx와 일치하는 requestForm 조회
     * @param requestFormIdx
     */
    @GetMapping("/selectRequestFormByRequsetFormIdx")
    public CustomResponseData selectRequestFormByRequsetFormIdx(
        @RequestParam("requestFormIdx") Long requestFormIdx
    ) {
        log.debug("[selectRequestFormByRequestFormIdx] 시작");
        log.debug("[selectRequestFormByRequestFormIdx] formIdx: {}", String.valueOf(requestFormIdx));
        CustomResponseData result = new CustomResponseData();

        RequestForm item = requestFormService.selectOneRequestFormByRequsetFormIdx(requestFormIdx);
        // Object item = null;
        if(item != null) {
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
     * CLIENT의 의뢰 임무 계약서 작성을 위한 정보 업데이트
     * @param requestForm
     * @return
     */
    @PutMapping("/updateRequestFormContractData")
    public CustomResponseData updateRequestFormContractData(
        RequestForm requestForm
    ) {
        CustomResponseData result = new CustomResponseData();
        
        RequestFormBackup backup = new RequestFormBackup();
        backup.setRequestFormIdx(requestForm.getRequestFormIdx());
        backup.setRequestFormContractClientDelegate(requestForm.getRequestFormContractClientDelegate());
        backup.setRequestFormContractClientAddress(requestForm.getRequestFormContractClientAddress());
        backup.setRequestFormContractClientBirth(requestForm.getRequestFormContractClientBirth());
        backup.setRequestFormContractClientContact(requestForm.getRequestFormContractClientContact());

        int updateRow = requestFormService.updateRequestFormContractData(requestForm);
        int updateBackupRow = requestFormBackupService.updateRequestFormContractData(backup);

        if(updateRow == 1 && updateBackupRow == 1) {
            log.info("의뢰내역서 번호 {} 의 의뢰 임무 계약서 작성을 위한 정보를 업데이트 합니다.", requestForm.getRequestFormIdx());
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(true);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            log.info("의뢰내역서 번호 {} 의 의뢰 임무 계약서 작성을 위한 정보를 업데이트를 실패했습니다.", requestForm.getRequestFormIdx());
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(false);
            result.setResponseDateTime(LocalDateTime.now());
        }
        
        return result;
    }

}

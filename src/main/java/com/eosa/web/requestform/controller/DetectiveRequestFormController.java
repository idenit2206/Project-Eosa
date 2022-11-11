package com.eosa.web.requestform.controller;

import com.eosa.web.requestform.entity.RequestForm;
import com.eosa.web.requestform.entity.RequestFormBackup;
import com.eosa.web.requestform.entity.SelectRequestFormList;
import com.eosa.web.requestform.service.DetectiveRequestFormService;
import com.eosa.web.requestform.service.RequestFormCategoryService;
import com.eosa.web.requestform.service.RequestFormService;
import com.eosa.web.requestform.service.tablebackup.DetectiveRequestFormBackupService;
import com.eosa.web.requestform.service.tablebackup.RequestFormCategoryBackupService;
import com.eosa.web.users.service.UsersService;
import com.eosa.web.util.CustomResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/requestForm")
public class DetectiveRequestFormController {

    @Autowired private DetectiveRequestFormService detectiveRequestFormService;
    @Autowired private DetectiveRequestFormBackupService detectiveRequestFormBackupService;
    @Autowired private RequestFormCategoryService requestFormCategoryService;
    @Autowired private RequestFormCategoryBackupService requestFormCategoryBackupService;  
    @Autowired private UsersService usersService;

    /**
     * CompanysIdx가 일치하는 모든 RequestForm 조회
     * 
     * @param companysIdx
     * @return
     */
    @GetMapping("/selectDetectiveAllRequestFormListByCompanysIdx")
    public CustomResponseData selectAllDetectiveRequestFormListByCompanysIdx(
            @RequestParam("companysIdx") Long companysIdx) {
        // log.debug("usersIdx: {}", companysIdx);
        CustomResponseData result = new CustomResponseData();
        List<SelectRequestFormList> list = detectiveRequestFormService
                .selectAllDetectiveRequestFormListByCompanysIdx(companysIdx);

        if (list.size() != 0) {
            log.debug("Client List: {}", list.toString());
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(list);
            result.setResponseDateTime(LocalDateTime.now());
        } else {
            result.setStatusCode(HttpStatus.BAD_REQUEST.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    /**
     * CompanysIdx가 일치하는 모든 RequestForm 조회 날짜기준 내림차순 정렬
     * 
     * @param companysIdx
     * @return
     */
    @GetMapping("/selectDetectiveAllRequestFormListByCompanysIdxOrderByDESC")
    public CustomResponseData selectAllDetectiveRequestFormListByCompanysIdxOrderByDESC(
            @RequestParam("companysIdx") Long companysIdx) {
        // log.debug("usersIdx: {}", companysIdx);
        CustomResponseData result = new CustomResponseData();
        List<SelectRequestFormList> list = detectiveRequestFormService.selectAllDetectiveRequestFormListByCompanysIdxOrderByDESC(companysIdx);
        // List<SelectRequestFormList> list = detectiveRequestFormBackupService.selectAllDetectiveRequestFormListByCompanysIdxOrderByDESC(companysIdx);

        if (list.size() != 0) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(list);
            result.setResponseDateTime(LocalDateTime.now());
        } else {
            result.setStatusCode(HttpStatus.BAD_REQUEST.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    /**
     * CompanysIdx가 일치하는 모든 RequestForm 조회 날짜기준 내림차순 정렬
     * 통계를 위해 백업 데이터를 조회합니다. 
     * @param companysIdx
     * @return
     */
    @GetMapping("/selectDetectiveAllRequestFormListByCompanysIdxOrderByDESCbackup")
    public CustomResponseData selectAllDetectiveRequestFormListByCompanysIdxOrderByDESCbackup(
            @RequestParam("companysIdx") Long companysIdx) {
        // log.debug("usersIdx: {}", companysIdx);
        CustomResponseData result = new CustomResponseData();
        // List<SelectRequestFormList> list = detectiveRequestFormService.selectAllDetectiveRequestFormListByCompanysIdxOrderByDESC(companysIdx);
        List<SelectRequestFormList> list = detectiveRequestFormBackupService.selectAllDetectiveRequestFormListByCompanysIdxOrderByDESC(companysIdx);

        if (list.size() != 0) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(list);
            result.setResponseDateTime(LocalDateTime.now());
        } else {
            result.setStatusCode(HttpStatus.BAD_REQUEST.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    /**
     * CompanysIdx가 일치하는 모든 RequestForm 조회 날짜기준 오름차순 정렬
     * 
     * @param companysIdx
     * @return
     */
    @GetMapping("/selectDetectiveAllRequestFormListByCompanysIdxOrderByASC")
    public CustomResponseData selectAllDetectiveRequestFormListByCompanysIdxOrderByASC(
            @RequestParam("companysIdx") Long companysIdx) {
        // log.debug("usersIdx: {}", companysIdx);
        CustomResponseData result = new CustomResponseData();
        List<SelectRequestFormList> list = detectiveRequestFormService
                .selectAllDetectiveRequestFormListByCompanysIdxOrderByASC(companysIdx);

        if (list.size() != 0) {
            log.debug("Client List: {}", list.toString());
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(list);
            result.setResponseDateTime(LocalDateTime.now());
        } else {
            result.setStatusCode(HttpStatus.BAD_REQUEST.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    /**
     * requestFormIdx가 일치하는 requestForm 상세보기
     * 
     * @param requestFormIdx
     * @return
     */
    @GetMapping("/selectDetectiveRequestFormInfoByRequestFormIdx")
    public CustomResponseData selectDetectiveRequestFormInfoByRequestFormIdx(
            @RequestParam("requestFormIdx") Long requestFormIdx) {
        log.debug("[selectDetectiveRequestFormInfoByRequestFormIdx] Start");
        CustomResponseData result = new CustomResponseData();
        RequestForm entity = detectiveRequestFormService.selectDetectiveRequestFormInfoByRequestFormIdx(requestFormIdx);

        if (entity != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(entity);
            result.setResponseDateTime(LocalDateTime.now());
        } else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    
    /** 
     * requestFormIdx가 일치하는 requestForm의 데이터 변경
     * @param updateRequestFormStatusByRequestFormIdx(
     * @return CustomResponseData
     * @throws IOException
     */
    @PostMapping("/updateRequestFormStatusByRequestFormIdx")
    public CustomResponseData updateRequestFormStatusByRequestFormIdx(
            @RequestParam(name = "requestFormIdx") Long requestFormIdx,
            @RequestParam(name = "requestFormStatus") String requestFormStatus,
            @RequestParam(name = "requestFormRejectMessage", required = false) String requestFormRejectMessage
    ) throws IOException {
        log.info("[updateRequestFormStatusByRequestFormIdx] RequestForm: {} 번에 대한 갱신 요청입니다.", requestFormIdx);
        CustomResponseData result = new CustomResponseData();
        
        RequestForm rf = detectiveRequestFormService.selectRequestFormByRequestFormIdx(requestFormIdx);
        // log.info("rf: {}", rf.toString());
        rf.setRequestFormStatus(requestFormStatus);
        rf.setRequestFormRejectMessage(requestFormRejectMessage);
        rf.setRequestFormClientReadState(0);

        log.info("[updateRequestFormStatusWhereRequestFormIdx] 의뢰요청서 업데이트: 의뢰요청서IDX: {}, 의뢰상태: {}, 의뢰관련메시지: {}", 
            rf.getRequestFormIdx(), rf.getRequestFormStatus(), rf.getRequestFormRejectMessage()
        );

        RequestFormBackup rfbackup = detectiveRequestFormBackupService.selectDetectiveRequestFormInfoByRequestFormIdx(requestFormIdx);
        rfbackup.setRequestFormStatus(requestFormStatus);
        rfbackup.setRequestFormRejectMessage(requestFormRejectMessage);
        rfbackup.setRequestFormClientReadState(0);

        int updateRows = 0;
        int updateBackupRows = 0;
        if(requestFormStatus.equals("상담취소")) {
            updateRows = detectiveRequestFormService.updateRequestFormByEntity(rf);
            updateBackupRows = detectiveRequestFormBackupService.updateRequestFormByEntity(rfbackup);
        }
        else if(requestFormStatus.equals("상담완료")) {
            updateRows = detectiveRequestFormService.updateRequestFormStatusByRequestFormIdxCaseConsultComplete(
                requestFormIdx, LocalDateTime.now(), requestFormStatus, requestFormRejectMessage
            );
            updateBackupRows = detectiveRequestFormBackupService.updateRequestFormStatusByRequestFormIdxCaseConsultComplete(
                requestFormIdx, LocalDateTime.now(), requestFormStatus, requestFormRejectMessage
            );
        }
        else if(requestFormStatus.equals("계약취소")) {
            rf.setRequestFormStatus("계약취소");
            rfbackup.setRequestFormStatus("계약취소");
            rf.setRequestFormStatusChangeDate(LocalDateTime.now());
            rfbackup.setRequestFormStatusChangeDate(LocalDateTime.now());
            updateRows = detectiveRequestFormService.updateRequestFormByEntity(rf);
            updateBackupRows = detectiveRequestFormBackupService.updateRequestFormByEntity(rfbackup);
        }
        else if(requestFormStatus.equals("임무취소")) {
            rf.setRequestFormStatus("임무취소");
            rfbackup.setRequestFormStatus("임무취소");
            rf.setRequestFormStatusChangeDate(LocalDateTime.now());
            rfbackup.setRequestFormStatusChangeDate(LocalDateTime.now());
            updateRows = detectiveRequestFormService.updateRequestFormByEntity(rf);
            updateBackupRows = detectiveRequestFormBackupService.updateRequestFormByEntity(rfbackup);
        }     
        else if (rf.getRequestFormStatus().equals("의뢰대기")) {
            rf.setRequestFormStatusChangeDate(LocalDateTime.now());
            rfbackup.setRequestFormStatusChangeDate(LocalDateTime.now());
            updateRows = detectiveRequestFormService.updateRequestFormByEntity(rf);
            updateBackupRows = detectiveRequestFormBackupService.updateRequestFormByEntity(rfbackup);
        }
        else if(rf.getRequestFormStatus().equals("의뢰거절")) {
            rf.setRequestFormStatusChangeDate(LocalDateTime.now());
            rfbackup.setRequestFormStatusChangeDate(LocalDateTime.now());
            updateRows = detectiveRequestFormService.updateRequestFormByEntity(rf);
            updateBackupRows = detectiveRequestFormBackupService.updateRequestFormByEntity(rfbackup);
        }
        else if(rf.getRequestFormStatus().equals("의뢰취소")) {
            rf.setRequestFormStatusChangeDate(LocalDateTime.now());
            rfbackup.setRequestFormStatusChangeDate(LocalDateTime.now());
            updateRows = detectiveRequestFormService.updateRequestFormByEntity(rf);
            updateBackupRows = detectiveRequestFormBackupService.updateRequestFormByEntity(rfbackup);
        }
        else if(rf.getRequestFormStatus().equals("계약진행")) {
            rf.setRequestFormStatusChangeDate(LocalDateTime.now());
            rfbackup.setRequestFormStatusChangeDate(LocalDateTime.now());
            updateRows = detectiveRequestFormService.updateRequestFormByEntity(rf);
            updateBackupRows = detectiveRequestFormBackupService.updateRequestFormByEntity(rfbackup);
        }
        else if(requestFormStatus.equals("계약취소")) {
            rf.setRequestFormStatus("계약취소");
            rfbackup.setRequestFormStatus("계약취소");
            rf.setRequestFormStatusChangeDate(LocalDateTime.now());
            rfbackup.setRequestFormStatusChangeDate(LocalDateTime.now());
            updateRows = detectiveRequestFormService.updateRequestFormByEntity(rf);
            updateBackupRows = detectiveRequestFormBackupService.updateRequestFormByEntity(rfbackup);
        }
        else if(rf.getRequestFormStatus().equals("임무진행")) {
            rf.setRequestFormStatusChangeDate(LocalDateTime.now());
            rfbackup.setRequestFormStatusChangeDate(LocalDateTime.now());
            updateRows = detectiveRequestFormService.updateRequestFormByEntity(rf);
            updateBackupRows = detectiveRequestFormBackupService.updateRequestFormByEntity(rfbackup);
        }       
        else if(rf.getRequestFormStatus().equals("임무완료")) {
            rf.setRequestFormStatusChangeDate(LocalDateTime.now());
            rfbackup.setRequestFormStatusChangeDate(LocalDateTime.now());
            updateRows = detectiveRequestFormService.updateRequestFormByEntity(rf);
            updateBackupRows = detectiveRequestFormBackupService.updateRequestFormByEntity(rfbackup);
        }
       

        if (updateRows == 1) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem("TRUE");
            result.setResponseDateTime(LocalDateTime.now());
        } else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem("FALSE");
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }
    
}

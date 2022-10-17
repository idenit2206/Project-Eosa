package com.eosa.web.pushnotification;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eosa.web.requestform.entity.RequestForm;
import com.eosa.web.util.CustomResponseData;

@RestController
@RequestMapping("/api/pushNotification")
public class PushNotificationController {

    @Autowired private PushNotificationService pushNotificationService;
    
    /**
     * CLIENT 회원의 알림 조회(상담,의뢰 대상)
     * @param usersIdx
     * @return
     */
    @GetMapping("/selectPushNotification")
    public CustomResponseData selectPushNotificationByUsersIdx(
        @RequestParam("usersIdx") Long usersIdx
    ) {
        CustomResponseData result = new CustomResponseData();

        List<RequestForm> items = pushNotificationService.selectPushNotificationByUsersIdx(usersIdx);

        if(items != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(items);
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
     * Detective 회원의 알림 조회(상담,의뢰 대상)
     * @param usersIdx
     * @return
     */
    @GetMapping("/selectPushNotificationForDetective")
    public CustomResponseData selectPushNotificationForDetective(
        @RequestParam("usersIdx") Long usersIdx
    ) {
        CustomResponseData result = new CustomResponseData();

        List<RequestForm> items = pushNotificationService.selectPushNotificationForDetective(usersIdx);

        if(items != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(items);
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
     * 푸시알림(requestForm 대상) 상태변경(읽음으로 전환)
     * @return CustomResponseData
     */
    @PutMapping("/readPushNotification")
    public CustomResponseData readPushNotification(
        @RequestParam("requestFormIdx") Long requestFormIdx
    ) {
        CustomResponseData result = new CustomResponseData();

        int item = pushNotificationService.updateReadStateRead(requestFormIdx);

        if(item == 1) {
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

    
    /** 
     * 푸시알림(requestForm 대상 Detective회원) 상태변경(읽음으로 전환)
     * @return CustomResponseData
     */
    @PutMapping("/readPushNotificationDetective")
    public CustomResponseData readPushNotificationDetective(
        @RequestParam("requestFormIdx") Long requestFormIdx,
        @RequestParam("usersIdx") Long usersIdx
    ) {
        CustomResponseData result = new CustomResponseData();

        int item = pushNotificationService.updateReadStateReadDetective(requestFormIdx, usersIdx);

        if(item == 1) {
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

package com.eosa.web.pushnotification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eosa.web.companys.service.CompanysService;
import com.eosa.web.requestform.entity.RequestForm;
import com.eosa.web.requestform.repository.RequestFormRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PushNotificationService {

    @Autowired private RequestFormRepository requestFormRepository;
    @Autowired private CompanysService companysService;

    public List<RequestForm> selectPushNotificationByUsersIdx(Long usersIdx) {
        List<RequestForm> items = requestFormRepository.selectRequestFormByUsersIdx(usersIdx);
        log.debug("[selectPushNotificationByUsersIdx] 요청한 유저번호: {} ", usersIdx);
        return items;
    }

    public List<RequestForm> selectPushNotificationForDetective(Long usersIdx) {
        Long companysIdx =companysService.selectCompanysIdxByUsersIdx(usersIdx);
        List<RequestForm> items = requestFormRepository.selectPushNotificationForDetective(companysIdx);
        log.debug("[selectPushNotificationForDetective] 요청한 업체 번호: {} ", companysIdx);
        return items;
    }    

    public List<RequestForm> selectPushNotificationByCompanysIdx(Long companysIdx) {
        List<RequestForm> items = requestFormRepository.selectRequestFormByCompanysIdx(companysIdx);
        return items;
    }

    /**
     * 푸시알림 읽음 처리 Client
     * @param requestFormIdx
     * @return
     */
    public int updateReadStateRead(Long requestFormIdx) {
        return requestFormRepository.updateReadStateRead(requestFormIdx);
    }

    /**
     * 푸시알림 읽음 처리 Detective
     * @return
     */
    public int updateReadStateReadDetective(Long requestFormIdx, Long usersIdx) {
        Long companysIdx = companysService.selectCompanysIdxByUsersIdx(usersIdx);
        return requestFormRepository.updateReadStateReadDetective(requestFormIdx, companysIdx);
    }
    
}

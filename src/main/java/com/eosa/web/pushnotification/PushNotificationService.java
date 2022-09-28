package com.eosa.web.pushnotification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eosa.web.requestform.entity.RequestForm;
import com.eosa.web.requestform.repository.RequestFormRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PushNotificationService {

    @Autowired private RequestFormRepository requestFormRepository;

    public List<RequestForm> selectPushNotificationByUsersIdx(Long usersIdx) {
        List<RequestForm> items = requestFormRepository.selectRequestFormByUsersIdx(usersIdx);
        log.debug("[selectPushNotificationByUsersIdx] 요청한 유저번호: {} ", usersIdx);
        return items;
    }

    public List<RequestForm> selectPushNotificationByCompanysIdx(Long companysIdx) {
        List<RequestForm> items = requestFormRepository.selectRequestFormByCompanysIdx(companysIdx);
        return items;
    }

    /**
     * 읽음 처리
     * @param requestFormIdx
     * @return
     */
    public int updateReadStateRead(Long requestFormIdx) {
        return requestFormRepository.updateReadStateRead(requestFormIdx);
    }
    
}

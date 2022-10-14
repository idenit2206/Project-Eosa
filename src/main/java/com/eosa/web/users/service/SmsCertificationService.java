package com.eosa.web.users.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
@Service
public class SmsCertificationService {

    private static Map<String, String> authKeyList = new HashMap<>();
    @Autowired private RedisTemplate<String, String> redisTemplate;
    
    /** 
     * @param usersPhone
     * @return String
     */
    public String createCertificationCode(String usersPhone) {
        String result = "";
        while(result.length() <= 5) {
            result += String.valueOf((int)Math.floor(Math.random() * 9));
        }
        log.info("[createCertificationCode] 서버에 새로운 인증코드 객체를 저장합니다.");
        log.debug("[createCertificationCode] 인증코드 usersPhone: {}  code: {}", usersPhone, result);

        if(authKeyList.get(usersPhone) != null) {
            // log.info("not null: {}",authKeyList.toString());
            authKeyList.clear();
            authKeyList.put(usersPhone, result);
            return result;
        }
        else {
            log.info("null: {}", authKeyList.toString());
            authKeyList.put(usersPhone, result);
            return result;
        }        
    }

    @Scheduled(fixedDelay = 600000)
    public void removeAuthCodePerHour() {
        authKeyList.clear();
    }

    
    /** 
     * @param usersPhone
     * @param code
     */
    public void savedAuthCode(String usersPhone, String code) {
        log.info("[savedAuthCode] redis에 인증코드 객체를 저장합니다.");
        redisTemplate.opsForValue()
            .set(usersPhone, code, Duration.ofSeconds(180));
    }

    
    /** 
     * @param usersPhone
     * @return String
     */
    public String getAuthCode(String usersPhone) {
        log.info("[savedAuthCode] 서버의 인증정보와 비교합니다.");
//        log.info("server의 인증정보를 가져옵니다.");
//        return redisTemplate.opsForValue().get(usersPhone);
        return authKeyList.get(usersPhone);
    }

    
    /** 
     * @param usersPhone
     */
    public void removeAuthCode(String usersPhone) {
//        log.info("redis의 인증정보를 삭제합니다.");
//        redisTemplate.delete(usersPhone);
        log.info("[savedAuthCode] 서버의 인증정보를 삭제합니다.");
        authKeyList.remove(usersPhone);
    }

}

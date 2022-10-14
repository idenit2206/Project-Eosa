package com.eosa.web.users.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

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
        log.debug("[createCertificationCode] 인증코드 usersPhone: {}  code: {}", usersPhone, result);

        // // K, V가 생성되었다면 더 이상 생성 되지 않게 하는 방식
        if(authKeyList.get(usersPhone) != null) {
            log.info("이미 인증번호가 생성되어있습니다.");
            return null;
        }
        else {
            log.info("새로운 인증번호 객체를 생성합니다. {} : {}", usersPhone, result);
            authKeyList.put(usersPhone, result);
            savedAuthCode(usersPhone, result);
            return result;
        } 

        // // 계속 K,V 가 생성되어도 마지막 하나의 K 하나의 V만 남기는 방식
        // if(authKeyList.get(usersPhone) != null) {
        //     // log.info("not null: {}",authKeyList.toString());
        //     authKeyList.clear();
        //     authKeyList.put(usersPhone, result);
        //     return result;
        // }
        // else {
        //     log.info("null: {}", authKeyList.toString());
        //     authKeyList.put(usersPhone, result);
        //     return result;
        // }        
    }

    // // Springboot에서 지원하는 schedule 기능
    // @Scheduled(fixedDelay = 600000)
    // public void removeAuthCodePerHour() {
    //     authKeyList.clear();
    // }
    
    /** 
     * @param usersPhone
     * @param code
     */
    public void savedAuthCode(String usersPhone, String code) {
        log.info("[savedAuthCode] 인증코드 객체를 저장합니다.");
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
       return redisTemplate.opsForValue().get(usersPhone);
        // return authKeyList.get(usersPhone);
    }
    
    /** 
     * @param usersPhone
     */
    public void removeAuthCode(String usersPhone) {
        redisTemplate.delete(usersPhone);
        log.info("[savedAuthCode] 서버의 인증정보를 삭제합니다.");
        authKeyList.remove(usersPhone);
    }

}

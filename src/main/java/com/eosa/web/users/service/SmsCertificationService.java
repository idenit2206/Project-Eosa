package com.eosa.web.users.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class SmsCertificationService {

    private static Map<String, String> authKeyList = new HashMap<>();
    @Autowired private RedisTemplate<String, String> redisTemplate;

    public String createCertificationCode(String usersPhone) {
        String result = "";
        while(result.length() <= 5) {
            result += String.valueOf((int)Math.floor(Math.random() * 9));
        }
        log.info("새로운 K, V를 저장합니다.");
        log.debug("{}: {}", usersPhone, result);
        authKeyList.put(usersPhone, result);
        return result;
    }

    public void savedAuthCode(String usersPhone, String code) {
        log.info("redis에 인증정보를 저장합니다.");
        redisTemplate.opsForValue()
            .set(usersPhone, code, Duration.ofSeconds(180));
    }

    public String getAuthCode(String usersPhone) {
        log.info("redis의 인증정보와 비교합니다.");
//        log.info("server의 인증정보를 가져옵니다.");
//        return redisTemplate.opsForValue().get(usersPhone);
        return authKeyList.get(usersPhone);
    }

    public void removeAuthCode(String usersPhone) {
//        log.info("redis의 인증정보를 삭제합니다.");
//        redisTemplate.delete(usersPhone);
        log.info("server의 인증정보를 삭제합니다.");
        authKeyList.remove(usersPhone);
    }

}
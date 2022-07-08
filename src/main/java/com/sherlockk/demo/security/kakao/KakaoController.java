package com.sherlockk.demo.security.kakao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class KakaoController {

    @Autowired
    kakaoService ks;

    @GetMapping("/kakao")
    public String getLoginPage(@RequestParam(value="code") String code) throws Exception {
        String token = ks.getToken(code);
        Map<String, Object> userInfo = ks.getUserInfo(token);

        return "# userInfo: " + userInfo.toString();
    }

}

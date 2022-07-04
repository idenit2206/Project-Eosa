package com.sherlockk.demo.users;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sherlockk.demo.util.CustomResponseData;

import io.swagger.v3.oas.annotations.Operation;
import springfox.documentation.spring.web.json.Json;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value="/api/user")
public class UsersKakaoController {

    private final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UsersService usersService;

    @GetMapping("/oauth/kakao")
    @PostMapping("/oauth/kakao")
    public CustomResponseData kakaoSignIn(
        @RequestParam(value="code") String code
    ) throws Exception {
        CustomResponseData result = new CustomResponseData();
        LocalDateTime currentTime = LocalDateTime.now();
                
        Map<String, String> items = new HashMap<>();
        items.put("code", code);

        String token = getAccessTokenByCode(code);        
        items.put("token", token);

        String userInfo = getKakaoUserInfo(token);
        items.put("userInfo", userInfo);

        result.setStatusCode(HttpStatus.OK.value());
        result.setResultItem(items);
        result.setResponseDateTime(currentTime);

        return result;
    }   

    public String getAccessTokenByCode(String code) {
        // Map<String, String> data = new HashMap<>();
        logger.info("## Requested getAccessTokenByCode()");
        // (이전)참고 URL - https://mangchhe.github.io/springboot/2021/07/18/SpringKakaoLogin/
        // (현재)참고 URL - https://velog.io/@shwncho/Spring-Boot-카카오-로그인-APIoAuth-2.0
        final String CLIENT_ID =  "bc8cb20bc2e3cd4dd484ef5657c1769b";
        final String CLIENT_SECRET = "FfAs3gRxRDQzwbKaMmgHfKukYjWOUBvE";
        
        // 사용자 액세스 토큰
        String access_Token = "";
        // 사용자 리프레시 토큰
        String refesh_Token = "";
        // 액세스 토큰의 유효기간(초)
        String expires_In = "";
        // 사용자 리프레시 토큰 유효기간(초)
        String refesh_Token_Expires_In = "";
        // 인증된 사용자의 정보 조회 권한 범위
        // 범위가 여러개일땐 공백으로 구분
        String scope = "";


        final String reqURL = "https://kauth.kakao.com/oauth/token";        

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            // POST 요청에 필요한 파라미터를 문자열스트림으로 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id="+CLIENT_ID);
            sb.append("&redirect_uri=http://localhost:8081/api/user/oauth/kakao");  
            sb.append("&code="+code);
            sb.append("&client_secret="+CLIENT_SECRET);
            bw.write(sb.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();
            // logger.info("## responseCode {}", responseCode);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while((line = br.readLine()) != null) {
                result += line;
            }
            // logger.info("## responseBody {}", result);

            // JsonParser parser = new JsonParser();
            // JsonElement element = parser.parseString(result);
            JsonElement element = JsonParser.parseString(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            expires_In = element.getAsJsonObject().get("expires_in").getAsString();
            refesh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
            refesh_Token_Expires_In = element.getAsJsonObject().get("refresh_token_expires_in").getAsString();
            scope = element.getAsJsonObject().get("scope").getAsString();

            // logger.info("## access_token {}", access_Token);
            // logger.info("## refresh_token {}", refesh_Token);

            // data.put("access_token", access_Token);
            // data.put("expires_in", expires_In);
            // data.put("refresh_token", refesh_Token);
            // data.put("refresh_token_expires_in", refesh_Token_Expires_In);
            // data.put("scope", scope);

            br.close();
            bw.close();
        }
        catch(Exception e) {
            // logger.error("## Error {}", e);
        }
        return access_Token;
    }

    public String getKakaoUserInfo(String Access_Token) throws Exception {
        logger.info("## Requested getKakaoUserInfo()");
        String data = "";

        final String reqURL = "https://kapi.kakao.com/v2/user/me";       

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + Access_Token);
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");      

            int responseCode = conn.getResponseCode();            
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
            
            while((line = br.readLine()) != null) {
                result += line;
            }
            /**
             * element: {
             *  "id": 정수타입,
             *  "connected_at": 타임스탬프,
             *  "properties": {
             *      "nickname": 문자열
             *  },
             *  "kakao_account": {
             *      "profile_nickname_needs_agreement": true/false,
             *      "profile": {
             *          "nickname": 문자열
             *      },
             *  "has_email": 참/거짓,
             *  "email_needs_agreement": 참/거짓,
             *  "has_age_range": 참/거짓,
             *  "age_range_needs_agreement": 참/거짓,
             *  "has_gender": 참/거짓,
             *  "gender_needs_agreement": 참/거짓
             *  }
             * }
             */
            JsonElement element = JsonParser.parseString(result);
            
            String id = element.getAsJsonObject().get("id").getAsString();
            String nickName = element.getAsJsonObject().get("properties").getAsJsonObject().get("nickname").getAsString();
            // System.out.println("## id & nickName" + id + " " + nickName);

            data = id + " " + nickName;
            System.out.println("## element: " + element);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return data;
    }

}

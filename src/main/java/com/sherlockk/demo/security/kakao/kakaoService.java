package com.sherlockk.demo.security.kakao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.jni.Buffer;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class kakaoService {

    public String getToken(String code) throws IOException {
        String reqURL = "https://kauth.kakao.com/oauth/token";        
        String token = "";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=7d140d1af07d8ac3e6fa79db75e8ebf5");
            sb.append("&client_secret=zejFswQLxSt8rzWT41rx5NTgBulWTPqc");
            sb.append("&redirect_uri=http://localhost:8081/api/user/kakao");
            sb.append("&code="+code);

            bw.write(sb.toString());
            bw.flush();

            int responseCode = connection.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));            

            String line = "";
            String result = "";
            while((line = br.readLine()) != null) {
                result += line;
            }

            JsonParser parser = new JsonParser();
            JsonObject element = (JsonObject) parser.parse(result);

            token = element.get("access_token").toString();
        }
        catch(Exception e) {
            log.error("[ERROR] {}", e);
        }
        return token;
    }
    
    public Map<String, Object> getUserInfo(String token) throws IOException {
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        Map<String, Object> data = new HashMap<>();

        try {
            URL url = new URL(reqURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            int responseCode = connection.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            String result = "";
            while((line = br.readLine()) != null) {
                result += line;
            }

            JsonParser parser = new JsonParser();
            JsonObject object = (JsonObject) parser.parse(result);
            JsonObject account = (JsonObject) object.get("kakao_account");
            JsonObject properties = (JsonObject) object.get("properties");

            data.put("id", object.get("id").toString());
            data.put("account", account);
            data.put("nickname", properties.get("nickname").toString());
            data.put("age", account.get("age_ranget").toString());

            br.close();
        }
        catch(Exception e) {
            
        }        
        return data;
    }
    
}

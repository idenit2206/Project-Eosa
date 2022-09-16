package com.eosa.admin.safety;

import com.eosa.admin.encode.SHA256;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : com.eosa.admin.safety
 * fileName       : Safety
 * author         : Jihun Kim
 * date           : 2022-09-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-16        Jihun Kim       최초 생성
 */
public class Safety {

    /**
     * 안심번호 인코딩
     *
     * @return Map
     * @throws NoSuchAlgorithmException
     */
    public Map<String, String> safetyEncode() throws NoSuchAlgorithmException {

        Map<String, String> map = new HashMap<>();

        SHA256 sha256 = new SHA256();
        LocalDate now = LocalDate.now();

        String id = "project010";
        String monthDay = now.format(DateTimeFormatter.ofPattern("MMdd"));
        String code = "19c08cb968e62bc208523aa9579c49fa8368a191cf9c9a5348a843bab4b3bf00";
        String secureCode = sha256.encrypt(id + monthDay + code);

        map.put("id", id);
        map.put("monthDay", monthDay);
        map.put("secureCode", secureCode);

        return map;
    }

    /**
     * 안심번호 연동 api
     *
     * @param apiUrl
     * @return String
     */
    public String safetyAPI(String apiUrl) {

        // API 호출
        StringBuilder sb = new StringBuilder();
        try{
            URL url = new URL(apiUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            conn.setDoOutput(true);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            while(br.ready()) {
                sb.append(br.readLine());
            }
            conn.disconnect();
        }catch(Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

}

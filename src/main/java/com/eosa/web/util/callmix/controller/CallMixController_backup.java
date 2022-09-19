package com.eosa.web.util.callmix.controller;

import com.eosa.web.util.CustomResponseData;
import com.eosa.web.util.callmix.service.SHA256;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * ORIGIN
 * packageName    : idenit.ansim
 * fileName       : AnsimController
 * author         : Jihun Kim
 * date           : 2022-09-08
 * description    : 메인 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-08        Jihun Kim       최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/callmix")
public class CallMixController_backup {

    @GetMapping("/ansim")
    public CustomResponseData test() throws NoSuchAlgorithmException, IOException {
        CustomResponseData result = new CustomResponseData();

        SHA256 sha256 = new SHA256();
        LocalDate now = LocalDate.now();

        String id = "project010";
        String monthDay = now.format(DateTimeFormatter.ofPattern("MMdd"));
        String code = "19c08cb968e62bc208523aa9579c49fa8368a191cf9c9a5348a843bab4b3bf00";

        String secureCode = sha256.encrypt(id + monthDay + code);
        String requestURL = "https://bizapi.callmix.co.kr/biz050/BZV100";
//                secureCode + "&bizId=" + id + "&monthDay=" + monthDay + "&selGbn=3" +
//                "&seqNo=0" + "&reqCnt=1";
//        String items = "";
        Map<String, Object> items = new HashMap<>();
        JsonObject jsonObject = null;
        String vn = "";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpUriRequest getReq = RequestBuilder.get()
                    .setUri(new URI(requestURL))
                    .addParameter("secureCode", secureCode)
                    .addParameter("bizId", id)
                    .addParameter("monthDay", monthDay)
                    .addParameter("selGbn", "3")
                    .addParameter("seqNo", "0")
                    .addParameter("reqCnt", "1")
                    .build();
            CloseableHttpResponse response = httpClient.execute(getReq);
            String responseString;

            try {
                responseString = EntityUtils.toString(response.getEntity());
                log.info("[ansim] response: {}", responseString);
                JsonParser parser = new JsonParser();
                JsonElement element = parser.parse(responseString);
                jsonObject = element.getAsJsonObject();
                log.debug("[ansim] resCd: {}", jsonObject.get("resCd"));
                JsonArray vnList = jsonObject.get("vnList").getAsJsonArray();
                log.debug("[ansim] vnList: {}", vnList);
                JsonObject vnList0 = vnList.get(0).getAsJsonObject();
                log.debug("[ansim] vnList0: {}", vnList0.toString());
                vn = vnList0.get("vn").getAsString();
            }
            catch(Exception e) {
                log.debug("[ansim] Request Error: {}", e.toString());
            }
            finally {
                response.close();
            }
        }
        catch(Exception e) {
            log.error("[ansim] CloseableHttpClient Error: {}", e.toString());
        }
        finally {
            httpClient.close();
        }
        items.put("vn", vn);

        result.setStatusCode(HttpStatus.SC_OK);
        result.setResultItem(items);
        result.setResponseDateTime(LocalDateTime.now());

        // 리턴해서 받아온 vn으로 안심번호 등록
        // "https://bizapi.callmix.co.kr/biz050/BZV210?secureCode=" + secureCode + "&bizId=" + id + "&monthDay=" + monthDay + "&tkGbn=1&rn=" + "실사용 번호" + &vn= + "가상 번호" + &brNm=&prlgUrl=&eplgUrl=

        // 목록 조회
        // 사용 중이지 않은 목록 1개 가져와서 vn만 추출하면 될듯
//        type String
//        return "redirect:https://bizapi.callmix.co.kr/biz050/BZV100" +
//                "?secureCode=" + secureCode +
//                "&bizId=" + id +
//                "&monthDay=" + monthDay +
//                "&selGbn=3" +
//                "&seqNo=0" +
//                "&reqCnt=1";
        return result;
    }

}

package com.eosa.web.firebase.pushnoti.service;

import java.io.IOException;
import java.util.List;

import com.eosa.web.firebase.pushnoti.entity.FcmMessage;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Component
@RequiredArgsConstructor
public class FirebaseCloudMessage {
    private final String API_URL = "https://fcm.googleapis.com/v1/projects/project-eosa/messages:send";
    private final ObjectMapper objectMapper;

    // 메세지 보내기
    public void sendMessageTo(String targetToken, String body, String link, String device) throws IOException {
        String message = makeMessage(targetToken, body, link, device);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(message,
                MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(API_URL)
                .post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                .build();

        Response response = client.newCall(request).execute();

        System.out.println(response.body().string());
    }

    private String makeMessage(String targetToken, String body, String link, String device) throws JsonParseException, JsonProcessingException {
        FcmMessage fcmMessage = FcmMessage.builder()
                .message(FcmMessage.Message.builder()
                    .token(targetToken)
                    .data(FcmMessage.data.builder()
                            .title("새로운 메세지 도착")
                            .body(body)
                            .link("https://dowajo.co.kr"+link)
                            .build()
                    )
                    .notification(FcmMessage.Notification.builder()
                            .title("새로운 메세지 도착")
                            .body(body)
                            .image(null)
                            .build()
                    ).build()).validateOnly(false).build();
        return objectMapper.writeValueAsString(fcmMessage);           
    }

    // accessToken 생성
    private String getAccessToken() throws IOException {
        String firebaseConfigPath = "firebase/firebase_service_key.json";

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }
    
}

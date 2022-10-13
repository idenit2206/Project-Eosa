package com.eosa.web.firebase.pushnoti;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eosa.web.firebase.pushnoti.entity.FcmMessage;
import com.eosa.web.firebase.pushnoti.entity.PushMessageRequestEntity;
import com.eosa.web.firebase.pushnoti.service.FirebaseCloudMessage;
import com.eosa.web.users.service.UsersService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/push")
public class PushNotiController {

    @Autowired private UsersService usersService;

    private final FirebaseCloudMessage firebaseCloudMessage;

    @PostMapping("/pushMessage")
    public ResponseEntity<?> pushMessage(@RequestBody PushMessageRequestEntity pushMessageRequestEntity) throws IOException {
        log.info("[pushMessage] token: {}, title: {}, body: {}", pushMessageRequestEntity.getToken(), pushMessageRequestEntity.getTitle(), pushMessageRequestEntity.getBody());
        firebaseCloudMessage.sendMessageTo(
            pushMessageRequestEntity.getToken(),
            pushMessageRequestEntity.getBody(),
            pushMessageRequestEntity.getUrl(),
            pushMessageRequestEntity.getDevice());
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/getTokenAndDevice")
    public String getTokenAndDevice(
        @RequestParam(value="token") String token,
        @RequestParam(value="device") String device
    ) {
        log.info("[getToken] token: {} , device: {}", token, device);
        return token + " : " + device;
    }

}

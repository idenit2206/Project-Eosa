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
@RequestMapping("/api/pushNotification")
public class PushNotiController {

    @Autowired 
    private UsersService usersService;

    private final FirebaseCloudMessage firebaseCloudMessage;

    /**
     * 모바일 어플리케이션 환경에서 푸시메시지를 보내기 위한 컨트롤러
     * @param pushMessageRequestEntity
     * @return
     * @throws IOException
     */
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
    
    /**
     * FirebaseCloudMessage (FCM)에 활용할 token 값 device 값을 갱신하는 컨트롤러
     * @param usersIdx
     * @param token
     * @param device
     * @return
     */
    @GetMapping("/getTokenAndDevice")
    public String getTokenAndDevice(
        @RequestParam(value="usersIdx", required = false) Long usersIdx,
        @RequestParam(value="token", required = false) String token,
        @RequestParam(value="device", required = false) String device
    ) {
        log.info("[getTokenAndDevice] usersIdx: {}, token: {} , device: {}",usersIdx, token, device);
        return usersService.getTokenCheck(usersIdx, token, device);
    }

}

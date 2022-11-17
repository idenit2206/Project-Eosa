package com.eosa.web.tempuser.controller;

import com.eosa.web.tempuser.service.TempUserService;
import com.eosa.web.users.entity.Users;
import com.eosa.web.util.CustomResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/tempUser")
public class TempUserController {

    @Autowired private TempUserService tempUserService;

    
    /** 
     * 상담전용 회원이 회원가입을 수행하는 컨트롤러
     * @return CustomResponseData
     */
    @PostMapping("/insertNewTempUser")
    public CustomResponseData insertNewTempUser(
        @RequestBody Users user
    ) {
        CustomResponseData result = new CustomResponseData();
        log.debug("[insertNewTempUser] parameter TempUser: {}", user.toString());
        Users insertRow = tempUserService.save(user);

        if(insertRow != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(insertRow);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

//     /**
//      * 상담전용회원의 로그인을 위한 컨트롤러(하드코딩 버전 22.11.17 기준 사용안함)
//      * @param usersEmail
//      * @return
//     */
//     @PostMapping("/signIn")
//     public CustomResponseData signIn(String usersEmail) {
//         log.info("[signIn] 비회원 usersEmail: {} 님이 로그인을 요청합니다.", usersEmail);
//         CustomResponseData result = new CustomResponseData();
//         Map<String, Object> items = new HashMap<>();

//         Users selectRows = tempUserService.signIn(usersEmail);

//         if(selectRows != null) {
//             log.info("[signIn] 임시사용자 account: {} 가 로그인을 시도합니다.", selectRows.getUsersAccount());

//             result.setStatusCode(HttpStatus.OK.value());
//             result.setResultItem(selectRows);
//             result.setResponseDateTime(LocalDateTime.now());
//         }
//         else {
//             log.error("[signIn]로그인에 실패했습니다.");
// //            log.debug("[signIn]")
//             result.setStatusCode(HttpStatus.OK.value());
//             result.setResultItem(null);
//             result.setResponseDateTime(LocalDateTime.now());
//         }

//         return result;
//     }

}

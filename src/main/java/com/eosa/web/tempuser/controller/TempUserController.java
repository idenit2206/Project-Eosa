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

    @PostMapping("/signIn")
    public CustomResponseData signIn(
        @RequestParam("usersAccount") String usersEmail,
        @RequestParam("usersPass") String usersPass
    ) {
        CustomResponseData result = new CustomResponseData();
        Map<String, Object> items = new HashMap<>();

        Users selectRows = tempUserService.signIn(usersEmail, usersPass);

        if(selectRows != null) {
            items.put("usersIdx", selectRows.getUsersIdx());
            items.put("usersEmail", selectRows.getUsersEmail());
            items.put("usersName", "TempUser"+selectRows.getUsersIdx());
            items.put("usersRole", selectRows.getUsersRole());

            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(items);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

}

package com.eosa.web.tempuser.controller;

import com.eosa.web.tempuser.entity.TempUser;
import com.eosa.web.tempuser.service.TempUserService;
import com.eosa.web.util.CustomResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/tempUser")
public class TempUserController {

    @Autowired private TempUserService tempUserService;

    @PostMapping("/insertNewTempUser")
    public CustomResponseData insertNewTempUser(
        @RequestBody TempUser tempUser
    ) {
        CustomResponseData result = new CustomResponseData();
        log.debug("[insertNewTempUser] parameter TempUser: {}", tempUser.toString());

        TempUser insertRow = tempUserService.save(tempUser);

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

}

package com.eosa.web.users.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eosa.web.users.entity.FindByUsersAccountEntity;
import com.eosa.web.users.service.UsersService;
import com.eosa.web.util.CustomResponseData;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/api/user")
public class MypageController {

    @Autowired private UsersService usersService;

    @PostMapping("/mypage/checkPass")
    public CustomResponseData mypageCheckPass(
        @RequestBody String param
    ) {
        JsonObject object = JsonParser.parseString(param).getAsJsonObject();
        String usersAccount = object.get("usersAccount").getAsString();
        String usersPass = object.get("usersPass").getAsString();
        // log.debug("{}", object.toString());
        // log.debug("{} : {}", usersAccount, usersPass);

        CustomResponseData result = new CustomResponseData();
        
        FindByUsersAccountEntity checkPassResult = usersService.checkMyPageByPass(usersAccount, usersPass);

        if(checkPassResult != null) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(checkPassResult);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.BAD_REQUEST.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }
    
}

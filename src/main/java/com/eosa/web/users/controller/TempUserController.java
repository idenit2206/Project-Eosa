package com.eosa.web.users.controller;

import com.eosa.web.users.entity.TempUser;
import com.eosa.web.users.service.TempUserService;
import com.eosa.web.util.CustomResponseData;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

    @PostMapping("/registTempUser")
    public CustomResponseData registTempUser(
            @RequestBody String param
    ) {
        TempUser entity = new TempUser();
        JsonObject jsonObject = (JsonObject) JsonParser.parseString(param).getAsJsonObject();
        entity.setTempUserEmail(jsonObject.get("associateEmail").getAsString());
        entity.setTempUserPass(jsonObject.get("associatePass").getAsString());
        if(jsonObject.get("associateGender").getAsString().equals("남자")) {
            entity.setTempUserGender(0);
        }else {
            entity.setTempUserGender(1);
        }
        entity.setTempUserAge(jsonObject.get("associateAge").getAsInt());
        entity.setTempUserRegion1(jsonObject.get("usersRegion1").getAsString());
        entity.setTempUserRegion2(jsonObject.get("usersRegion2").getAsString());

        CustomResponseData result = new CustomResponseData();

        TempUser step1 = tempUserService.save(entity);

        if(step1 != null) {
            result.setResultItem(HttpStatus.OK.value());
            result.setResultItem("TRUE");
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setResultItem(HttpStatus.BAD_REQUEST.value());
            result.setResultItem("FALSE");
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

}

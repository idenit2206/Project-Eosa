package com.eosa.web.requestform;

import java.time.LocalDateTime;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eosa.web.util.CustomResponseData;
import com.eosa.web.util.NullCheck;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/requestForm")
public class RequestFormController {

    @Autowired private RequestFormService requestFormService;

    @PostMapping("/requestRegister")
    public CustomResponseData requestRegister(
        RequestForm param
    ) {
        CustomResponseData result = new CustomResponseData();
        LocalDateTime currentTime = LocalDateTime.now();

        String[] targets = {
            "usersIdx", "companysIdx", "requestFormCategory", "requestFormRegion1", "requestFormRegion2"
        };

        NullCheck nullcheck = new NullCheck();
        Map<String, Object> items = nullcheck.ObjectNullCheck(param, targets);

        if(items.get("result") == "SUCCESS") {
            int transaction = requestFormService.requestFormSave(param);
            if(transaction == 1) {
                log.info("[Success] {} request registrer", param.getIdx());
                result.setStatusCode(HttpStatus.OK.value());
                result.setResultItem(items);
                result.setResponseDateTime(currentTime);
            }
            else {
                result.setStatusCode(HttpStatus.BAD_REQUEST.value());
                result.setResultItem("SQL ERROR /request");
                result.setResponseDateTime(currentTime);
            }
        }
        else {
            log.error("Failure " + param.getIdx() + " has /request");
            result.setStatusCode(HttpStatus.BAD_REQUEST.value());
            result.setResultItem(items);
            result.setResponseDateTime(currentTime);
        }
        return result;
    }
}

package com.eosa.web.requestform;

import java.time.LocalDateTime;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eosa.web.util.CustomResponseData;
import com.eosa.web.util.NullCheck;

@RestController
@RequestMapping("/api/requestform")
public class RequestFormController {
    
    private Logger logger = LoggerFactory.getLogger(RequestFormController.class);

    @Autowired
    private RequestFormService requestFormService;

    @PostMapping("/request")
    public CustomResponseData requestEvent(
        RequestForm param
    ) {
        CustomResponseData result = new CustomResponseData();
        LocalDateTime currentTime = LocalDateTime.now();

        String[] targets = {
            "usersIdx", "companysIdx", "requestFormCategory", "requestFormRegion1", "requestFormRegion2"
        };
        logger.info("# {}", param.toString());

        NullCheck nullcheck = new NullCheck();
        Map<String, Object> items = nullcheck.ObjectNullCheck(param, targets);

        if(items.get("result") == "SUCCESS") {
            int transaction = requestFormService.requestFormSave(param);
            if(transaction == 1) {
                logger.info("Success " + param.getIdx() + " has Requested!!");
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
            logger.error("Failure " + param.getIdx() + " has /request");
            result.setStatusCode(HttpStatus.BAD_REQUEST.value());
            result.setResultItem(items);
            result.setResponseDateTime(currentTime);
        }
        return result;
    }
}

package com.sherlockk.demo.users;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sherlockk.demo.util.CustomResponseData;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value="/api/user")
public class UsersSNSController {

    private final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UsersService usersService;

    /**
     * 테스트를 위한 메서드입니다.
     * @param status HttpStatusCode 관련 테스트를 위한 매개변수
     * @param req HttpServletRequest 관련 테스트를 위한 매개변수
     * @param res HttpServletResponse 관련 테스트를 위한 매개변수
     * @return {statusCode, resultItem, currentTime}
     */
    @Operation(summary = "/test02", description = "테스트용 입니다.")
    @GetMapping("/test02")
    public CustomResponseData test02(
        HttpStatus status, HttpServletRequest req, HttpServletResponse res
    ) {      
        CustomResponseData result = new CustomResponseData();
        LocalDateTime currentTime = LocalDateTime.now();

        int code = status.FAILED_DEPENDENCY.value();
        Map<Integer, String> item = new HashMap<>();        
        item.put(1, "AAA");
        item.put(2, "BBB");

        result.setStatusCode(code);
        result.setResultItem(item);
        result.setResponseDateTime(currentTime);

        return result;
    }

    @GetMapping("/oauth/kakao")
    public CustomResponseData kakaoSignIn(
        @RequestParam(value="code") String code
    ) {
        CustomResponseData result = new CustomResponseData();
        LocalDateTime currentTime = LocalDateTime.now();
        
        Map<String, String> items = new HashMap<>();
        items.put("code", code);

        result.setStatusCode(HttpStatus.OK.value());
        result.setResultItem(items);
        result.setResponseDateTime(currentTime);

        return result;
    }


}

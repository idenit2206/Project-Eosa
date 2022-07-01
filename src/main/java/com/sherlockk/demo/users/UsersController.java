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
public class UsersController {

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
    @Operation(summary = "/test01", description = "테스트용 입니다.")
    @GetMapping("/test01")
    public CustomResponseData test01(
        HttpStatus status, HttpServletRequest req, HttpServletResponse res
    ) {      
        CustomResponseData result = new CustomResponseData();
        LocalDateTime currentTime = LocalDateTime.now();

        int code = status.FAILED_DEPENDENCY.value();
        Map<Integer, String> item = new HashMap<>();        
        item.put(1, "넌 못지나간다.");
        item.put(2, "가고 싶은데로 간다!");

        result.setStatusCode(code);
        result.setResultItem(item);
        result.setResponseDateTime(currentTime);

        return result;
    }

    /**
     * 회원가입이시 데이터가 저장되는 메서드 입니다.
     * @param req
     * @param param
     * @return
     */
    @Operation(summary="회원가입 Api", description="회원가입시 DB트랜잭션을 수행합니다")
    @PostMapping("/signUp.do")
    public CustomResponseData doSignUp(HttpServletRequest req, Users param) {
        String requester = req.getLocalAddr();
        logger.info("{} has \"/signUp.do\" Request", requester);
        CustomResponseData result = new CustomResponseData();
        LocalDateTime currentTime = LocalDateTime.now();

        if(usersService.userSave(param) == 1) {
            result.setStatusCode(HttpStatus.ACCEPTED.value());
            result.setResultItem(param.getUsersAccount() + " 가입 완료");
            result.setResponseDateTime(currentTime);
        }
        else {
            result.setStatusCode(HttpStatus.BAD_REQUEST.value());
            result.setResultItem(param.getUsersAccount() + " 가입에 실패했습니다.");
            result.setResponseDateTime(currentTime);
        }

        return result;
    }

    // @PostMapping("/signIn.do")
    // public CustomResponseData signInDo(
    //     HttpServletRequest req, @Param("usersAccount") String usersAccount, @Param("usersPass") String usersPass
    // ) {
    //     String requester = req.getLocalAddr();
    //     logger.info("{} has \"/signIn\" Request", requester);
    //     CustomResponseData result = new CustomResponseData();
    //     LocalDateTime currentTime = LocalDateTime.now();
    //     String[] data = {usersAccount, usersPass};
        
    //     if(!data[0].isEmpty()) {
    //         result.setStatusCode(HttpStatus.OK.value());
    //         result.setResultItem(data);
    //         result.setResponseDateTime(currentTime);
    //     }
    //     else {
    //         result.setStatusCode(HttpStatus.BAD_REQUEST.value());
    //         result.setResultItem(data);
    //         result.setResponseDateTime(currentTime);
    //     }   

    //     return result;
    // }

    /**
     * 로그인에 성공했을 때 작동하는 메서드입니다.
     * @param usersAccount
     * @return userInformation
     */    
    @Operation(summary="/signIn 성공", description="signIn에 성공했을 때 작동하는 메서드 입니다.")
    @PostMapping(value="/signIn.success")
    public CustomResponseData signInSuccess(
        HttpServletRequest req,
        @RequestParam(value="usersAccount") String usersAccount
    ) {
        String requester = req.getLocalAddr();
        logger.info("{} has \"/signIn.success\" Request", requester);
        CustomResponseData result = new CustomResponseData();
        LocalDateTime currentTime = LocalDateTime.now();
        
        Map<String, String> items = new HashMap<>();
        items.put("message", "Welcome");
        items.put("usersName", usersAccount);

        result.setStatusCode(HttpStatus.OK.value());
        result.setResultItem(items);
        result.setResponseDateTime(currentTime);

        return result;
    }

    /**
     * 로그인에 실패했을 때 작동하는 메서드입니다.
     * @param usersAccount
     * @return userInformation
     */    
    @Operation(summary="/signIn 실패", description="signIn에 실패했을 때 작동하는 메서드 입니다.")
    @PostMapping(value="/signIn.failure")
    public CustomResponseData signInfailure(
        HttpServletRequest req,
        @RequestParam(value="usersAccount") String usersAccount
    ) {
        String requester = req.getLocalAddr();
        logger.info("{} has \"/signIn.failure\" Request", requester);
        CustomResponseData result = new CustomResponseData();
        LocalDateTime currentTime = LocalDateTime.now();

        Map<String, String> items = new HashMap<>();
        items.put("message", "Sorry");
        items.put("usersName", usersAccount);

        result.setStatusCode(HttpStatus.BAD_REQUEST.value());
        result.setResultItem(items);
        result.setResponseDateTime(currentTime);

        return result;
    }
    

}

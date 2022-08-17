package com.eosa.web.users;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eosa.security.CustomPrincipalDetails;
import com.eosa.web.users.entity.FindByUsersAccountEntity;
import com.eosa.web.users.entity.SelectByUsersAccountEntity;
import com.eosa.web.util.CustomResponseData;
import com.eosa.web.util.NullCheck;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.spring.web.json.Json;


@Slf4j
@RestController
@RequestMapping(value="/api/user")
public class UsersController {
 
    private NullCheck nullCheck = new NullCheck();

    @Autowired private UsersService usersService;

    @GetMapping("/sign/test01")
    public String test01() {
        return "/api/user/test01";
    }

    /**
     * 회원가입이시 데이터가 저장되는 메서드 입니다.
     * @param req
     * @param param
     * @return
     * @throws JSONException
     * @throws ParseException
     */
    @Operation(summary="회원가입 Api", description="회원가입시 DB트랜잭션을 수행합니다")
    @PostMapping("/sign/signUp.do")
    public CustomResponseData doSignUp(
        HttpServletRequest req, 
        @RequestBody String param
    ) throws JSONException, ParseException {
        String requester = req.getLocalAddr();
        JsonObject element = JsonParser.parseString(param).getAsJsonObject().get("info").getAsJsonObject();       
        log.info("[REQUEST] doSignUp from {}", requester);
        Users paramUsers = new Users();
            paramUsers.setUsersAccount(element.get("usersAccount").getAsString());
            paramUsers.setUsersPass(element.get("usersPass").getAsString());
            paramUsers.setUsersName(element.get("usersName").getAsString());
            paramUsers.setUsersNick(element.get("usersNick").getAsString());
            paramUsers.setUsersPhone(element.get("usersPhone").getAsString());
            paramUsers.setUsersEmail(element.get("usersEmail").getAsString());
            paramUsers.setUsersRole(element.get("usersRole").getAsString().toUpperCase());
            paramUsers.setUsersAge(element.get("usersAge").getAsInt());
            paramUsers.setUsersRegion1(element.get("usersRegion1").getAsString());
            paramUsers.setUsersRegion2(element.get("usersRegion2").getAsString());
            if(element.get("usersGender").getAsString().matches("남자")) {
                paramUsers.setUsersGender(0);
            } else {
                paramUsers.setUsersGender(1);
            }
            // paramUsers.setUsersGender(element.get("usersGender").getAsInt());
            if(element.get("usersNotice").getAsString().matches("true")) {
                paramUsers.setUsersNotice(1);
            } else {
                paramUsers.setUsersNotice(0);
            }
            // paramUsers.setUsersNotice(element.get("usersNotice").getAsInt());
        // log.debug("paramUsers: {}", paramUsers.toString());       

        CustomResponseData result = new CustomResponseData();
        
        String[] targets = {"usersAccount", "usersPass", "usersName", "usersNick", "usersPhone", "usersEmail", "usersRole", "usersAge", "usersRegion1", "usersRegion2", "usersGender", "usersNotice"};        
        Map<String, Object> checkItem = nullCheck.ObjectNullCheck(paramUsers, targets);
        
        if(checkItem.get("result") == "SUCCESS") {
            int transaction = usersService.userSave(paramUsers);
            if(transaction == 1) {
                log.info("Success " + paramUsers.getUsersAccount() + " 's Join");                
                result.setStatusCode(HttpStatus.OK.value());
                result.setResultItem(checkItem);
                result.setResponseDateTime(LocalDateTime.now());
            }
            else {
                result.setStatusCode(HttpStatus.BAD_REQUEST.value());
                result.setResultItem("SQL ERROR /signUp.do");
                result.setResponseDateTime(LocalDateTime.now());
            }
        }
        else {
            log.error("Failure " + paramUsers.getUsersAccount() + " 's Join");
            result.setStatusCode(HttpStatus.BAD_REQUEST.value());
            result.setResultItem(checkItem);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    @GetMapping("/sign/usersAccountDupliCheck")
    public CustomResponseData usersAccountDupliCheck(
        @RequestParam("usersAccount") String usersAccount
    ) {
        CustomResponseData result = new CustomResponseData();
        int dupliCheckResult = usersService.usersAccountDupliCheck(usersAccount);

        return result;
    }

    /**
     * 로그인에 성공했을 때 작동하는 메서드입니다. (Spring Security formLogin()을 통해 로그인을 할때 사용하는 메서드)
     * @param usersAccount
     * @return userInformation
     * @throws IOException
     * @throws ServletException
     */    
    @Operation(summary="/signIn 성공", 
    description=
        "signIn에 성공했을 때 작동하는 메서드 입니다.\n" + 
        "성공하면 userInfo라는 이름으로 해당 사용자의 정보를 전송합니다."
    )
    @PostMapping(value="/sign/signIn.success")
    public CustomResponseData signInSuccess(
        HttpServletRequest req,
        HttpServletResponse res,
        @RequestParam(value="usersAccount") String usersAccount
    ) throws IOException, ServletException {
        String requester = req.getLocalAddr();
        log.info("[OK] {} signIn Success FROM {}",usersAccount, requester);
        CustomResponseData result = new CustomResponseData();
        LocalDateTime currentTime = LocalDateTime.now();
        
        SelectByUsersAccountEntity userInfo = usersService.selectByUsersAccount(usersAccount);

        Map<String, Object> items = new HashMap<>();
        items.put("message", "Welcome");
        items.put("userInfo", userInfo);

        result.setStatusCode(HttpStatus.OK.value());
        result.setResultItem(items);
        result.setResponseDateTime(currentTime);

        return result;
    }

    /**
     * 로그인에 실패했을 때 작동하는 메서드입니다. (Spring Security formLogin()을 통해 로그인을 할때 사용하는 메서드)
     * @param usersAccount
     * @return userInformation
     */    
    @Operation(summary="/signIn 실패", description="signIn에 실패했을 때 작동하는 메서드 입니다.")
    @PostMapping(value="/sign/signIn.failure")
    public CustomResponseData signInfailure(
        HttpServletRequest req,
        @RequestParam(value="usersAccount") String usersAccount
    ) {
        String requester = req.getLocalAddr();
        log.info("[ERROR] {} signIn Failure FROM {}",usersAccount, requester);
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

    /**
     * OAuth2를 활용한 SNS로그인 성공시 메서드 
     * @param principalUserDetails
     * @return
     * @throws IOException
     * @throws ServletException
     */
    @GetMapping("/sign/oauth2SignIn.success")
    public void oauth2SignInSuccess(
        HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,
        @AuthenticationPrincipal CustomPrincipalDetails principalUserDetails
    ) throws IOException, ServletException {
        log.debug("SNS: {}", principalUserDetails.toString());
        CustomResponseData result = new CustomResponseData();
        Map<String, Object> items = new HashMap<>();
            
        String sns = principalUserDetails.getProvider();
        String usersAccount = principalUserDetails.getUsername();
        String usersEmail = principalUserDetails.getUsers().getUsersEmail();
        String usersRole = principalUserDetails.getUsers().getUsersRole(); 
        // log.debug("# sns:{}, usersEmail: {}, usersRole: {}",sns, usersEmail, usersRole);       
       

        // Users user = usersService.selectByUsersEmail(usersEmail);
        // String existUsersEmail = "";
        // if(user != null) {
        //     existUsersEmail = user.getUsersEmail();            
        //     items.put("sns", sns);
        //     items.put("usersAccount", usersAccount);
        //     items.put("usersRole", usersRole);   
        // }
        // else {
        //     log.info("신규회원");
        //     items.put("sns", sns);
        //     items.put("usersEmail", usersEmail);
        //     items.put("usersRole", null);
        //     items.put("message", "해당 회원은 신규회원입니다.");
        // }

        // result.setStatusCode(HttpStatus.OK.value());
        // result.setResultItem(items);
        // result.setResponseDateTime(LocalDateTime.now());

        // redirectAttributes.addAttribute("email", usersEmail);

        // Cookie cookie_sns = new Cookie("sns", sns);
        // Cookie cookie_account = new Cookie("account", usersAccount);
        // Cookie cookie_role = new Cookie("role", usersRole);

        // cookie_sns.setPath("/");
        // cookie_account.setPath("/");
        // cookie_role.setPath("/");
       
        // response.addCookie(cookie_sns);
        // response.addCookie(cookie_account);
        // response.addCookie(cookie_role);

        response.sendRedirect("http://localhost:3000/");

        // return result;
    }

    @GetMapping("/sign/oauth2SignIn.failure")
    public void oauth2SignInFailure(
        @AuthenticationPrincipal CustomPrincipalDetails principalUserDetails,
        HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        String platform = principalUserDetails.getProvider();
        String usersEmail = principalUserDetails.getUsername();
        log.info("# {}의 {} 계정을 활용한 로그인에 실패했습니다,", usersEmail, platform);
        response.sendRedirect("http://localhost:3000/");
        // response.setContentType("text/html; charset=UTF-8");
        // PrintWriter out = response.getWriter();
        // out.println("<script>alert(" + 
        // platform + " '계정을 활용한 로그인에 실패했습니다.'); location.href='http://localhost:3000/user/signin' " + 
        // "</script>");
        // response.sendRedirect("http://localhost:3000/user/signin");
    }

    /**
     * 사용자가 계정을 분실했을 때 활용되는 메서드 입니다.
     * 사용자의 회원가입 당시 등록한 이메일을 활용해 계정정보를 찾습니다.
     * @param usersEmail
     * @return
     */
    @Operation(summary="사용자가 계정을 분실했을 때 활용되는 메서드")
    @GetMapping("/sign/checkAccountByUsersEmail")
    public CustomResponseData checkAccountByUsersEmail(
        @RequestParam("usersEmail") String usersEmail
    ) {
        log.debug("# Requestsed Email: {}", usersEmail);
        CustomResponseData result = new CustomResponseData();

        int transaction = usersService.checkAccountByUsersEmail(usersEmail);

        if(transaction == 1) {
            log.info("# {} 은 존재하는 회원입니다.", usersEmail);
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem("회원정보가 존재합니다.");
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            log.error("# {} 과 일치하는 회원 정보가 없습니다.", usersEmail);
            result.setStatusCode(HttpStatus.NO_CONTENT.value());
            result.setResultItem("일치하는 회원정보가 없습니다.");
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    @PostMapping("/checkMyPagePass")
    public CustomResponseData checkMyPagePass(
        @RequestParam("usersAccount") String usersAccount,
        @RequestParam("usersPass") String usersPass
    ) {
        CustomResponseData result = new CustomResponseData();
        log.debug("# usersAccount : usersPass -> {} : {}", usersAccount, usersPass);
        FindByUsersAccountEntity transaction = usersService.checkMyPageByPass(usersAccount, usersPass);
        // log.debug("# UserInfoByUsersAccount {}", transaction.getUsersAccount());
        
        if(transaction.getUsersAccount().equals(usersAccount)) {
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem(transaction);
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            result.setStatusCode(HttpStatus.BAD_REQUEST.value());
            result.setResultItem(null);
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
    }

    // @Operation(summary="회원정보 조회")
    // @GetMapping(value="/getUsersInfo")
    // public CustomResponseData getUsersInfoByUsersAccount(
    //     @RequestParam(value="usersAccount") String usersAccount
    // ) {
    //     log.info("## Someone request {} 's information", usersAccount);
    //     CustomResponseData result = new CustomResponseData();

    //     Map<String, Object> item = new HashMap<>();
    //     LocalDateTime currentTime = LocalDateTime.now();
        
    //     boolean nc = nullCheck.StringNullCheck(usersAccount);       

    //     if(nc == false) {
    //         FindByUsersAccount transaction = usersService.selectByUsersAccount(usersAccount);
    //         if(transaction == null) {
    //             result.setStatusCode(HttpStatus.BAD_REQUEST.value());
    //             item.put("item", transaction);
    //             result.setResultItem(item);
    //             result.setResponseDateTime(currentTime);
    //         }
    //         else {
    //             result.setStatusCode(HttpStatus.OK.value());
    //             item.put("item", transaction);
    //             result.setResultItem(item);
    //             result.setResponseDateTime(currentTime);
    //         }
    //     }        
    //     else {
    //         result.setStatusCode(HttpStatus.BAD_REQUEST.value());
    //         item.put("result", "FAILURE cause parameter(usersAccount) Null");
    //         result.setResultItem(item);
    //         result.setResponseDateTime(currentTime);
    //     }
    //     return result;
    // }

    /**
     * 회원정보를 수정하는 url입니다.
     * @param Users param
     * @return
     */
    @Operation(summary = "회원정보 수정", description = "회원이 스스로 회원정보를 수정할때 사용합니다.")
    @PutMapping("/updateUserInfo")
    public CustomResponseData updateUserInfo(Users param) {
        CustomResponseData result = new CustomResponseData();
        LocalDateTime currentTime = LocalDateTime.now();
        
        String[] targets = {
            "usersIdx", "usersPass", "usersNick", "usersEmail", 
            "usersRegion1", "usersRegion2", "usersGender"
        };
        Map<String, Object> checkItem = nullCheck.ObjectNullCheck(param, targets);        

        if(checkItem.get("result") == "SUCCESS") {
            int transaction = usersService.updateUserInfo(param);
            if(transaction == 1) {
                log.info("Success " + param.getUsersAccount() + " information Update!!");                
                result.setStatusCode(HttpStatus.OK.value());
                result.setResultItem(checkItem);
                result.setResponseDateTime(currentTime);
            }
            else {
                result.setStatusCode(HttpStatus.BAD_REQUEST.value());
                result.setResultItem("SQL ERROR /updateUserInfo");
                result.setResponseDateTime(currentTime);
            }
        }
        else {
            log.error("Failure " + param.getUsersAccount() + " information Update...");
            result.setStatusCode(HttpStatus.BAD_REQUEST.value());
            result.setResultItem(checkItem);
            result.setResponseDateTime(currentTime);
        }       

        return result;
    }

    /**
     * 회원 탈퇴를 요청하는 url입니다.
     * @param usersIdx
     * @return
     */
    @Operation(summary="회원탈퇴", description="회원이 직접 서비스로부터 회원탈퇴를 신청합니다.")
    @PutMapping("/deleteUserInfo")
    public CustomResponseData deleteUserInfo(@Param("usersIdx") Long usersIdx) {
        CustomResponseData result = new CustomResponseData();
        Map<String, Object> item = new HashMap<>();
        LocalDateTime currentTime = LocalDateTime.now();        
       
        int transaction = usersService.deleteUserInfo(usersIdx);

        if(transaction == 1) {
            result.setStatusCode(HttpStatus.OK.value());
            item.put("message", "Success Delete Users idx=" + Long.valueOf(usersIdx));
            result.setResultItem(item);
            result.setResponseDateTime(currentTime);
        }
        else {
            result.setStatusCode(HttpStatus.BAD_REQUEST.value());
            item.put("message", "Failure Delete Users idx=" + Long.valueOf(usersIdx) );
            result.setResultItem(item);
            result.setResponseDateTime(currentTime);
        }
        return result;
    }

    
}

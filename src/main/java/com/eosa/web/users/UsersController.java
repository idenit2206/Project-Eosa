package com.eosa.web.users;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eosa.web.security.CustomPrincipalDetails;

import com.eosa.web.users.userinfo.SelectByUsersAccount;
import com.eosa.web.util.CustomResponseData;
import com.eosa.web.util.NullCheck;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping(value="/api/user")
public class UsersController {

    private NullCheck nullCheck = new NullCheck();

    @Autowired private UsersService usersService;    

    /**
     * 회원가입이시 데이터가 저장되는 메서드 입니다.
     * @param req
     * @param param
     * @return
     */
    @Operation(summary="회원가입 Api", description="회원가입시 DB트랜잭션을 수행합니다")
    @PostMapping("/signUp.do")
    public CustomResponseData doSignUp(
        HttpServletRequest req, 
        Users param
    ) {
        String requester = req.getLocalAddr();
        log.info("{} has \"/signUp.do\" Request", requester);
        CustomResponseData result = new CustomResponseData();
        LocalDateTime currentTime = LocalDateTime.now();
        
        String[] targets = {"usersAccount", "usersPass", "usersName", "usersNick", "usersPhone", "usersEmail", "usersRole", "usersAge", "usersRegion1", "usersRegion2", "usersGender", "usersNotice"};        
        Map<String, Object> checkItem = nullCheck.ObjectNullCheck(param, targets);
        
        if(checkItem.get("result") == "SUCCESS") {
            int transaction = usersService.userSave(param);
            if(transaction == 1) {
                log.info("Success " + param.getUsersAccount() + " 's Join");                
                result.setStatusCode(HttpStatus.OK.value());
                result.setResultItem(checkItem);
                result.setResponseDateTime(currentTime);
            }
            else {
                result.setStatusCode(HttpStatus.BAD_REQUEST.value());
                result.setResultItem("SQL ERROR /signUp.do");
                result.setResponseDateTime(currentTime);
            }
        }
        else {
            log.error("Failure " + param.getUsersAccount() + " 's Join");
            result.setStatusCode(HttpStatus.BAD_REQUEST.value());
            result.setResultItem(checkItem);
            result.setResponseDateTime(currentTime);
        }

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
    @PostMapping(value="/signIn.success")
    public CustomResponseData signInSuccess(
        HttpServletRequest req,
        HttpServletResponse res,
        @RequestParam(value="usersAccount") String usersAccount
    ) throws IOException, ServletException {
        String requester = req.getLocalAddr();
        log.info("[OK] {} signIn Success FROM {}",usersAccount, requester);
        CustomResponseData result = new CustomResponseData();
        LocalDateTime currentTime = LocalDateTime.now();
        
        SelectByUsersAccount userInfo = usersService.selectByUsersAccount(usersAccount);

        // log.info("## [REQUEST] {}", req.toString());
        // log.info("## [RESPONSE] {}", res.getOutputStream());

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
    @PostMapping(value="/signIn.failure")
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

    @GetMapping(value="/signOut.success")
    public CustomResponseData signOutSuccess(
        @RequestParam(value="usersAccount") String usersAccount
    ) {
        log.info("# SignOut {}", usersAccount);
        CustomResponseData result = new CustomResponseData();

        Map<String, Object> item = new HashMap<>();
        item.put("message", usersAccount + "signOut Complete See ya");

        result.setResultItem(item);

        return result;
    }

    @GetMapping("/OAuth2/Signin")
    public void OAuth2Signin(
        HttpServletRequest request, HttpServletResponse response,
        @RequestParam("provider") String provider
    ) {
        log.debug("# {} Request {} platform login",request.getLocalAddr(), provider);
        CustomSnsService cSS = new CustomSnsService(provider);
        log.debug(cSS.getProvider());
        //  //  참고 https://suyeoniii.tistory.com/79
    }    

    /**
     * OAuth2를 활용한 SNS로그인 성공시 메서드 
     * @param principalUserDetails
     * @return
     * @throws IOException
     * @throws ServletException
     */
    @GetMapping("/oauth2SignIn.success")
    public void oauth2SignInSuccess(
        HttpServletRequest request, HttpServletResponse response, HttpSession session,
        @AuthenticationPrincipal CustomPrincipalDetails principalUserDetails
    ) throws IOException, ServletException {
        CustomResponseData result = new CustomResponseData();
        Map<String, Object> items = new HashMap<>();
            
        String sns = principalUserDetails.getProvider();
        String usersEmail = principalUserDetails.getUsers().getUsersEmail();
        String usersRole = principalUserDetails.getUsers().getUsersRole();
        // log.debug("# sns:{}, usersEmail: {}, usersRole: {}",sns, usersEmail, usersRole);
        Users user = usersService.selectByUsersEmail(usersEmail);
        String existUsersEmail = "";
        if(user != null) {
            existUsersEmail = user.getUsersEmail();            
            items.put("sns", sns);
            items.put("usersEmail", existUsersEmail);
            items.put("usersRole", usersRole);   
        }
        else {
            items.put("sns", sns);
            items.put("usersEmail", usersEmail);
            items.put("usersRole", null);
            items.put("message", "해당 회원은 신규회원입니다.");
        }

        result.setStatusCode(HttpStatus.OK.value());
        result.setResultItem(items);
        result.setResponseDateTime(LocalDateTime.now()); 

        Cookie cookie_sns = new Cookie("sns", sns);
        Cookie cookie_email = new Cookie("email", usersEmail);
        Cookie cookie_role = new Cookie("role", usersRole);

        cookie_sns.setPath("/");
        cookie_email.setPath("/");
        cookie_role.setPath("/");
       
        response.addCookie(cookie_sns);
        response.addCookie(cookie_email);
        response.addCookie(cookie_role);

        response.sendRedirect("http://localhost:3000/");

        // return result;    
    }

    @GetMapping("/oauth2SignIn.failure")
    public void oauth2SignInFailure(
        @AuthenticationPrincipal CustomPrincipalDetails principalUserDetails,
        HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        String platform = principalUserDetails.getProvider();
        String usersEmail = principalUserDetails.getUsername();
        log.info("# {}의 {} 계정을 활용한 로그인에 실패했습니다,", usersEmail, platform);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert(" + 
        platform + " '계정을 활용한 로그인에 실패했습니다.'); location.href='http://localhost:3000/user/signin' " + 
        "</script>");
        // response.sendRedirect("http://localhost:3000/user/signin");
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

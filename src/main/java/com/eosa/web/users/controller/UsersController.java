package com.eosa.web.users.controller;

import java.io.IOException;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eosa.web.users.entity.*;
import com.eosa.web.users.service.SmsCertificationService;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eosa.security.CustomPrincipalDetails;
import com.eosa.web.users.service.TerminateUserService;
import com.eosa.web.users.service.UsersService;
import com.eosa.web.util.CustomResponseData;
import com.eosa.web.util.NullCheck;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.service.DefaultMessageService;


@Slf4j
@RestController
@RequestMapping(value="/api/user")
public class UsersController {
 
    private NullCheck nullCheck = new NullCheck();
    private String myHostName = new InternetAddress().getAddress();

    final DefaultMessageService messageService;
    public UsersController() {
        this.messageService = NurigoApp.INSTANCE.initialize("NCSVBBUZQHJ2IJW8", "SL2MVRXGWZB7KQODM6XHMLZSPMQFDWZP", "https://api.coolsms.co.kr");
    }
    private static Map<String, Object> snsAuthKeyList = new HashMap<>();

    @Autowired private SmsCertificationService smsCertificationService;
    @Autowired private UsersService usersService;
    @Autowired private TerminateUserService terminateUserService;

    @Value("${my.service.domain}") private String myDomain;
    @Value("${my.ui.port}") private String myUiPort;

    @GetMapping("/sign/test01")
    public String test01() throws UnknownHostException {
        // String hostAddress = InetAddress.getLocalHost().getHostAddress();
        // return hostAddress + "/api/user/test01";
        return myDomain; 
    }
    @PostMapping(value="/sign/sendPhoneCheckMessage")
    public void sendOne(@RequestParam("usersPhone") String usersPhone) {
        CustomResponseData result = new CustomResponseData();
        String senderPhone = "01071899972";

         Message message = new Message();
         String authCode = smsCertificationService.createCertificationCode(usersPhone);
//       //  발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
         message.setFrom("01071899972"); // 발신번호
         message.setTo(usersPhone);  // 수신번호
         message.setText("어사 회원가입 핸드폰 인증 단계입니다.\n다음의 번호를 입력해주세요.\n"+authCode); // 발신내용
        log.debug("[sendOne] authCode: {}", authCode);
         SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
//       //  smsCertificationService.savedAuthCode(usersPhone, authCode);
    }

    @PostMapping(value="/sign/checkMyPhone")
    public CustomResponseData checkMyPhone(
        @RequestParam("usersPhone") String usersPhone,
        @RequestParam("passKey")String passKey
    ) {
        CustomResponseData result = new CustomResponseData();
        String storedAuthKey = smsCertificationService.getAuthCode(usersPhone);
        if(storedAuthKey.equals(passKey)) {
            log.debug("authKey가 일치합니다.");
            smsCertificationService.removeAuthCode(usersPhone);
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem("TRUE");
            result.setResponseDateTime(LocalDateTime.now());
        }
        else {
            log.debug("authKey가 불일치합니다.");
            result.setStatusCode(HttpStatus.OK.value());
            result.setResultItem("FALSE");
            result.setResponseDateTime(LocalDateTime.now());
        }

        return result;
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
        JsonObject jsonObject = (JsonObject) JsonParser.parseString(param).getAsJsonObject();
        // log.info("{}", jsonObject.toString());
        log.info("[REQUEST] doSignUp from {}, usersAccount: {}", requester, jsonObject.get("usersAccount").getAsString());
        Users paramUsers = new Users();
            paramUsers.setUsersAccount(jsonObject.get("usersAccount").getAsString().toLowerCase());
            paramUsers.setUsersPass(jsonObject.get("usersPass").getAsString());
            paramUsers.setUsersName(jsonObject.get("usersName").getAsString());
            paramUsers.setUsersNick(jsonObject.get("usersNick").getAsString());
            paramUsers.setUsersPhone(jsonObject.get("usersPhone").getAsString());
            paramUsers.setUsersEmail(jsonObject.get("usersEmail").getAsString());
            paramUsers.setUsersRole(jsonObject.get("usersRole").getAsString().toUpperCase());
            String prevUsersAge = jsonObject.get("usersAge").getAsString();
            int usersAge = Integer.parseInt(prevUsersAge.substring(0, 2));
            paramUsers.setUsersAge(usersAge);
            paramUsers.setUsersRegion1(jsonObject.get("usersRegion1").getAsString());
            paramUsers.setUsersRegion2(jsonObject.get("usersRegion2").getAsString());
            if(jsonObject.get("usersGender").getAsString().equals("남자")) {
                paramUsers.setUsersGender(0);
            } else {
                paramUsers.setUsersGender(1);
            }
            // paramUsers.setUsersGender(element.get("usersGender").getAsInt());
            if(jsonObject.get("usersNotice").getAsString().equals("true")) {
                paramUsers.setUsersNotice(1);
            } else {
                paramUsers.setUsersNotice(0);
            }
            paramUsers.setProvider(jsonObject.get("provider").getAsString());
            paramUsers.setUsersProfile(jsonObject.get("picture").getAsString());
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
        String item = "";
        Users entity = usersService.usersAccountDupliCheck(usersAccount);
        if(entity == null) {
            item = usersAccount;
        }
        
        result.setStatusCode(HttpStatus.OK.value());
        result.setResultItem(item);
        result.setResponseDateTime(LocalDateTime.now());
        return result;
    }

    @GetMapping("/sign/usersEmailDupliCheck")
    public CustomResponseData usersEmailDupliCheck(
        @RequestParam("usersEmail") String usersEmail
    ) {
        CustomResponseData result = new CustomResponseData();
        String item = "";
        Users entity = usersService.usersEmailDupliCheck(usersEmail);
        if(entity == null) {
            item = usersEmail;
        }

        result.setStatusCode(HttpStatus.OK.value());
        result.setResultItem(item);
        result.setResponseDateTime(LocalDateTime.now());
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        // authorities.stream().forEach(e -> log.debug("{}", e.getAuthority()));
        // log.debug(auth.toString());
        String requester = req.getLocalAddr();
        log.info("[OK] {} signIn Success FROM {}", usersAccount, requester);
        CustomResponseData result = new CustomResponseData();
        LocalDateTime currentTime = LocalDateTime.now();
        
        GetUsersInfoByUsersAccountEntity user = usersService.getUsersInfoByUsersAccount(usersAccount);
        
        result.setStatusCode(HttpStatus.OK.value());
        result.setResultItem(user);
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
        // log.debug("SNS: {}", principalUserDetails.toString());

        String sns = principalUserDetails.getProvider();
        String usersAccount = principalUserDetails.getUsername();
        String usersEmail = principalUserDetails.getUsers().getUsersEmail();
        String usersRole = principalUserDetails.getUsers().getUsersRole();
        String picture = "";
        // log.debug("# sns:{}, usersEmail: {}, usersRole: {}",sns, usersEmail, usersRole);

        Users user = usersService.selectByUsersEmail(usersEmail);
        if(user != null) {    
            Cookie cookieAccount = new Cookie("usersAccount", usersAccount);
            cookieAccount.setPath("/");
            response.addCookie(cookieAccount);
            response.sendRedirect("http://" + myDomain + ":" + myUiPort + "/");
        }
        else {
            log.info("{}, {} 님은 신규회원 입니다. 회원가입 페이지로 이동합니다.", sns, usersAccount);
//            log.info("프로필 이미지 정보: {}", );
//            redirectAttributes.addFlashAttribute("info","info");
             Cookie cookieProvider = new Cookie("provider", sns+"/"+usersEmail+"/"+picture);
//             Cookie cookieUsersEmail = new Cookie("usersEmail", usersEmail);
//             Cookie cookiePicture = new Cookie("picture", "");
             cookieProvider.setPath("/");
             response.addCookie(cookieProvider);
//             response.addCookie(cookieUsersEmail);
//             response.addCookie(cookiePicture);
            response.sendRedirect("http://" + myDomain + ":" + myUiPort + "/user/register");
            response.flushBuffer();
        }
    }

    @GetMapping("/sign/oauth2SignIn.failure")
    public void oauth2SignInFailure(
        @AuthenticationPrincipal CustomPrincipalDetails principalUserDetails,
        HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        log.info("# SNS 계정을 활용한 로그인에 실패했습니다,");
        response.sendRedirect("http://" + myDomain + ":" + myUiPort);
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
        // log.debug("# usersAccount : usersPass -> {} : {}", usersAccount, usersPass);
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

    @Operation(summary="회원정보 조회 (메인페이지 전용)")
    @GetMapping(value="/getUsersInfo")
    public CustomResponseData getUsersInfoByUsersAccount(
        @RequestParam(value="usersAccount") String usersAccount
    ) {
        CustomResponseData result = new CustomResponseData();
        GetUsersInfoByUsersAccountEntity item = usersService.getUsersInfoByUsersAccount(usersAccount);

        result.setStatusCode(HttpStatus.OK.value());
        result.setResultItem(item);
        result.setResponseDateTime(LocalDateTime.now());

        return result;
    }
    // @Operation(summary="회원정보 조회 (마이페이지 전용)")
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
     * @param param Users
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
     * @param usersIdx Long
     * @param terminateReason String
     * @return
     */
    @Operation(summary="회원탈퇴", description="회원이 직접 서비스로부터 회원탈퇴를 신청합니다.")
    @PutMapping("/deleteUserInfo")
    public CustomResponseData deleteUserInfo(
        @Param("usersIdx") Long usersIdx,
        @Param("terminateReason") String terminateReason
    ) {
        CustomResponseData result = new CustomResponseData();
        Map<String, Object> item = new HashMap<>();
        LocalDateTime currentTime = LocalDateTime.now();        
       
        int transaction = usersService.deleteUserInfo(usersIdx);
        if(transaction == 1) {
            TerminateUser entity = new TerminateUser();
            entity.setUsersIdx(usersIdx);
            entity.setTerminateReason(terminateReason);
            TerminateUser step2 = terminateUserService.save(entity);
            if(step2 != null) {
                result.setStatusCode(HttpStatus.OK.value());
                item.put("message", "회원탈퇴가 완료되었습니다. ");
                result.setResultItem(item);
                result.setResponseDateTime(currentTime);
            }
        }
        else {
            result.setStatusCode(HttpStatus.BAD_REQUEST.value());
            item.put("message", "회원탈퇴에 실패했습니다 사용자의 사용자번호 idx: " + String.valueOf(usersIdx) );
            result.setResultItem(item);
            result.setResponseDateTime(currentTime);
        }
        

        // if(transaction == 1) {
        //     result.setStatusCode(HttpStatus.OK.value());
        //     item.put("message", "Success Delete Users idx=" + Long.valueOf(usersIdx));
        //     result.setResultItem(item);
        //     result.setResponseDateTime(currentTime);
        // }
        // else {
        //     result.setStatusCode(HttpStatus.BAD_REQUEST.value());
        //     item.put("message", "Failure Delete Users idx=" + Long.valueOf(usersIdx) );
        //     result.setResultItem(item);
        //     result.setResponseDateTime(currentTime);
        // }
        return result;
    }
    
}

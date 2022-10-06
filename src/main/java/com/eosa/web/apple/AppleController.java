package com.eosa.web.apple;

import com.eosa.web.users.entity.Users;
import com.eosa.web.users.service.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.ParseException;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AppleController {

    @Autowired private UsersService usersService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final HttpSession session;

    @Value("${my.service.domain}") private String myDomain;
    @Value("${my.ui.port}") private String myUiPort;

    
    /** 
     * @param response
     * @throws JOSEException
     * @throws ParseException
     * @throws IOException
     */
    @PostMapping("/api/user/apple/loginCallBack")
    @ResponseBody
    public void appleLoginCallBack(
        @RequestBody String apple_data,
        HttpServletResponse response
    ) throws JOSEException, ParseException, IOException {

        // ObjectMapper objectMapper = new ObjectMapper();
        // ModelAndView mv = new ModelAndView();

        log.debug("===================================================");
        log.debug(apple_data);
        log.debug("===================================================");

        String[] datas = apple_data.split("&");

        String code = datas[1].replace("code=", "");
        String id_token = datas[2].replace("id_token=", "");

        AppleUser appleUser = null;
        if (datas.length > 3) {
            ObjectMapper mapper = new ObjectMapper();

            log.info("datas[3] : " + datas[3]);

            String user = datas[3].replace("user=", "");
            log.info("user : " + user);

            String decode = URLDecoder.decode(user, "UTF-8");

            appleUser = mapper.readValue(decode, AppleUser.class);
            log.info("appleUser 인코딩 후 :" + appleUser);
        }

        // 애플 정보조회 성공
        //애플 정보 디코딩 후 계정 공개
        JSONObject user = decodeFromIdToken(id_token);
        
        //apple 계정 정보 삽입
        String appleUniqueNo = user.get("sub").toString();
        String appleName = "";
        String appleEmail = "";
        if(user.get("email") != null) { appleEmail = user.get("email").toString(); }

        if (appleUser != null) {
            appleEmail = appleUser.getEmail();
            appleName = appleUser.getName().getLastName() + appleUser.getName().getFirstName();
        }

        String provider = "apple";
        String providerId = appleUniqueNo;
        // String username = provider + "_" + providerId;
        String username = appleEmail.split("@")[0];
        String memberPassword = bCryptPasswordEncoder.encode("eosa_sns_password");
        String memberEmail = appleEmail;
        String memberName = appleName;

        log.debug("username: {}  memberEmail: {}", username, memberEmail);

        //회원정보 확인
//        MemberDTO memberDTO = memberMapper.findByOAuthUsername(username);
//        PrincipalDetails principalDetails = new PrincipalDetails(memberDTO);

        Users memberDTO = usersService.findByUsersAccount(username);
        // log.debug("[113] memberDTO: {}", memberDTO.toString());
        // CustomPrincipalDetails principalDetails = new CustomPrincipalDetails(memberDTO);

        //회원정보 없을 시 회원가입
        if (memberDTO == null) {
//            MVC pattern
//            session.setAttribute("provider", provider);
//            session.setAttribute("providerId", providerId);
//            session.setAttribute("username", username);
//            session.setAttribute("memberPassword", memberPassword);
//            session.setAttribute("memberEmail", memberEmail);
//            session.setAttribute("memberName", memberName);
//
//            mv.setViewName("redirect:/sns-signup");
//            return mv;
            Cookie cookieProvider = new Cookie("provider", provider+"/"+memberEmail+"/");
            cookieProvider.setPath("/");
            response.addCookie(cookieProvider);
            response.sendRedirect("https://dowajo.co.kr/user/register");
            response.flushBuffer();

        } else if (memberDTO != null) {
//            MVC pattern
//            String msg = "msg-approval";
//            msg = URLEncoder.encode(msg, "UTF-8");
//            mv.setViewName("redirect:/login/error?msg="+msg);
//            return mv;
            Cookie cookieAccount = new Cookie("provider", provider+"/"+memberEmail+"/");
            cookieAccount.setPath("/");
            response.addCookie(cookieAccount);
            response.sendRedirect("https://dowajo.co.kr/");
        }
        else {
            Cookie cookieAccount = new Cookie("provider", provider+"/"+memberEmail+"/");
            cookieAccount.setPath("/");
            response.addCookie(cookieAccount);
            response.sendRedirect("https://dowajo.co.kr/");
        }

        // // 로그인 세션에 들어갈 권한을 설정합니다.
        // Collection<GrantedAuthority> collectors = new ArrayList<>();

        // collectors.add(() -> {
        //     return "CLIENT";
        // });

        // Authentication auth = new UsernamePasswordAuthenticationToken(principalDetails, provider, collectors);

        // SecurityContextHolder.getContext().setAuthentication(auth);

        // mv.setViewName("redirect:/");
        // return mv;
    }

    
    /** 
     * @param id_token
     * @return JSONObject
     */
    public JSONObject decodeFromIdToken(String id_token) {

        try {
            SignedJWT signedJWT = SignedJWT.parse(id_token);
            JWTClaimsSet getPayload = signedJWT.getJWTClaimsSet();
            ObjectMapper objectMapper = new ObjectMapper();
            JSONObject payload = objectMapper.readValue(getPayload.toString(), JSONObject.class);
            log.debug("after decode payload: {}", payload.toString());
            return payload;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
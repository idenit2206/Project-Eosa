// package com.sherlockk.demo.users;

// import java.util.Map;

// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.annotation.AuthenticationPrincipal;
// import org.springframework.security.oauth2.core.user.OAuth2User;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/api/user")
// public class UsersOAuthController {

//     @GetMapping("/oauth/loginInfo")
//     @ResponseBody
//     public String oauthLoginInfo(Authentication authentication, @AuthenticationPrincipal OAuth2User oAuth2UserPrincipal){
//         OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
//         Map<String, Object> attributes = oAuth2User.getAttributes();
//         System.out.println(attributes);
//         // PrincipalOauth2UserService의 getAttributes내용과 같음

//         Map<String, Object> attributes1 = oAuth2UserPrincipal.getAttributes();
//         // attributes == attributes1

//        return attributes.toString();     //세션에 담긴 user가져올 수 있음음
//     }
    
// }
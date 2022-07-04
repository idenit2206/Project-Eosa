// package com.sherlockk.demo.oauth2;

// import java.util.UUID;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
// import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
// import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
// import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
// import org.springframework.security.oauth2.core.user.OAuth2User;
// import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
// import org.springframework.stereotype.Service;

// import com.sherlockk.demo.users.Users;
// import com.sherlockk.demo.users.UsersRepository;
// import com.sherlockk.demo.users.UsersRole;
// import com.sherlockk.demo.users.UsersService;

// @Service
// public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

//     @Autowired
//     private UsersService usersService;

//     @Override
//     public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

//         OAuth2User oAuth2User = super.loadUser(userRequest);

//         OAuth2UserInfo oAuth2UserInfo = null;
//         String provider = userRequest.getClientRegistration().getRegistrationId();
        
//         if(provider.equals("kakao")) {
//             oAuth2UserInfo = (OAuth2UserInfo) new KakaoUserInfo(oAuth2User.getAttributes());
//         }

//         String providerId = oAuth2UserInfo.getProviderId();	
//         String username = provider+"_"+providerId;  			

//         String uuid = UUID.randomUUID().toString().substring(0, 6);
//         String password = new BCryptPasswordEncoder().encode("패스워드"+uuid); 

//         String email = oAuth2UserInfo.getEmail();
//         UsersRole role = UsersRole.CLIENT;

//         Users byUsername = usersService.findByUsersAccount(username);
        
//         // DB에 없는 사용자라면 회원가입처리
//         if(byUsername == null){
//             byUsername = Users.oauth2Register()
//                     .usersAccount(username).usersPass(password).usersEmail(email).usersRole(role)
//                     .provider(provider).providerId(providerId)
//                     .build();
//             usersService.save(byUsername);
//         }

//         return new PrincipalDetails(byUsername, oAuth2UserInfo);
//     }
// }

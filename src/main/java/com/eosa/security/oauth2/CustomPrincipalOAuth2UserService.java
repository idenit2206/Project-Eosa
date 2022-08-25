package com.eosa.security.oauth2;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.eosa.security.CustomPrincipalDetails;
import com.eosa.security.oauth2.entity.GoogleUserInfo;
import com.eosa.security.oauth2.entity.KakaoUserInfo;
import com.eosa.security.oauth2.entity.NaverUserInfo;
import com.eosa.web.users.Users;
import com.eosa.web.users.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * OAuth2를 거쳐 Provider(SNS)에 로그인을 성공하면 Provider의 유저정보를 가져오는 서비스입니다.
 */
@Slf4j
@Service
public class CustomPrincipalOAuth2UserService extends DefaultOAuth2UserService {
    
    @Autowired private UsersRepository usersRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        
        OAuth2User oAuth2User = super.loadUser(userRequest);
        CustomOAuth2UserInfo customOAuth2UserInfo = null;
        CustomPrincipalDetails result = null;
        Users user = null;

        // log.debug("CustomPrincipalOAuth2UserService.class [OAuth2User] -> {}", oAuth2User.toString());

        // 플랫폼 명칭 ex) google, kakao, ...
        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = null;
        String platform = null;
        String usersEmail = null;

        if(provider.equals("kakao")) {
            customOAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
            platform = customOAuth2UserInfo.getProvider();
            usersEmail = customOAuth2UserInfo.getEmail();
            log.debug("CustomPrincipalOAuth2UserService.class [kakao]: {}", customOAuth2UserInfo.getProviderId());
        }
        else if(provider.equals("google")) {
            platform = provider;
            providerId = oAuth2User.getAttribute("sub");
            usersEmail = oAuth2User.getAttribute("email").toString();
            // log.debug("#[Google] providerId: {}", providerId);
            // log.debug("#[Google] oAuth2Email: {}", usersEmail);
            customOAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
            // log.debug("# customOAuth2UserInfo[google]: {}", customOAuth2UserInfo);
        }
        else if(provider.equals("naver")) {
            Map<String, Object> response = oAuth2User.getAttribute("response");
            log.debug(response.toString());
            platform = provider;
            providerId = oAuth2User.getAttribute("sub");
            usersEmail = response.get("email").toString();
        }
       
        log.debug("platform: {}, email: {}", platform, usersEmail);

        // DB에 기존유저로써 존재하는지 검사
        user = (Users) usersRepository.selectByUsersEmail(usersEmail);
        // log.debug("# after DB check .selectByUsersEmail() -> oAuth2User: {}", oAuth2User.getAttributes());

        
        if(user != null) {
            // DB에 데이터가 존재하는 경우(기존회원)
            result = new CustomPrincipalDetails(user, oAuth2User.getAttributes(), provider);
            // log.debug("[PrincipalOAuth2UserService] 사용자 DB체크 결과: {}", user.toString());
        }
        else {
            // DB에 데이터가 존재하지않는 경우(신규회원)
            int tempIndex = usersEmail.indexOf("@");
            String tempAccount = usersEmail.substring(0, tempIndex);  
            user = new Users(tempAccount, usersEmail);          
            result = new CustomPrincipalDetails(user, oAuth2User.getAttributes(), provider);
            log.debug("사용자 DB체크 결과: {} 사용자는 신규회원입니다.", usersEmail);
           
        }
        return result;
    }
}
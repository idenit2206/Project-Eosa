package com.eosa.web.security.oauth2;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.eosa.web.security.CustomPrincipalDetails;
import com.eosa.web.users.FindByUsersAccount;
import com.eosa.web.users.Users;
import com.eosa.web.users.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomPrincipalOAuth2UserService extends DefaultOAuth2UserService {
    
    @Autowired private UsersRepository usersRepository;
    @Autowired private BCryptPasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        
        OAuth2User oAuth2User = super.loadUser(userRequest);
        CustomOAuth2UserInfo customOAuth2UserInfo = null;

        // 플랫폼 명칭 ex) google, kakao, ...
        String provider = userRequest.getClientRegistration().getRegistrationId();

        if(provider.equals("kakao")) {
            customOAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
            log.debug("# customOAuth2UserInfo: {}", customOAuth2UserInfo.getProviderId());
        }

        String platform = customOAuth2UserInfo.getProvider();
        String usersEmail = customOAuth2UserInfo.getEmail();
       
        log.debug("# platform: {}, email: {}", platform, usersEmail);

        // DB에 기존유저로써 존재하는지 검사
        Users user = (Users) usersRepository.selectByUsersEmail(usersEmail);
        log.debug("[PrincipalOAuth2UserService] 사용자 DB체크 결과: {}", user.toString());

        // DB에 데이터가 없는 신규회원의 경우
        if(user == null) {
            
        }

        return new CustomPrincipalDetails(user, oAuth2User.getAttributes());
    }

}
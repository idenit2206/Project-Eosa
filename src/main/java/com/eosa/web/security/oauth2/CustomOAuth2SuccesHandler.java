// 19일 기준 사용하지 않는 Handler

// package com.eosa.web.security.oauth2;

// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import org.springframework.security.core.Authentication;
// import org.springframework.security.oauth2.core.user.OAuth2User;
// import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
// import org.springframework.stereotype.Component;

// import com.eosa.web.users.Users;
// import com.eosa.web.users.UsersRepository;
// import com.fasterxml.jackson.databind.ObjectMapper;

// import io.jsonwebtoken.io.IOException;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// @RequiredArgsConstructor
// @Component
// public class CustomOAuth2SuccesHandler extends SimpleUrlAuthenticationSuccessHandler {    

//     private final UsersRepository usersRepository;
//     private final ObjectMapper objectMapper;

//     @Override
//     public void onAuthenticationSuccess(
//         HttpServletRequest request, HttpServletResponse response, 
//         Authentication authentication
//     ) throws IOException, ServletException {
//         log.debug("# [CustomOAuth2SuccessHandler] Start");
//         OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
//         log.debug("# oAuth2User: {}", oauth2User.getAttributes());
//         // Users user = usersRepository.selectByUsersEmail(customOAuth2UserInfo.getEmail());
//     }
    
// }

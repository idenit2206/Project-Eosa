package com.eosa.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import com.eosa.web.security.jwt.javajwt.CustomJwtAuthenticationFilter;
import com.eosa.web.security.jwt.javajwt.CustomJwtAuthorizationFilter;
import com.eosa.web.users.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {

    @Autowired private UsersRepository usersRepository;
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        http.addFilter(new CustomJwtAuthenticationFilter(authenticationManager));
        http.addFilter(new CustomJwtAuthorizationFilter(authenticationManager, usersRepository));
    }

    public static MyCustomDsl customDsl() {
        log.info("## [INFO] Loaded MyCustomDsl.customDsl()");
        return new MyCustomDsl();
    }

}

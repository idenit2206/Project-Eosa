package com.eosa.web.security.jwt.javajwt;

public interface CustomJwtProperties {
    String SECRET_KEY = "TESTING";
    // 10일이라 합니다. 1은 1ms입니다. 
    int EXPIRATION_TIME = 864000000;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}

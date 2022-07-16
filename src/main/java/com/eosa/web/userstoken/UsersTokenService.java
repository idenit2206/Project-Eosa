package com.eosa.web.userstoken;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersTokenService implements UsersTokenRepository {
    
    @Autowired private UsersTokenRepository usersTokenRepository;

    public int saveAccessToken(Long tokenUsersIdx, String accessToken, LocalDateTime tokenCreateDate) {
        return usersTokenRepository.saveAccessToken(tokenUsersIdx, accessToken, tokenCreateDate);
    }

}

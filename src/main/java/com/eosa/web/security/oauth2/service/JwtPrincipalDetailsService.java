package com.eosa.web.security.oauth2.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eosa.web.security.oauth2.entity.JwtPrincipalDetails;
import com.eosa.web.users.FindByUsersAccount;
import com.eosa.web.users.Users;
import com.eosa.web.users.UsersRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtPrincipalDetailsService implements UserDetailsService {
    
    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String usersAccount) throws UsernameNotFoundException {
        Users users = usersRepository.findByUsersAccount(usersAccount);
        if(users != null) {
            return new JwtPrincipalDetails(users);
        }
        else { 
            log.error("## [JwtPrincipalDetailsService] users: {} 는 존재하지 않는 회원입니다.", usersAccount);  
            return null;
        }
       
       
    }

}
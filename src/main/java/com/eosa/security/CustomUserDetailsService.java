package com.eosa.security;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eosa.web.users.Users;
import com.eosa.web.users.UsersRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Spring Security FormLogin시 활용되는 서비스
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails result = null;
        Users user = usersRepository.findByUsersAccount(username);
        // CustomPrincipalDetails user = new CustomPrincipalDetails(usersRepository.findByUsersAccount(username));
    
        if(user != null) {
            result = new CustomPrincipalDetails(user);
        }

        return result;
    }
    
}

package com.eosa.web.security;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eosa.web.users.Users;
import com.eosa.web.users.UsersRepository;

import lombok.RequiredArgsConstructor;

/**
 * Spring Security FormLogin시 활용되는 서비스
 */
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired private UsersRepository usersRepository;

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

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

@Service
@RequiredArgsConstructor
public class JwtPrincipalDetailsService implements UserDetailsService {
    
    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByUsersAccount(username);
        return new JwtPrincipalDetails(users);
    }



}

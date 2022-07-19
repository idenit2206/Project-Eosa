package com.eosa.web.security;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.eosa.web.users.Users;
import com.eosa.web.users.UsersRepository;
import com.eosa.web.users.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    // @Autowired
    // private UsersService usersService;

    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //     Users user = usersService.findByUsersAccount(username);
    //     if(user == null) {
    //         log.debug("## user: {}", user);
    //         throw new UsernameNotFoundException(username);
    //     }
    //     return new CustomUserDetails(user);
    // }

    private final UsersRepository usersRepository;

    public CustomUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usersAccount) throws UsernameNotFoundException {
        return usersRepository.findOneWithAuthoritiesByUsersAccount(usersAccount)
            .map(user -> createUser(usersAccount, user))
            .orElseThrow(() ->   new UsernameNotFoundException(usersAccount + " -> 존재하지 않는 사용자입니다."));
    }

    private CustomUserDetails createUser(String usersAccount, CustomUserDetails user) {
        if(!user.isEnabled()) {
            throw new RuntimeException(usersAccount + " -> 사용 불가능한 사용자입니다.");
        }
        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
            .collect(Collectors.toList());
        return new CustomUserDetails(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
    
}

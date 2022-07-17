package com.eosa.web.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.eosa.web.users.Users;
import com.eosa.web.users.UsersService;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired private UsersService usersService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersService.findByUsersAccount(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }

    // private final UsersService usersService;

    // public CustomUserDetailsService(UsersService usersService) {
    //     this.usersService = usersService;
    // }

    // @Override
    // @Transactional
    // public UserDetails loadUserByUsername(String usersAccount) throws UsernameNotFoundException {
    //     return usersService.findByUsersAccount(usersAccount)
    //         .map(user -> createU)
    // }

    

    
    
}

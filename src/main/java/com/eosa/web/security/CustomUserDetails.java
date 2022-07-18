package com.eosa.web.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.eosa.web.users.Users;

public class CustomUserDetails implements UserDetails {

    private Users user;

    public CustomUserDetails(Users user) {
        this.user = user;
    }

    public CustomUserDetails(String username, String password, List<GrantedAuthority> grantedAuthorities) {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> auth = new ArrayList<>();
        String userRole = user.getUsersRole().toString();
        auth.add(new SimpleGrantedAuthority(userRole));
        return auth;
    }

    @Override
    public String getPassword() {
       return user.getUsersPass();
    }

    @Override
    public String getUsername() {
       return user.getUsersAccount();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}

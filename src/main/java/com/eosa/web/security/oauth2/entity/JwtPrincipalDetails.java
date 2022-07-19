package com.eosa.web.security.oauth2.entity;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.eosa.web.users.Users;

public class JwtPrincipalDetails implements UserDetails {

    private Users users;

    public JwtPrincipalDetails(Users users) {
        this.users = users;
    }

    public Users getUsers() {
        return users;
    }

    @Override
    public String getUsername() {
        return users.getUsersAccount();
    }

    @Override
    public String getPassword() {
        return users.getUsersPass();
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        users.getUsersRoleList().forEach(r -> {
            authorities.add(() -> { return r; });
        });
        return null;
    }
    
}

package com.eosa.web.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.eosa.web.users.Users;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
// UserDetails - SpringSecurity에서 FormLogin()이 작동할 때 쓰임
// OAuth2User - SpringSecurity에서 OAuth2Login()을 작동할 때 쓰임
public class CustomPrincipalDetails implements UserDetails, OAuth2User {

    private Users users;
    private Map<String, Object> attributes;

    // UserDetails와 연동 FormLogin() 작동시 사용
    public CustomPrincipalDetails(Users users) {
        this.users = users;
    }

    // OAuth2User와 연동 OAuth2Login() 작동시 사용
    public CustomPrincipalDetails(Users users, Map<String, Object> attributes) {
        this.users = users;
        this.attributes = attributes;
    }

    /**
     * UserDetails와 연동 해당 유저의 권한 반환
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> auths = new ArrayList<>();
        auths.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return users.getUsersRole();
            }         
        });
        return auths;
    }

    /**
     * OAuth2User와 연동 SNS플랫폼에 있는 사용자 정보 반환
    */    
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    /**
     * OAUth2User와 연동해 SNS플랫폼에서 사용중인 ID 반환
     */
    @Override
    public String getName() {
        String usersAccount = attributes.get("sub").toString();
        return usersAccount;
    }

    /**
     * UserDetails와 연동 
     */
    @Override
    public String getUsername() {
        return users.getUsersAccount();
    }

    /**
     * UserDetails와 연동 사용자 패스워드 반환
     */
    @Override
    public String getPassword() {
        return users.getUsersPass();
    }

    /**
    * UserDetails와 연동
    */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
    * UserDetails와 연동
    */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
    * UserDetails와 연동
    */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
    * UserDetails와 연동
    */
    @Override
    public boolean isEnabled() {
        return true;
    }
    
}

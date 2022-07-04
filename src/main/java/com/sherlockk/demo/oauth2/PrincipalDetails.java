// package com.sherlockk.demo.oauth2;

// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.Map;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.oauth2.core.user.OAuth2User;

// import com.sherlockk.demo.users.Users;

// import lombok.Getter;
// import lombok.ToString;

// @Getter
// @ToString
// public class PrincipalDetails implements UserDetails, OAuth2User{

//     private Users users;
//     private OAuth2UserInfo oAuth2UserInfo;

//     public PrincipalDetails(Users users) {
//         this.users = users;
//     }

//     public PrincipalDetails(Users users, OAuth2UserInfo oAuth2UserInfo) {
//         this.users = users;
//         this.oAuth2UserInfo = oAuth2UserInfo;
//     }

//     @Override
//     public Map<String, Object> getAttributes() {
//         return oAuth2UserInfo.getAuthorities();
//     }

//     @Override
//     public String getName() {
//         return oAuth2UserInfo.getProviderId();
//     }

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         Collection<GrantedAuthority> collect = new ArrayList<>();
//         collect.add(new GrantedAuthority() {
//             @Override
//             public String getAuthority() {
//                 return users.getUsersRole().toString();
//             }            
//         });
//         return collect;
//     }

//     @Override
//     public String getPassword() {
//         return users.getUsersPass();
//     }

//     @Override
//     public String getUsername() {
//         return users.getUsersAccount();
//     }

//     @Override
//     public boolean isAccountNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isEnabled() {
//         return true;
//     }
    
// }
package com.eosa.security.oauth2.entity;

import java.util.Map;

import com.eosa.security.oauth2.CustomOAuth2UserInfo;

public class NaverUserInfo implements CustomOAuth2UserInfo {

    private Map<String, Object> attributes;
    private Map<String, Object> attributesAccount;
    private Map<String, Object> attributesProfile;

    public NaverUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
        this.attributesAccount = (Map<String, Object>) attributes.get("naver_account");
        this.attributesProfile = (Map<String, Object>) attributesAccount.get("profile");
    }

    
    /** 
     * @return String
     */
    @Override
    public String getEmail() {
        return attributesAccount.get("email").toString();
    }

    
    /** 
     * @return String
     */
    @Override
    public String getProvider() {
        return "Naver";
    }

    
    /** 
     * @return String
     */
    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    
    /** 
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }
    
}

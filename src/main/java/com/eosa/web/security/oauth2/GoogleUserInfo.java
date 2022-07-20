package com.eosa.web.security.oauth2;

import java.util.Map;

public class GoogleUserInfo implements CustomOAuth2UserInfo {

    private Map<String, Object> attributes;
    private Map<String, Object> attributesAccount;
    private Map<String, Object> attributesProfile;

    public GoogleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
        // this.attributesAccount = (Map<String, Object>) attributes.get("email");
        // this.attributesProfile = (Map<String, Object>) attributesAccount.get("profile");
    }

    @Override
    public String getEmail() {
        return attributesAccount.get("email").toString();
    }

    @Override
    public String getProvider() {
        return "Google";
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }
    
}

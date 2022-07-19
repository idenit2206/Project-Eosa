package com.eosa.web.security.oauth2;

import java.util.Map;

public interface CustomOAuth2UserInfo {
   
    String getEmail();
    String getProvider();
    String getProviderId();
    Map<String, Object> getAttributes();   

}

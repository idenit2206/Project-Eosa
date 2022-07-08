// package com.sherlockk.demo.security.oauth2.entity;

// import java.util.Map;

// import org.springframework.security.core.userdetails.User;

// import com.sherlockk.demo.users.Users;

// import lombok.Builder;
// import lombok.Getter;

// @Getter
// public class OAuthAttributes {

//     private Map<String, Object> attributes;
//     private String nameAttributeKey;
//     private String name;
//     private String email;
//     private String picture;

//     @Builder
//     public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String pictures) {
//         this.attributes = attributes;
//         this.nameAttributeKey = nameAttributeKey;
//         this.name = name;
//         this.email = email;
//         this.picture = pictures;
//     }

//     public Users toEntity() {
//         return Users.builder()
//                 .usersEmail(email)
//                 .build();
//     }

//     public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {        
//         if("kakao".equals(registrationId)) {
//             return ofKakao("id", attributes);
//         }
        
//         if("naver".equals(registrationId)) {
//             return ofNaver("id", attributes);
//         }

//         return ofGoogle(userNameAttributeName, attributes);
//     }   

//     private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
//         Map<String, Object> response = (Map<String, Object>) attributes.get("response");
//         return OAuthAttributes.builder()
//             .email((String) response.get("email"))
//             .attributes(response)
//             .nameAttributeKey(userNameAttributeName)
//             .build();
//     }

//     private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
//         Map<String, Object> response = (Map<String, Object>) attributes.get("response");
//         return OAuthAttributes.builder()
//             .email((String) response.get("email"))
//             .attributes(response)
//             .nameAttributeKey(userNameAttributeName)
//             .build();
//     }

//     private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
//         Map<String, Object> response = (Map<String, Object>) attributes.get("response");
//         return OAuthAttributes.builder()
//             .email((String) response.get("email"))
//             .attributes(response)
//             .nameAttributeKey(userNameAttributeName)
//             .build();
//     }

// }

package com.eosa.web.users.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;

import com.eosa.web.companys.entity.Companys;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name="Users")
public class Users {

    public enum usersRole {
        CLIENT, DETECTIVE, TEMP, ADMIN, SUPER_ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usersIdx;

    @Column(nullable=true, length=30)
    private String usersAccount;

    @Column(nullable=false)
    private String usersPass;

    @Column(nullable=true, length=30)
    private String usersName;    

    @Column(nullable=true, length=30)
    private String usersNick;

    @Column(nullable=true, length=15)
    private String usersPhone;

    @Column(nullable=false, length=30)
    private String usersEmail;

    // @Enumerated(EnumType.STRING)
    // @Column(nullable=false)
    // private UsersRole usersRole;
    @Column
    private String usersRole;

    @Column(nullable=false)
    private int usersAge;

    @Column(nullable=false, length=30)
    private String usersRegion1;

    @Column(nullable=true, length=30)
    private String usersRegion2;

    @Column(nullable=false)
    private int usersGender;

    @Column(nullable=false)
    private LocalDateTime usersJoinDate;

    @Column(nullable=false)
    private int usersNotice;

    // OAuth2를 이용하는 경우 활용하는 플랫폼 이름(google, kakao, naver, ...)
    @Column private String provider;

    // OAuth2를 이용할 경우 플랫폼에서의 사용자 아이디
    @Column private String providerId;

    // 모바일 앱 환경에서 접속했을때 google firebase 서비스에서 받는 토큰(push 알림에 활용)
    @Column private String token;
    
    // 모바일 앱 환경에서 접속했을때 google firebase 서비스에서 받는 값(push 알림에 활용)
    @Column private String device;

    // OAuth2를 이용할 경우 프로필 이미지 파일 링크(사용하지 않음)
    @Column private String usersProfile;

    @Column(nullable=false)
    @ColumnDefault("1") // Table을 ddl을 통해 새로 생성할때만 작동
    private int usersEnabled;

    @Column(nullable=false)
    @ColumnDefault("0")
    private int usersDelete;

    @Builder(builderClassName = "UserDetailRegister", buildMethodName = "userDetailRegister")
    public Users(String usersAccount, String usersEmail) {
        this.usersAccount = usersAccount;
        this.usersEmail = usersEmail;
    }

    @Builder(builderClassName = "OAuth2Register", buildMethodName = "oauth2Register")
    public Users(String usersAccount, String usersEmail, String provider, String providerId) {
        this.usersAccount = usersAccount;
        this.usersEmail = usersEmail;
        this.provider = provider;
        this.providerId = providerId;
    }

}

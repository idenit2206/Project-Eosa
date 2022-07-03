package com.sherlockk.demo.users;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="Users")
public class Users {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usersIdx;

    @Column(nullable=false, length=30)
    private String usersAccount;

    @Column(nullable=false, length=30)
    private String usersPass;

    @Column(nullable=false, length=30)
    private String usersName;

    @Column(nullable=false)
    private int usersGender;

    @Column(nullable=false, length=30)
    private String usersNick;

    @Column(nullable=false, length=13)
    private String usersPhone;

    @Column(nullable=false, length=30)
    private String usersEmail;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=30)
    private UsersRole usersRole;

    @Column(nullable=false)
    private int usersAge;

    @Column(nullable=false, length=30)
    private String usersRegion1;

    @Column(nullable=false, length=30)
    private String usersRegion2;

    @Column(nullable=false)
    private LocalDateTime usersJoinDate;

    @Column(nullable=false)
    @ColumnDefault("1") // Table을 ddl을 통해 새로 생성할때만 작동
    private int usersEnabled;

    @Column(nullable=false)
    @ColumnDefault("0")
    private int usersDelete;

    // OAuth2를 이용하는 경우 활용하는 플랫폼 이름(google, kakao, naver, ...)
    @Column
    private String provider;

    // OAuth2를 이용할 경우 플랫폼에서의 사용자 아이디
    @Column
    private String providerId;


    @Builder(builderClassName = "OAuth2Register", builderMethodName = "oauth2Register")
    public Users(String usersAccount, String usersPass, String usersEmail, UsersRole usersRole, String provider, String providerId) {
        this.usersAccount = usersAccount;
        this.usersPass = usersPass;
        this.usersEmail = usersEmail;
        this.usersRole = usersRole;
        this.provider = provider;
        this.providerId = providerId;
    }

}

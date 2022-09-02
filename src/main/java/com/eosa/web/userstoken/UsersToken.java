package com.eosa.web.userstoken;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.eosa.web.users.entity.Users;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name="UsersToken")
public class UsersToken {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenIdx;

    @Column
    private Long tokenUsersIdx;

    @Column
    private String accessToken;

    @Column
    private String refreshToken;

    @Column
    private LocalDateTime tokenCreateDate;

    @OneToOne
    @JoinColumn(name = "usersIdx")
    private Users users;

    public UsersToken(Long tokenUsersIdx, String jwt, LocalDateTime tokenCreateDate) {
        this.tokenUsersIdx = tokenUsersIdx;
        this.accessToken = jwt;
        this.tokenCreateDate = tokenCreateDate;
    }

}

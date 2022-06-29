package com.sherlockk.demo.users;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Data
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

    @Column(nullable=false, length=30)
    private String usersRole;

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

}

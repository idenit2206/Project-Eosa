package com.eosa.web.users.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="TempUser")
public class TempUser {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long tempUserIdx;

    @Column(nullable = false) private String tempUserEmail;
    @Column(nullable = false) private String tempUserPass;
    @Column(nullable = false) private int tempUserGender;
    @Column(nullable = false) private int tempUserAge;
    @Column(nullable = false) private String tempUserRegion1;
    @Column(nullable = false) private String tempUserRegion2;
    @Column(nullable = false) private LocalDateTime tempUserRegistDate;

}

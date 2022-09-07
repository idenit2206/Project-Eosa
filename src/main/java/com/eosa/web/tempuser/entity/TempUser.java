package com.eosa.web.tempuser.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="TempUser")
public class TempUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tempUserIdx;
    @Column private String tempUserEmail;
    @Column private String tempUserPass;
    @Column private boolean tempUserGender;
    @Column private int tempUserAge;
    @Column private String tempUserRegion1;
    @Column private String tempUserRegion2;
    @Column
    private LocalDateTime tempUserRegistDate;


}

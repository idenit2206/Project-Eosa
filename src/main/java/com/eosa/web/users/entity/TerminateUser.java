package com.eosa.web.users.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="TerminateUser")
public class TerminateUser {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long terminateUserIdx;

    @Column private Long usersIdx;
    @Column(length=255) private String terminateReason;
    @Column LocalDateTime terminateRequestDate;

}

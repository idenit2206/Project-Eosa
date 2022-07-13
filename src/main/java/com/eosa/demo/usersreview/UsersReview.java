package com.eosa.demo.usersreview;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name="UsersReview")
public class UsersReview {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable=false)
    private Long usersIdsx;

    @Column(nullable=false)
    private Long companysIdx;

    @Column(nullable=false)
    private Long reqeustFormIdx;

    @Column(nullable=false)
    private int resultScore;

    @Column(nullable=false)
    private int communicationScore;

    @Column(nullable=false)
    private int processScore;

    @Column(nullable=false)
    private int specialityScore;

    @Column(nullable=true)
    private String reviewDetail;

    @Column(nullable=false)
    private LocalDateTime reviewDate;

}

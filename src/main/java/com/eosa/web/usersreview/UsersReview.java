package com.eosa.web.usersreview;

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
@Table(name="UsersReview")
public class UsersReview {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable=false)
    private Long usersIdx;

    @Column(nullable=false)
    private Long companysIdx;

    @Column(nullable=false)
    private Long requestFormIdx;

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

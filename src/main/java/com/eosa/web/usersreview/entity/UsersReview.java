package com.eosa.web.usersreview;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.eosa.web.users.entity.Users;
import lombok.Data;

@Data
@Entity
@Table(name="UsersReview")
public class UsersReview {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable=false)
    private Long reviewUsersIdx;

    @Column(nullable=false)
    private Long reviewCompanysIdx;

    @Column(nullable=false)
    private Long reviewRequestFormIdx;

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

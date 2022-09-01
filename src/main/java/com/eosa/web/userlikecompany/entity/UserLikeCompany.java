package com.eosa.web.userlikecompany.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
public class UserLikeCompany {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idx;
    @Column Long usersIdx;
    @Column Long companysIdx;
    @Column LocalDateTime likeDate;

}

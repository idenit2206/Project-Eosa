package com.eosa.web.users.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="UserLikeCompany")
public class UserLikeCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false) private Long usersIdx;
    @Column(nullable = false) private Long companysIdx;
    @Column(nullable = false) private LocalDateTime likeDate;

}

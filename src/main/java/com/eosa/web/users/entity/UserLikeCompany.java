package com.eosa.web.users.entity;

import com.eosa.web.companys.entity.Companys;
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
    @Column(nullable = false) private int userLikeCompanyEnable;
    @Column(nullable = false) private LocalDateTime likeDate;

    @ManyToOne(targetEntity= Companys.class, fetch=FetchType.EAGER)
    private Companys companys;

    @ManyToOne(targetEntity= Users.class, fetch=FetchType.EAGER)
    private Users users;


}

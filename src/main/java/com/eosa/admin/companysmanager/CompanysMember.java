package com.eosa.admin.companysmanager;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.eosa.web.users.Users;

import lombok.Data;

@Data
public class CompanysMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column private Long usersIdx;
    @Column private Long companysIdx;
    @Column private LocalDateTime registDate;
    @Column private int statusValue;

    @OneToOne(mappedBy="companysMember")
    private Users users;

}

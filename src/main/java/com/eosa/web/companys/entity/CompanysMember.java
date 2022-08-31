package com.eosa.web.companys.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="CompanysMember")
public class CompanysMember {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long idx;

    @Column Long usersIdx;
    @Column(name="companysIdx") Long companysIdx;
    @Column LocalDateTime registDate;
    @Column int statusValue;

    @ManyToOne(targetEntity=Companys.class, fetch=FetchType.EAGER)
    private Companys companys;

}

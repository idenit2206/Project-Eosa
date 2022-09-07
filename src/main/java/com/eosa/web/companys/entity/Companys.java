package com.eosa.web.companys.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Data
@Entity
@Table(name="Companys")
public class Companys {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companysIdx;

    @Column(nullable=false, length=100)
    private String companysName;

    @Column(nullable=false)
    private Long companysCeoIdx;

    @Column(nullable=false)
    private String companysCeoName;

    @Column(nullable=true, length=255)
    private String companysComment;

    @Column(nullable=true)
    private String companysSpec;

    @Column private String companysPhone;

    @Column(nullable=false, length=50)
    private String companysRegion1;

    @Column(nullable=false, length=50)
    private String companysRegion2;

    @Column(nullable=false, length=255)
    private String companysRegion3;

    @Column(nullable=true, length=255)
    private String companysRegistCerti;

    @Column(nullable=true, length=255)
    private String companysLicense;

    @Column(nullable=true, length=255)
    private String companysProfileImage;

    @Column private String companysBankName;
    @Column private String companysBankNumber;

    @Column(nullable=false)
    @ColumnDefault("0")
    private boolean companysPremium;

    @Column(nullable=false)
    @ColumnDefault("0")
    private boolean companysLocalPremium;

    @Column(nullable=true, length=255)
    private LocalDateTime companysRegistDate;

    @Column(nullable=false)
    private boolean companysEnabled;   

    @Column(nullable=false)
    @ColumnDefault("0")
    private boolean companysDelete;

    // @ManyToOne(targetEntity=Users2.class, fetch=FetchType.LAZY)
    // @JoinColumn(name="usersIdx")
    // private Users2 users;

//    @OneToMany(mappedBy="companys", fetch=FetchType.LAZY)
//    private List<CompanysCategory> companysCategory = new ArrayList<>();
//
//    @OneToMany(mappedBy="companys")
//    private List<CompanysActiveRegion> companysActiveRegion = new ArrayList<>();
//
//    @OneToMany(mappedBy="companys")
//    private List<CompanysLicense> companysLicense = new ArrayList<>();
//
//    @OneToMany(mappedBy="companys")
//    private List<CompanysMember> companysMembers = new ArrayList<>();

}

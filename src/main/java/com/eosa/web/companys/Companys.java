package com.eosa.web.companys;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.eosa.web.users.Users;
import com.eosa.web.users.entity.Users2;

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

    @Column(nullable=false, length=255)
    private String companysRegistCerti;

    @Column(nullable=true)
    private String companysProfileImage;

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

    @OneToOne(mappedBy = "companys")
    private Users2 users;

}

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
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.eosa.web.users.Users;

import lombok.Data;

@Data
@Entity
@Table(name="Companys")
public class Companys {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companysIdx;

    @Column(nullable=false)
    private Long companysCeoIdx;

    @Column(nullable=false, length=255)
    private String companysName;

    @Column(nullable=true, length=255)
    private String companysComment;

    @Column(nullable=true)
    private String companysSpec;

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

    @Column(nullable=true, length=255)
    private LocalDateTime companysRegistDate;

    @Column(nullable=false)
    @ColumnDefault("1")
    private boolean companysEnabled;

    @Column(nullable=false)
    @ColumnDefault("0")
    private boolean companysDelete;

    @ManyToOne(optional = false)
    @JoinTable(
        name="Users",
        joinColumns = @JoinColumn(name="companysCeoIdx"),
        inverseJoinColumns = @JoinColumn(name="usersIdx")
    )
    private Users user;

}

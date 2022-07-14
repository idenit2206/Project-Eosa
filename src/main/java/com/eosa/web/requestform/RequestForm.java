package com.eosa.web.requestform;

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
@Table(name="RequestForm")
public class RequestForm {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable=false)
    private Long usersIdx;

    @Column(nullable=false)
    private Long companysIdx;

    @Column(nullable=false)
    private Long detectiveIdx;

    @Column(nullable=false)
    private String requestFormCategory;

    @Column(nullable=false)
    private String requestFormRegion1;

    @Column(nullable=false)
    private String requestFormRegion2;

    @Column(nullable=false)
    private String requestFormStatus;
    
    @Column(nullable=false)
    private LocalDateTime requestFormDate;

    @Column(nullable=true)
    private LocalDateTime requestFormAcceptDate;

    @Column(nullable=true)
    private LocalDateTime requestFormCompDate;

    @Column(nullable=true)
    private String requestFormRejectMessage;

}

package com.eosa.web.requestform.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="RequestFormBackup")
public class RequestFormBackup {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestFormBackupIdx;

    @Column private Long requestFormIdx;

    @Column(nullable=false) private Long usersIdx;

    @Column(nullable=false) private Long companysIdx;

    @Column(nullable=false) private String requestFormRegion1;

    // @Column(nullable=true) private String requestFormRegion2;

    @Column(nullable=false) private String requestFormChannel;

    @Column(nullable=false) private String requestFormStatus;
    @Column(nullable=true) private LocalDateTime requestFormStatusChangeDate;

    @Column(nullable=true) private LocalDateTime requestConsultDate;    
    @Column(nullable=true) private LocalDateTime requestFormDate;
    @Column(nullable=true) private LocalDateTime requestFormAcceptDate;
    @Column(nullable=true) private LocalDateTime requestFormCompDate;
    @Column(nullable=true) private String requestFormRejectMessage;  
    @Column(nullable=false) private int requestFormClientReadState;
    @Column private LocalDateTime requestFormClientReadDate;
    @Column(nullable=false) private int requestFormDetectiveReadState;
    @Column private LocalDateTime requestFormDetectiveReadDate;

//    @OneToMany(mappedBy = "requestForm")
//    private List<RequestFormCategory> requestFormCategory = new ArrayList<>();

    public RequestFormBackup() {}

    public RequestFormBackup(Long requestFormIdx, String requestFormStatus, String requestFormRejectMessage) {
        this.requestFormIdx = requestFormIdx;
        this.requestFormStatus = requestFormStatus;
        this.requestFormRejectMessage = requestFormRejectMessage;
    }
    
}
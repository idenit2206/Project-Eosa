package com.eosa.web.reportform.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="ReportForm")
public class ReportForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Column private Long usersIdx;
    @Column private Long companysIdx;
    @Column private String reportDetail;
    @Column private int reportCheckState;
    @Column private LocalDateTime reportDate;

}

package com.eosa.web.requestform.entity;

import lombok.Data;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="MissionForm")
public class MissionForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long missionFormIdx;
    @Column(nullable = false) private Long usersIdx;
    @Column(nullable = false) private Long companysIdx;
    @Column(nullable = false) private String missionFormRegion1;
    @Column(nullable = false) private String missionFormRegion2;
    @Column(nullable = false) private String missionFormStatus;
    @Column private LocalDateTime missionFormAcceptDate;
    @Column private LocalDateTime missionFormCompDate;
    @Column private String missionFormMessage;

}

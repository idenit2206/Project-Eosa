package com.eosa.web.requestcontract.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name="RequestContract")
@Table(name="RequestContract")
public class RequestContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestContractIdx;

    @Column(nullable = false) private Long requestFormIdx;
    @Column(nullable = false, length = 255) private String requestContractContractId;
    @Column(nullable = false) private String companysIdx;
    @Column(nullable = false) private String usersIdx;
    // @Column(nullable = false, length = 300) private String requestContractUrl;
    @Column(nullable = false) private LocalDateTime requestContractCreateDate;
    @Column(nullable = false) private int requestContractTurn;
    
}

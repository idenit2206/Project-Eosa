package com.eosa.admin.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RequestContractDTO {
    
    private Long requestContractIdx;
    private Long requestFormIdx;
    private String requestContractContractId;
    private String companysIdx;
    private String usersIdx;
    private LocalDateTime requestContractCreateDate;
    private int requestContractTurn;

}

package com.eosa.web.requestform.entity;

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
@Table(name="RequestFormCategory")
public class RequestFormCategory {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long requestFormCategoryIdx;
    @Column private Long requestFormIdx;
    @Column private String requestFormCategoryValue;

    // @ManyToOne(targetEntity=RequestForm.class, fetch=FetchType.EAGER)
    // private RequestForm requestForm;
}

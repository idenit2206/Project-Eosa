package com.eosa.web.price.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name="Price")
@Table(name="Price")
public class Price {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int priceIdx;

    @Column private String bankName;
    @Column private String bankNumber;

}

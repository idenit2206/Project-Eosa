package com.eosa.admin.companysmanager;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.eosa.web.companys.Companys;

import lombok.Data;

@Data
@Entity
@Table(name="CompanysCategory")
public class CompanysCategory {
    
    private Long companysCategoryIdx;
    private Long companysIdx;
    private String companysCategoryValue;

    @ManyToOne
    @JoinColumn(name="companysIdx")
    private Companys companys;

}

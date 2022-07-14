package com.eosa.web.companysmember;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


/**
 * @apiNote
 */
@Data
@Entity
@Table(name="CompanysMember")
public class CompanysMember {
    /**
     * 이 테이블의 인덱스번호
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    /**
     * Detective 회원의 색인 번호
     */
    @Column(nullable=false)
    private Long usersIdx;

    /**
     * Detective 회원이 속한 회사의 색인 번호
     */
    @Column(nullable=false)
    private Long companysIdx;

    /**
     * Detective 회원이 회사에 소속된 날짜
     */
    @Column(nullable=false)
    private LocalDateTime registDate;

    @Column(nullable=false)
    private int statusValue;
}

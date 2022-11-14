package com.eosa.web.board.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="Notice")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;                           // 공지사항 idx
    @Column private String title;               // 공지사항 제목
    @Column private String content;             // 공지사항 내용
    @Column private String author;              // 공지사항 작성자
    @Column private LocalDateTime postDate;     // 공지사항 작성일

}

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
    private Long idx;
    @Column private String title;
    @Column private String content;
    @Column private String author;
    @Column private LocalDateTime postDate;

}

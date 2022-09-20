package com.eosa.admin.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoticeDTO {

    private Long idx;
    private String title;
    private String content;
    private String author;
    private LocalDateTime postDate;

}

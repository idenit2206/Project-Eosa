package com.eosa.web.util.mail;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MailEntity {
    
    private String address;
    private String title;
    private String message;
    private String certiCode;
    private MultipartFile file;

}

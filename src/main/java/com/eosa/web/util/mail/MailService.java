package com.eosa.web.util.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailService {
    
    private JavaMailSender javaMailSender;

    public void mailSend(MailEntity mailEntity) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailEntity.getAddress());
        message.setSubject(mailEntity.getTitle());
        message.setText(mailEntity.getMessage());
        javaMailSender.send(message);
    }

}

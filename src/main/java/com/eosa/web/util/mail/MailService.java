package com.eosa.web.util.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class MailService {
    
    private JavaMailSender javaMailSender;

    public void mailSend(MailEntity mailEntity) throws MessagingException {
//        Gmail
        SimpleMailMessage message = new SimpleMailMessage();
        // 네이버 메일계정으로 메일을 송신한다면 반드시 .setFrom("메일주소")로 메일송신자를 설정합니다 .
        // Gmail 계정을 활용한다면 없어도 됩니다.
        message.setFrom("projecteosa@naver.com");
        message.setTo(mailEntity.getAddress());
        message.setSubject(mailEntity.getTitle());
        message.setText(mailEntity.getMessage());


        javaMailSender.send(message);
    }

    public void codeMailSend(MailEntity mailEntity) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailEntity.getAddress());
        message.setSubject(mailEntity.getTitle());
        message.setText(mailEntity.getMessage());       
        javaMailSender.send(message);
    }

}

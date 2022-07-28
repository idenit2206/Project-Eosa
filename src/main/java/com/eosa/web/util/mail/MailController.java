package com.eosa.web.util.mail;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/mail")
@RequiredArgsConstructor
public class MailController {
    
    private final MailService mailService;

    /**
     * 가장 기본적인 형태의 메일을 전송합니다.
     * 제목, 내용
     * @param mailEntity
     */
    @PostMapping("/send")
    public void mailSend(MailEntity mailEntity) {
        mailService.mailSend(mailEntity);
    }

    @PostMapping("/sendCode")
    public void codeMailSend(MailEntity mailEntity) {        
        String code = UUID.randomUUID().toString();
        code = code.substring(0, 8);
        mailEntity.setMessage(mailEntity.getMessage() + "\n인증코드: " + code);
        mailService.codeMailSend(mailEntity);
        log.info("# 발송된 인증코드: {}", code);
    }

}

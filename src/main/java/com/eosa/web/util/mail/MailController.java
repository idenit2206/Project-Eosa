package com.eosa.web.util.mail;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eosa.web.users.service.UsersService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.mail.MessagingException;

@Slf4j
@RestController
@RequestMapping("/api/mail")
@RequiredArgsConstructor
public class MailController {
    
    private final MailService mailService;
    private final UsersService usersService;

    /**
     * 가장 기본적인 형태의 메일을 전송합니다.
     * 제목, 내용
     * @param mailEntity
     */
    @PostMapping("/send")
    public void mailSend(MailEntity mailEntity) throws MessagingException {
        mailService.mailSend(mailEntity);
    }

    
    /** 
     * @param mailEntity
     */
    @PostMapping("/sendCode")
    public void codeMailSend(MailEntity mailEntity) {        
        String code = UUID.randomUUID().toString();
        code = code.substring(0, 8);
        mailEntity.setMessage(mailEntity.getMessage() + "\n인증코드: " + code);
        mailService.codeMailSend(mailEntity);
        log.info("# 발송된 인증코드: {}", code);
    }

    /**
     * 계정을 분실한 회원의 계정을 이메일로 전송합니다.
     * @param usersEmail
     */
    @GetMapping("/sendAccount")
    public void accountMailSend(@RequestParam("usersEmail") String usersEmail, MailEntity me) throws MessagingException {
        log.info("# {} 의 계정정보를 메일로 전송 요청 받았습니다.", usersEmail);
        String usersAccount = usersService.accountMailSend(usersEmail);
        log.debug("# usersAccount: {}", usersAccount);
        me.setAddress(usersEmail);
        me.setTitle("도와조 고객센터 입니다. 신청하신 계정 정보를 발송합니다.");
        me.setMessage("안녕하세요. 선택의 기준, 도와줘 고객센터 입니다.\n회원님의 아이디는 " + usersAccount + " 입니다.\n감사합니다.");
        mailService.mailSend(me);
    } 
}

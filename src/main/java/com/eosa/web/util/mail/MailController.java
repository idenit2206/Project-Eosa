package com.eosa.web.util.mail;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/mail")
@RequiredArgsConstructor
public class MailController {
    
    private final MailService mailService;

    @PostMapping("/send")
    public void mailSend(MailEntity mailEntity) {
        mailService.mailSend(mailEntity);
    }

}

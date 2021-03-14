package com.example.library.activation;

import com.example.library.user.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;

@RestController
@RequestMapping("/user")
public class MailController {
    private final EmailSender emailSender;
    @Autowired
    public MailController(EmailSender emailSender){
        this.emailSender = emailSender;
    }
    @PostMapping(value = "/activate")
    public String activation(@RequestBody ApplicationUser user) {
        String code = emailSender.sendEmail("activation", user.getEmail());
        return code;
    }

    @PostMapping(value = "/newPassword")
    public String resetPassword(@RequestBody ApplicationUser user) {
        String code = emailSender.sendEmail("authentication", user.getEmail());
        return code;
    }

    @PostMapping(value = "/confirm")
    public String confirm(@RequestBody ApplicationUser user) {
        if(emailSender.confirm(user.getVerificationCode(), user.getEmail())){
            return "activated";
        }
        return "code invalid";
    }
}


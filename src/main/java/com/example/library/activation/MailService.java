package com.example.library.activation;

import com.example.library.user.ApplicationUser;
import com.example.library.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
public class MailService implements EmailSender{

    private JavaMailSender javaMailSender;
    private TemplateEngine templateEngine;
    private UserService userService;


    @Autowired
    public MailService(JavaMailSender javaMailSender, TemplateEngine templateEngine, UserService userService) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.userService = userService;
    }

    @Override
    public String sendEmail(String actOrAuth, String email) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        Context context = new Context();
        String generatedCode = generateRandomActivationCode();
        userService.updateCode(email,generatedCode);
        context.setVariable("code", generatedCode);
        String body = "";
        String title = "";
        if(actOrAuth.equals("activation")){
            body = templateEngine.process("ActivationMailTemplate", context);
            title = "Book Library - Activating Code";
        }
        else if(actOrAuth.equals("authentication")){
            body = templateEngine.process("AuthenticationMailTemplate", context);
            title = "Book Library - Authentication";
        }
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(email);
            helper.setReplyTo("no_reply@example.com");
            helper.setFrom(new InternetAddress("no_reply@example.com", "NoReply"));
            helper.setSubject(title);
            helper.setText(body, true);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mail);
        return generatedCode;
    }

    public String generateRandomActivationCode() {
        Random rand_generator = new Random();
        int randInt = rand_generator.nextInt(50);
        String activating_keys[] = {"CTOV", "KQK6", "JH7V", "TKME", "FDET", "5YK6", "UFT4", "YCZ4", "ZXPP", "JPW6", "9RJB", "SBS3", "M79P", "UI7K", "9DNF", "FGCB", "VQU9", "DAQI", "SJE7", "MCJE", "89UF", "UBZ7", "TSE9", "2O7G", "PEML", "8FTI", "KC52", "8NO1", "52ME", "MU9Q", "7QSW", "Q18N", "UV5A", "NXWU", "VJBC", "T289", "SNWL", "NNR8", "8DA9", "PAOE", "VZQY", "RBME", "HPMQ", "DK46", "H8NT", "911L", "XUDR", "NM4M", "UTO6", "56GB"};
        return activating_keys[randInt];
    }

    @Override
    public Boolean confirm(String verificationCode, String email) {
        ApplicationUser user = userService.findByEmail(email);
        if(verificationCode.equals(user.getVerificationCode())){
            user.setActive(true);
            userService.saveUser(user);
            return true;
        };
        return false;
    }

}

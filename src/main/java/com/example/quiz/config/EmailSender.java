package com.example.quiz.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;


    public void sendConfirmedToken(String toEmail, String token, String url){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Confirm account");
        simpleMailMessage.setFrom(fromEmail);
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setText(url + "/confirmActivated?token=" + token);

        javaMailSender.send(simpleMailMessage);
    }

    public void sendResetPassword(String toEmail, String token, HttpServletRequest request){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Reset password");
        simpleMailMessage.setFrom(fromEmail);
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setText("reset password " + request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +
                "/reset?token=" + token);

        javaMailSender.send(simpleMailMessage);
    }
}

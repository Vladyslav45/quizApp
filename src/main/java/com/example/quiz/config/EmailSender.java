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
    private String email;

    public void sendConfirmedToken(String email, String token, HttpServletRequest request){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Confirm account");
        simpleMailMessage.setFrom(email);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setText("test " + request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +
                "/confirmActivated?token=" + token);

        javaMailSender.send(simpleMailMessage);
    }

    public void sendResetPassword(String email, String token, HttpServletRequest request){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Reset password");
        simpleMailMessage.setFrom(email);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setText("reset password " + request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +
                "/reset?token=" + token);

        javaMailSender.send(simpleMailMessage);
    }
}

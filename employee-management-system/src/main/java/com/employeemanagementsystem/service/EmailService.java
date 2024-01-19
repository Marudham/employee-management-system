package com.employeemanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendVerificationEmail(String to, String verificationToken, Long id) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Account Verification - Employee Management System");
        message.setText("To verify your account, click the following link: "
                + "http://localhost:8080/verify?token=" + verificationToken + "&id=" + id);

        javaMailSender.send(message);
    }
}

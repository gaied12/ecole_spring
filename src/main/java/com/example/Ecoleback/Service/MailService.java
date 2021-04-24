package com.example.Ecoleback.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService implements IMailService {
    @Autowired
    JavaMailSender javaMailSender;


    @Override
    public void sendEmail(String to,String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("oussamagaied07@gmail.com");
        message.setTo(to);
        message.setSubject("Confirmer votre compte ");
        message.setText(text);
       javaMailSender.send(message);


    }
}

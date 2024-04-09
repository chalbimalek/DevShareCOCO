package com.coco.pibackend.ServiceIMp;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;

@Service

public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

       public void sendSimpleMessage(
                String to, String subject, String text) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("mchalbi606@gmail.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
        }
  /*  public String sendMail(MultipartFile[] file, String to, String[] cc, String subject, String body) {
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom("mchalbi606@gmail.com");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setCc(cc);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);

            for (int i = 0; i < file.length; i++) {
                mimeMessageHelper.addAttachment(
                        file[i].getOriginalFilename(),
                        new ByteArrayResource(file[i].getBytes()));
            }

            emailSender.send(mimeMessage);

            return "mail send";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }*/
    }




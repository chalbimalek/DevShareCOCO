package com.coco.pibackend.Security.services;

import com.coco.pibackend.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.UUID;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment environment;

    public void sendVerificationEmail(User user, String token) {
        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";

        String emailContent = buildEmailVerifyMail(token);

        sendHtmlEmail(recipientAddress, subject, emailContent);
    }

    private String buildEmailVerifyMail(String token) {
        String url = environment.getProperty("app.root.frontend") + "/confirm-account/" + token;
        return buildEmailBody(
                url,
                "Verify Email Address",
                "Please, click on the link below to verify your email address.",
                "Click to Verify"
        );
    }
    public void sendPasswordResetEmail(User user, String token) {
        String recipientAddress = user.getEmail();
        String subject = "Password Reset Instructions";

        String emailContent = buildPasswordResetEmail(token);

        sendHtmlEmail(recipientAddress, subject, emailContent);
    }

    private String buildPasswordResetEmail(String token) {
        String url = environment.getProperty("app.root.frontend") + "/reset-password/" + token;
        return buildEmailBody(
                url,
                "Reset Your Password",
                "Please click on the link below to reset your password.",
                "Reset Password"
        );
    }


    private String buildEmailBody(String url, String emailBodyHeader, String emailBodyDetail, String buttonText) {
        return "<div style=\"margin: 0 auto; width: 500px; text-align: center; background: #ffffff; border-radius: 5px; border: 3px solid #838383;\">" +
                "<h2 style=\"background: #838383; padding: 15px; margin: 0; font-weight: 700; font-size: 24px; color: #ffffff;\">" + emailBodyHeader + "</h2>" +
                "<p style=\"padding: 20px; font-size: 20px; color: #202020;\">" + emailBodyDetail + "</p>" +
                "<a style=\"display: inline-block; padding: 10px 20px; margin-bottom: 30px; text-decoration: none; background: #3f51b5; font-size: 16px; border-radius: 3px; color: #ffffff;\" href=\" " + url + " \">" + buttonText + "</a>" +
                "</div>";
    }

    private void sendHtmlEmail(String recipientAddress, String subject, String emailContent) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        try {
            helper.setTo(recipientAddress);
            helper.setSubject(subject);
            helper.setText(emailContent, true); // Set to true for HTML content

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}

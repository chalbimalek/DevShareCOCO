package com.coco.pibackend.Controller;

import com.coco.pibackend.DTO.EmailRequest;
import com.coco.pibackend.ServiceIMp.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private JavaMailSender emailSender;
    @PreAuthorize("hasRole('ROLE_MEMBRE')")
    @PostMapping("/sendEmail")
    public void sendEmail(@RequestBody EmailRequest emailRequest) {
        emailService.sendSimpleMessage(
                emailRequest.getTo(),
                emailRequest.getSubject(),
                emailRequest.getText());
    }

    @PostMapping("/sendHtmlEmail")
    public void sendHtmlEmail(@RequestBody EmailRequest emailRequest) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(emailRequest.getTo());
        helper.setSubject(emailRequest.getSubject());
        helper.setText(emailRequest.getText(), true); // Définit le contenu HTML du message

        emailSender.send(message);
    }
/*@PostMapping("/send")
public String sendMail(@RequestParam(value = "file", required = false) MultipartFile[] file, String to, String[] cc, String subject, String body) {
    return emailService.sendMail(file, to, cc, subject, body);
}*/
}

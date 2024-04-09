package com.coco.pibackend.Controller;

import com.coco.pibackend.DTO.EmailRequest;
import com.coco.pibackend.ServiceIMp.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;
    @PreAuthorize("hasRole('ROLE_MEMBRE')")
    @PostMapping("/sendEmail")
    public void sendEmail(@RequestBody EmailRequest emailRequest) {
        emailService.sendSimpleMessage(
                emailRequest.getTo(),
                emailRequest.getSubject(),
                emailRequest.getText());
    }
/*@PostMapping("/send")
public String sendMail(@RequestParam(value = "file", required = false) MultipartFile[] file, String to, String[] cc, String subject, String body) {
    return emailService.sendMail(file, to, cc, subject, body);
}*/
}

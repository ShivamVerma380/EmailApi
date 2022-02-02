package com.email.webapi.webapi.controllers;

import com.email.webapi.webapi.entities.Email;
import com.email.webapi.webapi.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RestController
public class EmailController {
    
    @Autowired
    public EmailService emailService;

    @PostMapping("/email")
    public ResponseEntity<String> sendEmail(@javax.validation.constraints.Email @RequestParam("to") String to,@RequestParam("subject")String subject,@RequestParam("message") String message,@RequestParam("file") MultipartFile file){
        Email email = new Email(to,"shivam380.testing@gmail.com",message,file,subject);
        return emailService.sendEmail(email);
    }
}

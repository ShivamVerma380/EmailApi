package com.email.webapi.webapi.services;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.email.webapi.webapi.entities.Email;
//import com.email.webapi.webapi.helper.FileValidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class EmailService {
    

    public ResponseEntity<String> sendEmail(Email email){
        try {
            String from = email.getFrom();
            String to = email.getTo();
            String subject = email.getSubject();
            String message = email.getMessage();
            MultipartFile attachment = email.getAttachment();
           
            //Variable for gmail
            String host = "smtp.gmail.com";
            //Get System properties
            Properties properties = System.getProperties();
            
            System.out.println("Properties:"+properties);

            properties.put("mail.smtp.host", host);  //set up host as gmail.com
            properties.put("mail.smtp.port", "465"); // gmail port number
            properties.put("mail.smtp.ssl.enable", "true"); // security purposes
            properties.put("mail.smtp.auth", "true"); //for authentication
            
            //Step 1: Get the session object:
            Session session = Session.getInstance(properties,new Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(from, "*****");
                }
            });

            session.setDebug(true);

            //Step 2: Compose the message[text,multimedia]
            MimeMessage m = new MimeMessage(session); // create object of mime message
            //sender email id
            m.setFrom(from);
                
            //reciever email id
            m.addRecipient(Message.RecipientType.TO,new InternetAddress(to)); //In mail drafting set to column with value string to

            //set subject of mail
            m.setSubject(subject);
            
            //Create Mimebodypart for text and attachments
            MimeBodyPart textBodyPart = new MimeBodyPart();

            MimeBodyPart attachmentBodyPart = new MimeBodyPart();

            textBodyPart.setText(message);

            //attachmentBodyPart.attachFile((File) attachment);
            File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+attachment.getOriginalFilename());
            attachment.transferTo(convFile);
            
            attachmentBodyPart.attachFile(convFile);

            //Now create mimemultipart to add this in body 
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);
            mimeMultipart.addBodyPart(attachmentBodyPart);

            //Attach mimeMultipart to message
            m.setContent(mimeMultipart);


            
            //Step 3: Send message from transport
            Transport.send(m);

            


            return ResponseEntity.ok("Email sent successfully!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email not sent!!");
        }
    }


}

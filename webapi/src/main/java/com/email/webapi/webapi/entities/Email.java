package com.email.webapi.webapi.entities;

import org.springframework.web.multipart.MultipartFile;

public class Email {
    
    private String to;
    private String from;
    private String message;
    private MultipartFile attachment;
    private String subject;

    public Email(String to, String from, String message, MultipartFile attachment, String subject) {
        this.to = to;
        this.from = from;
        this.message = message;
        this.attachment = attachment;
        this.subject = subject;
    }

    public Email() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MultipartFile getAttachment() {
        return attachment;
    }

    public void setAttachment(MultipartFile attachment) {
        this.attachment = attachment;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Email [attachment=" + attachment + ", from=" + from + ", message=" + message + ", subject=" + subject
                + ", to=" + to + "]";
    }
    
    
}

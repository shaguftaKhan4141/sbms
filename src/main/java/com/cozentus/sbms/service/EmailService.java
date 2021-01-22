package com.cozentus.sbms.service;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    
	 public void sendEmailWithAttachment(String username, String authorName, String email, MultipartFile file) throws MessagingException, IOException {

	        MimeMessage msg = javaMailSender.createMimeMessage();

	        MimeMessageHelper helper = new MimeMessageHelper(msg, true);	        
	        helper.setTo(email);
            helper.setSubject("Testing from Spring Boot");
            helper.setText("Dear " + username + ", " + authorName + " has published a new post!", true);
            
            var attachment = new ByteArrayDataSource(file.getInputStream(), "application/octet-stream");

	        helper.addAttachment(file.getOriginalFilename(), attachment);
	        javaMailSender.send(msg);

	    }
}

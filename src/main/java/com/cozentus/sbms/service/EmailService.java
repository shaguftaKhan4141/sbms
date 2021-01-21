package com.cozentus.sbms.service;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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
    
	 public void sendEmailWithAttachment(String username, MultipartFile file) throws MessagingException, IOException {

	        MimeMessage msg = javaMailSender.createMimeMessage();

	        MimeMessageHelper helper = new MimeMessageHelper(msg, true);	        
	        helper.setTo("to_@email");
	        helper.setSubject("Testing from Spring Boot");
	        helper.setText("<h1>Dear username, Check attachment for image!</h1>", true);
	        helper.addAttachment(file.getName(), new InputStreamResource(file.getInputStream()));

	        javaMailSender.send(msg);

	    }
}

package com.cozentus.sbms.event;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.cozentus.sbms.service.EmailService;

@Component
public class EmailNotificationListener {
     
	 @Autowired
	 EmailService emailService;
	
	 @Async
	 @EventListener
	 void  sendMailEvent(EventData event) {
		 try {
			emailService.sendEmailWithAttachment(event.getUsername(), event.getFile());
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
	 }
}

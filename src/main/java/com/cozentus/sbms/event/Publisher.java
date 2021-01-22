package com.cozentus.sbms.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
class Publisher {
  
	@Autowired
    private ApplicationEventPublisher  publisher;
    
  void publishEvent(final String name, final String authorName, final String email, final MultipartFile file) {
    // Publishing event created by extending ApplicationEvent
    publisher.publishEvent(new EventData(name, authorName, email, file));
  }
}
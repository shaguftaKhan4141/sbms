package com.cozentus.sbms.event;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventData {
    
	private String username;
	private String email;
	private String authorName;
	private MultipartFile file;
}

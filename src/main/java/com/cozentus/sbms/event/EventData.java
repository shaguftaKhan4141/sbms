package com.cozentus.sbms.event;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventData {
    
	private String username;
	private MultipartFile file;
}

package com.cozentus.sbms.endpoint;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface TopicEndpoint {

	@PostMapping(value = "/topic")
	public ResponseEntity<?> addTopics(@RequestBody List<String> topicNames);
}

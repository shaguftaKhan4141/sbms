package com.cozentus.sbms.endpoint;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.cozentus.sbms.error.NotFoundException;

public interface TopicEndpoint {

	@PostMapping(value = "/topic")
	public ResponseEntity<?> addTopics(@RequestBody List<String> topicNames);

	@PutMapping(value = "/topic/{id}")
	public ResponseEntity<?> updateTopic(@RequestParam("topicName") String topicName, @PathVariable("id") Long id)
			throws NotFoundException;
	
	@DeleteMapping(value = "/topic/{id}")
	public ResponseEntity<?> deleteTopic(@PathVariable("id") Long id) throws NotFoundException;
	
	@GetMapping(value = "/topic")
	public ResponseEntity<?> getAllTopics();
	
	@PutMapping(value = "/topic/{topicId}/subscribe/user/{userId}")
	public ResponseEntity<?> updateTopicSubscriber(@PathVariable("userId") Long userId, @PathVariable("topicId") Long topicId)
			throws NotFoundException;

}

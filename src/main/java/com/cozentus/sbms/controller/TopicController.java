package com.cozentus.sbms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.cozentus.sbms.dto.TopicDto;
import com.cozentus.sbms.endpoint.TopicEndpoint;
import com.cozentus.sbms.error.NotFoundException;
import com.cozentus.sbms.handler.GenericResponseHandler;
import com.cozentus.sbms.processor.TopicProcessor;

@RestController
public class TopicController implements TopicEndpoint {

	@Autowired
	private TopicProcessor topicProcessor;

	@Override
	public ResponseEntity<?> addTopics(List<String> topicNames) {
		topicProcessor.addTopics(topicNames);
		return new GenericResponseHandler.Builder().setStatus(HttpStatus.OK).setMessage("Topic added successfully")
				.create();
	}

	@Override
	public ResponseEntity<?> updateTopic(String topicName, Long id) throws NotFoundException {
		topicProcessor.updateTopic(topicName, id);
		return new GenericResponseHandler.Builder().setStatus(HttpStatus.OK).setMessage("Topic updated successfully")
				.create();
	}

	@Override
	public ResponseEntity<?> deleteTopic(Long id) throws NotFoundException {
		topicProcessor.deleteTopicById(id);
		return new GenericResponseHandler.Builder().setStatus(HttpStatus.OK).setMessage("Topic deleted successfully")
				.create();
	}

	@Override
	public ResponseEntity<?> getAllTopics() {
		List<TopicDto> response = topicProcessor.getAllTopics();
		return new GenericResponseHandler.Builder().setStatus(HttpStatus.OK).setMessage("Topic fetched successfully")
				.setData(response).create();
	}
}

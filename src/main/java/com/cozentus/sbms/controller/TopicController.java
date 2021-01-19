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
import com.cozentus.sbms.service.TopicService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TopicController implements TopicEndpoint {

	@Autowired
	private TopicService topicService;

	@Override
	public ResponseEntity<?> addTopics(List<String> topicNames) {
		log.debug("Inside addTopics() method");
		topicService.addTopics(topicNames);
		return new GenericResponseHandler.Builder().setStatus(HttpStatus.OK).setMessage("Topic added successfully")
				.create();
	}

	@Override
	public ResponseEntity<?> updateTopic(String topicName, Long id) throws NotFoundException {
		log.debug("Inside updateTopic() method");
		topicService.updateTopic(topicName, id);
		return new GenericResponseHandler.Builder().setStatus(HttpStatus.OK).setMessage("Topic updated successfully")
				.create();
	}

	@Override
	public ResponseEntity<?> deleteTopic(Long id) throws NotFoundException {
		log.debug("Inside deleteTopic() method");
		topicService.deleteTopicById(id);
		return new GenericResponseHandler.Builder().setStatus(HttpStatus.OK).setMessage("Topic deleted successfully")
				.create();
	}

	@Override
	public ResponseEntity<?> getAllTopics() {
		log.debug("Inside getAllTopics() method");
		List<TopicDto> response = topicService.getAllTopics();
		return new GenericResponseHandler.Builder().setStatus(HttpStatus.OK).setMessage("Topic fetched successfully")
				.setData(response).create();
	}
}

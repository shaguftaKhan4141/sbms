package com.cozentus.sbms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.cozentus.sbms.endpoint.TopicEndpoint;
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

}

package com.cozentus.sbms.service;

import java.util.List;

import com.cozentus.sbms.dto.TopicDto;
import com.cozentus.sbms.error.NotFoundException;

public interface TopicService {

	List<TopicDto> getAllTopics();

	void deleteTopicById(Long id) throws NotFoundException;

	void updateTopic(String topicName, Long id) throws NotFoundException;

	void addTopics(List<String> topicNames);

}
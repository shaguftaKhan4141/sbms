package com.cozentus.sbms.processor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cozentus.sbms.domain.Topic;
import com.cozentus.sbms.repository.TopicRepository;

@Service
public class TopicProcessor {

	@Autowired
	private TopicRepository topicRepository;

	public void addTopics(List<String> topicNames) {
		topicNames.stream().forEach(topicName -> {
			Topic topic = new Topic();
			topic.setName(topicName);
			topicRepository.save(topic);
		});
	}
}

package com.cozentus.sbms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cozentus.sbms.domain.Topic;
import com.cozentus.sbms.dto.TopicDto;
import com.cozentus.sbms.error.NotFoundException;
import com.cozentus.sbms.repository.TopicRepository;

@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicRepository topicRepository;

	
	@Override
	public void addTopics(List<String> topicNames) {
		topicNames.stream().forEach(topicName -> {
			Topic topic = topicRepository.findByName(topicName);
			if (topic == null) {
				topic = new Topic();
				topic.setName(topicName);
				topic.setCreatedBy("API");
				topic.setCreatedDate(new Date());
				topicRepository.save(topic);
			}
		});
	}

	
	@Override
	public void updateTopic(String topicName, Long id) throws NotFoundException {
		Optional<Topic> topicFromdB = topicRepository.findById(id);
		if (topicFromdB.isPresent()) {
			Topic topic = topicFromdB.get();
			topic.setName(topicName);
			topic.setUpdatedBy("API");
			topic.setUpdatedDate(new Date());
			topicRepository.save(topic);
		} else {
			throw new NotFoundException("Topic not found for id : " + id);
		}
	}

	
	@Override
	public void deleteTopicById(Long id) throws NotFoundException {
		Optional<Topic> topicFromdB = topicRepository.findById(id);
		if (topicFromdB.isPresent()) {
			topicRepository.delete(topicFromdB.get());
		} else {
			throw new NotFoundException("Topic not found for id : " + id);
		}
	}

	@Override
	public List<TopicDto> getAllTopics() {
		List<TopicDto> topicDtos = new ArrayList<>();
		List<Topic> topicsFromDb = topicRepository.findAll();
		if (!CollectionUtils.isEmpty(topicsFromDb)) {
			topicsFromDb.stream().forEach(topic -> {
				TopicDto topicDto = new TopicDto(topic.getId(), topic.getName());
				topicDtos.add(topicDto);
			});
		}
		return topicDtos;
	}
}

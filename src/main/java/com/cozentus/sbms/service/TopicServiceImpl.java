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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicRepository topicRepository;

	@Override
	public void addTopics(List<String> topicNames) {
		log.info("Start executing add Topics method");
		topicNames.stream().forEach(topicName -> {
			log.info("Extracting topics from db having topicName : {}", topicName);
			Topic topic = topicRepository.findByName(topicName);
			if (topic == null) {
				log.info("Topic not present in db, hence adding new topic in db");
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
		log.info("Start executing update Topic method");
		log.info("Extracting topic from db having topicId : {}", id);
		Optional<Topic> topicFromdB = topicRepository.findById(id);
		if (topicFromdB.isPresent()) {
			log.info("Topic extracted from db, updating existing topic data");
			Topic topic = topicFromdB.get();
			topic.setName(topicName);
			topic.setUpdatedBy("API");
			topic.setUpdatedDate(new Date());
			topicRepository.save(topic);
		} else {
			log.error("Topic not found for id : " + id);
			throw new NotFoundException("Topic not found for id : " + id);
		}
	}

	
	@Override
	public void deleteTopicById(Long id) throws NotFoundException {
		log.info("Start executing delete topic method");
		log.info("Extracting topic from db having topicId : {}",id);
		Optional<Topic> topicFromdB = topicRepository.findById(id);
		if (topicFromdB.isPresent()) {
			log.info("Topic present in db, hence deleting existing topic");
			topicRepository.delete(topicFromdB.get());
		} else {
			log.error("Topic not found for id : " + id);
			throw new NotFoundException("Topic not found for id : " + id);
		}
	}

	@Override
	public List<TopicDto> getAllTopics() {
		log.info("Start executing fetching all topics method");
		List<TopicDto> topicDtos = new ArrayList<>();
		log.info("Extracting all topics from db");
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

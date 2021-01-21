package com.conzentus.sbms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.conzentus.sbms.TestData;
import com.cozentus.sbms.domain.Topic;
import com.cozentus.sbms.dto.TopicDto;
import com.cozentus.sbms.error.NotFoundException;
import com.cozentus.sbms.repository.TopicRepository;
import com.cozentus.sbms.service.TopicServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TopicServiceTest {

	@Mock
	TopicRepository topicRepository;

	@InjectMocks
	TopicServiceImpl topicServiceImpl;

	@Test
	void getAllTopics() {
		List<Topic> topics = TestData.getAllMockTopics();
		when(topicRepository.findAll()).thenReturn(topics);
		List<TopicDto> topicDto = topicServiceImpl.getAllTopics();
		assertEquals(topics.get(0).getId(), topicDto.get(0).getId());
	}

	@Test
	void deleteTopicById() throws NotFoundException {
		when(topicRepository.existsById(Mockito.any(Long.class))).thenReturn(true);
		topicServiceImpl.deleteTopicById(1L);
		Mockito.verify(topicRepository, Mockito.times(1)).deleteById(Mockito.any(Long.class));
	}

	@Test
	void addTopics() {
		List<String> topicNames = TestData.mockTopicNames();
		when(topicRepository.findByName((Mockito.any(String.class)))).thenReturn(null);
		topicServiceImpl.addTopics(topicNames);
	}
}

package com.conzentus.sbms;

import java.util.ArrayList;
import java.util.List;

import com.cozentus.sbms.domain.Blog;
import com.cozentus.sbms.domain.Topic;
import com.cozentus.sbms.domain.User;
import com.cozentus.sbms.dto.BlogRequestDto;
import com.cozentus.sbms.enumeration.BlogStatus;

public class TestData {

	public static Blog getMockBlog() {
		Blog blog = new Blog();
		blog.setId(1L);
		blog.setAuthorId(1L);
		blog.setBlogLink("http://test_link");
		blog.setCoAuthorId(2L);
		blog.setTitle("test");
		blog.setStatus(BlogStatus.SYNOPSIS_APPROVED.toString());
		return blog;
	}
	
	public static BlogRequestDto getMockBlogRequestDto() {
		BlogRequestDto requestDto = new BlogRequestDto();
		requestDto.setAuthorId(1L);
		requestDto.setBlogLink("http://test_link");
		requestDto.setCoAuthorId(2L);
		return requestDto;
	}
	
	public static List<Topic> getAllMockTopics() {
		List<Topic> topics = new ArrayList<>();
		Topic topic = new Topic();
		topic.setId(1L);
		topic.setName("Sports");
		topics.add(topic);
		return topics;
	}
	
	public static Topic getMockTopic() {
		Topic topic = new Topic();
		topic.setId(1L);
		topic.setName("Sports");
		return topic;
	}
	
	public static List<String> mockTopicNames() {
		List<String> topicNames = new  ArrayList<>();
		topicNames.add("Sports");
		topicNames.add("Politics");
		return topicNames;
	}
	
	public static List<User> getAllMockUsers() {
		List<User> users = new  ArrayList<>();
		User user = new User();
		user.setId(1L);
		user.setFirstName("Shashank");
		user.setLastName("Sharma");
		user.setContactNo("826922794");
		users.add(user);
		return users;
	}
	
	public static User getMockUser() {
		User user = new User();
		user.setId(1L);
		user.setFirstName("Shashank");
		user.setLastName("Sharma");
		user.setContactNo("826922794");
		return user;
	}
}

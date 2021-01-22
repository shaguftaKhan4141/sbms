package com.cozentus.sbms.mapper;

import java.util.Date;

import com.cozentus.sbms.domain.Blog;
import com.cozentus.sbms.domain.Topic;
import com.cozentus.sbms.dto.BlogRequestDto;
import com.cozentus.sbms.dto.BlogResponseDto;
import com.cozentus.sbms.dto.TopicDto;
import com.cozentus.sbms.enumeration.BlogStatus;

import static com.cozentus.sbms.util.CommonUtils.exists;

public class BlogMapper {

	public static Blog blogRequestDtoToBlogForCreate(BlogRequestDto blogRequestDto, Topic topic, String user) {
		
		Blog blog = new Blog();
		blog.setSynopsis(blogRequestDto.getSynopsis());
		blog.setTitle(blogRequestDto.getTitle());
		blog.setTopic(topic);
		blog.setAuthorId(blogRequestDto.getAuthorId());
		blog.setCoAuthorId(blogRequestDto.getCoAuthorId());
		blog.setStatus(BlogStatus.SYNOPSIS_PENDING.toString());
		blog.setCreatedBy(user);
		blog.setCreatedDate(new Date());
		blog.setUpdatedBy(user);
		blog.setUpdatedDate(new Date());
		return blog;
	}
	
	public static Blog blogRequestDtoToBlogForUpdate(Blog blog, Topic topic, BlogRequestDto blogRequestDto, String user) {
		
		if(exists(blogRequestDto.getSynopsis()))
		   blog.setSynopsis(blogRequestDto.getSynopsis());
		
		if(exists(blogRequestDto.getTitle()))
		   blog.setTitle(blogRequestDto.getTitle());
		
		if(topic!=null)
		   blog.setTopic(topic);
		
		blog.setUpdatedBy(user);
		blog.setUpdatedDate(new Date());
		
		return blog;
	}
	
	public static BlogResponseDto blogToBlogResponseDto(Blog blog) {
		
		TopicDto topicDto = new TopicDto(blog.getTopic().getId(), blog.getTopic().getName());
		
		return BlogResponseDto.builder()
		.id(blog.getId())
		.authorId(blog.getAuthorId())
		.coAuthorId(blog.getCoAuthorId())
		.blogLink(blog.getBlogLink())
        .status(blog.getStatus())
        .synopsis(blog.getSynopsis())
        .title(blog.getTitle())
        .topic(topicDto)
        .createdBy(blog.getCreatedBy())
        .createdDate(blog.getCreatedDate())
		.updatedBy(blog.getUpdatedBy())
		.updatedDate(blog.getUpdatedDate())
		.build();

	}
}

package com.cozentus.sbms.mapper;

import java.util.Date;

import com.cozentus.sbms.domain.Blog;
import com.cozentus.sbms.dto.BlogRequestDto;
import com.cozentus.sbms.dto.BlogResponseDto;
import com.cozentus.sbms.enumeration.BlogStatus;

import static com.cozentus.sbms.util.CommonUtils.exists;

public class BlogMapper {

	public static Blog blogRequestDtoToBlogForCreate(BlogRequestDto blogRequestDto, String user) {
		
		Blog blog = new Blog();
		blog.setSynopsis(blogRequestDto.getSynopsis());
		blog.setTitle(blogRequestDto.getTitle());
		blog.setTopic(blogRequestDto.getTopic());
		blog.setAuthorId(blogRequestDto.getAuthorId());
		blog.setCoAuthorId(blogRequestDto.getCoAuthorId());
		blog.setStatus(BlogStatus.SYNOPSIS_PENDING.toString());
		blog.setCreatedBy(user);
		blog.setCreatedDate(new Date());
		
		return blog;
	}
	
	public static Blog blogRequestDtoToBlogForUpdate(Blog blog, BlogRequestDto blogRequestDto, String user) {
		
		if(exists(blogRequestDto.getSynopsis()))
		   blog.setSynopsis(blogRequestDto.getSynopsis());
		
		if(exists(blogRequestDto.getTitle()))
		   blog.setTitle(blogRequestDto.getTitle());
		
		if(blogRequestDto.getTopic()!=null)
		   blog.setTopic(blogRequestDto.getTopic());
		
		blog.setUpdatedBy(user);
		blog.setUpdatedDate(new Date());
		
		return blog;
	}
	
	public static BlogResponseDto blogToBlogResponseDto(Blog blog) {
		
		return BlogResponseDto.builder()
		.authorId(blog.getAuthorId())
		.coAuthorId(blog.getCoAuthorId())
		.blogLink(blog.getBlogLink())
        .status(blog.getStatus())
        .synopsis(blog.getSynopsis())
        .title(blog.getTitle())
        .topic(blog.getTopic())
        .createdBy(blog.getCreatedBy())
        .createdDate(blog.getCreatedDate())
		.updatedBy(blog.getUpdatedBy())
		.updatedDate(blog.getUpdatedDate())
		.build();

	}
}

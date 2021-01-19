package com.conzentus.sbms;

import com.cozentus.sbms.domain.Blog;
import com.cozentus.sbms.dto.BlogRequestDto;
import com.cozentus.sbms.enumeration.BlogStatus;

public class TestData {

	public static Blog getMockBlog() {
		Blog blog = new Blog();
		blog.setId(1L);
		blog.setAuthorId(1L);
		blog.setBlogLink("http://test_link");
		blog.setCoAuthorId(2L);
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
}

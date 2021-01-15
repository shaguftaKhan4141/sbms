package com.cozentus.sbms.service;

import com.cozentus.sbms.dto.BlogRequestDto;
import com.cozentus.sbms.dto.BlogResponseDto;
import com.cozentus.sbms.error.InvalidDataException;
import com.cozentus.sbms.error.NotFoundException;
import com.cozentus.sbms.error.UserNotAuthorizedException;

public interface BlogService {

	BlogResponseDto createBlog(BlogRequestDto blogDto);

	// this does not updates the status of Blog
	BlogResponseDto updateBlog(BlogRequestDto blogDto, Long id) throws NotFoundException;

	void updateBlogStatus(Long blogId, String status)
			throws NotFoundException, UserNotAuthorizedException, InvalidDataException;

	void deleteBlog(Long id) throws NotFoundException;

	BlogResponseDto getBlog(Long blogId) throws NotFoundException;

}
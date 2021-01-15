package com.cozentus.sbms.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cozentus.sbms.dto.BlogRequestDto;
import com.cozentus.sbms.error.InvalidDataException;
import com.cozentus.sbms.error.NotFoundException;
import com.cozentus.sbms.error.UserNotAuthorizedException;

public interface BlogEndpoint {
    
	@PostMapping
	ResponseEntity<?> createBlog(BlogRequestDto blogDto);
    
	@PutMapping
	ResponseEntity<?> updateBlog(@RequestBody BlogRequestDto blogDto, @PathVariable("blog_id") Long id) throws NotFoundException;

	@PutMapping(value = "/status")
	ResponseEntity<?> updateBlogStatus(@RequestBody String status, @PathVariable("blog_id") Long id)
			throws NotFoundException, UserNotAuthorizedException, InvalidDataException;

	@DeleteMapping
	ResponseEntity<?> deleteBlog(@PathVariable("blog_id") Long id) throws NotFoundException;

}
package com.cozentus.sbms.endpoint;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cozentus.sbms.dto.BlogRequestDto;
import com.cozentus.sbms.error.InvalidDataException;
import com.cozentus.sbms.error.NotFoundException;
import com.cozentus.sbms.error.UserNotAuthorizedException;

public interface BlogEndpoint {
    
	@PostMapping
	ResponseEntity<?> createBlog(@RequestBody BlogRequestDto blogDto) throws NotFoundException;
    
	@PutMapping
	ResponseEntity<?> updateBlog(@RequestBody BlogRequestDto blogDto, @PathVariable("blog_id") Long id) throws NotFoundException;

	@PutMapping(value = "/status")
	ResponseEntity<?> updateBlogStatus(@RequestBody String status, @PathVariable("blog_id") Long id)
			throws NotFoundException, UserNotAuthorizedException, InvalidDataException;

	@DeleteMapping
	ResponseEntity<?> deleteBlog(@PathVariable("blog_id") Long id) throws NotFoundException;

	@PutMapping(value = "/document/{blog_id}")
	ResponseEntity<?> saveOrUpdateDocument(@RequestParam("file") MultipartFile file, @PathVariable("blog_id") Long id) throws NotFoundException, IOException;

}
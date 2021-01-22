package com.cozentus.sbms.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cozentus.sbms.dto.BlogRequestDto;
import com.cozentus.sbms.endpoint.BlogEndpoint;
import com.cozentus.sbms.error.InvalidDataException;
import com.cozentus.sbms.error.NotFoundException;
import com.cozentus.sbms.error.UserNotAuthorizedException;
import com.cozentus.sbms.handler.GenericResponseHandler;
import com.cozentus.sbms.service.BlogService;

@RestController
@RequestMapping("/blog")
public class BlogController implements BlogEndpoint {

	@Autowired
	BlogService blogService;
		
	@Override
	public ResponseEntity<?> createBlog(BlogRequestDto blogDto) throws NotFoundException{
		return new GenericResponseHandler
				.Builder()
				.setStatus(HttpStatus.OK)
				.setMessage("Blog created successfully")
				.setData(blogService.createBlog(blogDto))
				.create();
	}
	
	
	@Override
	public ResponseEntity<?> updateBlog(BlogRequestDto blogDto, Long id) throws NotFoundException{
		return new GenericResponseHandler
				.Builder()
				.setStatus(HttpStatus.OK)
				.setMessage("Blog updated successfully")
				.setData(blogService.updateBlog(blogDto, id))
				.create();
	}
	
	@Override
	public ResponseEntity<?> updateBlogStatus(String status, Long id) throws NotFoundException, UserNotAuthorizedException, InvalidDataException{
		blogService.updateBlogStatus(id, status);
		return new GenericResponseHandler
				.Builder()
				.setStatus(HttpStatus.NO_CONTENT)
				.setMessage("Blog status updated successfully")
				.create();
	}
	
	@Override
	public ResponseEntity<?> deleteBlog(Long id) throws NotFoundException{
		blogService.deleteBlog(id);
		return new GenericResponseHandler
				.Builder()
				.setStatus(HttpStatus.NO_CONTENT)
				.setMessage("Blog status updated successfully")
				.create();
	}
	
	@Override
	public ResponseEntity<?> saveOrUpdateDocument(MultipartFile file, Long id) throws NotFoundException, IOException{
		blogService.saveOrUpdateDocument(file, id);
		return new GenericResponseHandler
				.Builder()
				.setStatus(HttpStatus.NO_CONTENT)
				.setMessage("Document uploaded successfully")
				.create();
	}
}

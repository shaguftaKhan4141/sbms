package com.cozentus.sbms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozentus.sbms.dto.UserResponseDto;
import com.cozentus.sbms.endpoint.AdminEndpoint;
import com.cozentus.sbms.enumeration.UserRequestStatus;
import com.cozentus.sbms.error.InvalidDataException;
import com.cozentus.sbms.error.NotFoundException;
import com.cozentus.sbms.error.UserNotAuthorizedException;
import com.cozentus.sbms.handler.GenericResponseHandler;
import com.cozentus.sbms.service.AdminService;
import com.cozentus.sbms.service.BlogService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController implements AdminEndpoint {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private BlogService blogService;

	@Override
	public ResponseEntity<?> getAllUsersByRoleId(Long roleId) throws NotFoundException {
		log.debug("Inside getAllUserByRole() method");
		List<UserResponseDto> response = adminService.getAllUsersByRoleId(roleId);
		return new GenericResponseHandler.Builder().setStatus(HttpStatus.OK).setMessage("User fetched successfully")
				.setData(response).create();
	}

	@Override
	public ResponseEntity<?> getAllUsers(String requestStatus) throws NotFoundException, InvalidDataException {
		log.debug("Inside getAllUsers() method");
		List<UserResponseDto> response = adminService.getAllUsers(requestStatus);
		return new GenericResponseHandler.Builder().setStatus(HttpStatus.OK).setMessage("User fetched successfully")
				.setData(response).create();
	}

	@Override
	public ResponseEntity<?> approvedUserRequest(Long userId, String requestStatus) throws NotFoundException, InvalidDataException {
		log.debug("Inside approvedUserRequest() method");
		if(requestStatus.equalsIgnoreCase(UserRequestStatus.APPROVED.toString()) || 
				requestStatus.equalsIgnoreCase(UserRequestStatus.REJECTED.toString())) {
			adminService.approvedUserRequest(userId, requestStatus);
		} else {
			log.error("User Request Status must be APPROVED or REJECTED");
			throw new InvalidDataException("User Request Status must be APPROVED or REJECTED");
		}
		return new GenericResponseHandler.Builder().setStatus(HttpStatus.OK)
				.setMessage("User Request status changed").create();
	}
	
	
	@Override
	public ResponseEntity<?> approveSynopsis(Long blogId, String status) throws NotFoundException, UserNotAuthorizedException, InvalidDataException {
		blogService.updateBlogStatus(blogId, status);
		return new GenericResponseHandler.Builder().setStatus(HttpStatus.OK)
				.setMessage("Synopsis approved!").create();
	}
}

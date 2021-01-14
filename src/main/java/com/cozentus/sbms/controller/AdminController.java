package com.cozentus.sbms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.cozentus.sbms.dto.UserResponseDto;
import com.cozentus.sbms.endpoint.AdminEndpoint;
import com.cozentus.sbms.error.InvalidDataException;
import com.cozentus.sbms.error.UserNotFoundException;
import com.cozentus.sbms.handler.GenericResponseHandler;
import com.cozentus.sbms.processor.AdminProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AdminController implements AdminEndpoint {

	@Autowired
	private AdminProcessor adminProcessor;

	@Override
	public ResponseEntity<?> getAllUsersByRoleId(Long roleId) throws UserNotFoundException {
		log.debug("Inside getAllUserByRole() method"); 
		List<UserResponseDto> response = adminProcessor.getAllUsersByRoleId(roleId);
		return new GenericResponseHandler.Builder().setStatus(HttpStatus.OK).setMessage("User fetched successfully")
				.setData(response).create();
	}

	@Override
	public ResponseEntity<?> getAllUsers(String requestStatus) throws UserNotFoundException, InvalidDataException {
		List<UserResponseDto> response = adminProcessor.getAllUsers(requestStatus);
		return new GenericResponseHandler.Builder().setStatus(HttpStatus.OK).setMessage("User fetched successfully")
				.setData(response).create();
	}
}

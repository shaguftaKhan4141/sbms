package com.cozentus.sbms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.cozentus.sbms.dto.UserResponseDto;
import com.cozentus.sbms.endpoint.AdminEndpoint;
import com.cozentus.sbms.error.InvalidDataException;
import com.cozentus.sbms.error.NotFoundException;
import com.cozentus.sbms.handler.GenericResponseHandler;
import com.cozentus.sbms.service.AdminService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AdminController implements AdminEndpoint {

	@Autowired
	private AdminService adminService;

	@Override
	public ResponseEntity<?> getAllUsersByRoleId(Long roleId) throws NotFoundException {
		log.debug("Inside getAllUserByRole() method");
		List<UserResponseDto> response = adminService.getAllUsersByRoleId(roleId);
		return new GenericResponseHandler.Builder().setStatus(HttpStatus.OK).setMessage("User fetched successfully")
				.setData(response).create();
	}

	@Override
	public ResponseEntity<?> getAllUsers(String requestStatus) throws NotFoundException, InvalidDataException {
		List<UserResponseDto> response = adminService.getAllUsers(requestStatus);
		return new GenericResponseHandler.Builder().setStatus(HttpStatus.OK).setMessage("User fetched successfully")
				.setData(response).create();
	}

	@Override
	public ResponseEntity<?> approvedUserRequest(Long userId) throws NotFoundException {
		adminService.approvedUserRequest(userId);
		return new GenericResponseHandler.Builder().setStatus(HttpStatus.OK)
				.setMessage("User Request Approved successfully").create();
	}
}

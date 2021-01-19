package com.cozentus.sbms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.cozentus.sbms.service.UserService;

import lombok.extern.slf4j.Slf4j;

import com.cozentus.sbms.dto.UserRequestDto;
import com.cozentus.sbms.dto.UserResponseDto;
import com.cozentus.sbms.endpoint.UserEndpoint;
import com.cozentus.sbms.error.InvalidDataException;
import com.cozentus.sbms.error.UserAlreadyExistsException;
import com.cozentus.sbms.handler.GenericResponseHandler;

@Slf4j
@RestController
public class UserController implements UserEndpoint {

	@Autowired
	UserService userService;

	@Override
	public ResponseEntity<?> saveAuthor(@Valid UserRequestDto userDto)
			throws UserAlreadyExistsException, InvalidDataException {
		log.debug("Inside saveAuthor() method");
		UserResponseDto user = userService.signup(userDto);
		return new GenericResponseHandler.Builder().setStatus(HttpStatus.OK).setMessage("User signup successfully")
				.setData(user).create();
	}
}

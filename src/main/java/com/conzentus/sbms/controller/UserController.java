package com.conzentus.sbms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.conzentus.sbms.domain.User;
import com.conzentus.sbms.dto.UserDto;
import com.conzentus.sbms.error.InvalidDataException;
import com.conzentus.sbms.error.UserAlreadyExistsException;
import com.conzentus.sbms.service.UserService;


@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/user/signup")
	public @ResponseBody ResponseEntity<User> saveAuthor(@RequestBody UserDto userDto) throws UserAlreadyExistsException, InvalidDataException {
		return new ResponseEntity<>(userService.signup(userDto), HttpStatus.OK);
	}
	
}

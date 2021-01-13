package com.conzentus.sbms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.conzentus.sbms.domain.User;
import com.conzentus.sbms.service.UserService;


@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/user/signup")
	public @ResponseBody ResponseEntity<User> saveAuthor(@RequestBody User user) {
		return new ResponseEntity<>(userService.signupUser(user), HttpStatus.OK);
	}
	
}

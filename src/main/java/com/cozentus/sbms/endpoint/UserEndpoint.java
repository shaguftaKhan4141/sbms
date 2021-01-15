package com.cozentus.sbms.endpoint;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cozentus.sbms.dto.UserRequestDto;
import com.cozentus.sbms.error.InvalidDataException;
import com.cozentus.sbms.error.UserAlreadyExistsException;

public interface UserEndpoint {

	@PostMapping(value = "/user/signup")
	public ResponseEntity<?> saveAuthor(@RequestBody @Valid UserRequestDto userDto)
			throws UserAlreadyExistsException, InvalidDataException;
}

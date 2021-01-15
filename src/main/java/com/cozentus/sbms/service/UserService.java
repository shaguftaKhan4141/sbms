package com.cozentus.sbms.service;

import com.cozentus.sbms.dto.UserRequestDto;
import com.cozentus.sbms.dto.UserResponseDto;
import com.cozentus.sbms.error.InvalidDataException;
import com.cozentus.sbms.error.UserAlreadyExistsException;

public interface UserService {

	UserResponseDto signup(UserRequestDto userDto) throws UserAlreadyExistsException, InvalidDataException;
}
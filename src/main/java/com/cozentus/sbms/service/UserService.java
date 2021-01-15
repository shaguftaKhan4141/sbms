package com.cozentus.sbms.service;

import com.cozentus.sbms.dto.UserDto;
import com.cozentus.sbms.error.InvalidDataException;
import com.cozentus.sbms.error.UserAlreadyExistsException;

public interface UserService {

	UserDto signup(UserDto userDto) throws UserAlreadyExistsException, InvalidDataException;
}
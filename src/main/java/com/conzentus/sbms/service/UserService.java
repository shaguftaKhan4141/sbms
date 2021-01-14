package com.conzentus.sbms.service;

import com.conzentus.sbms.domain.User;
import com.conzentus.sbms.dto.UserDto;
import com.conzentus.sbms.error.InvalidDataException;
import com.conzentus.sbms.error.UserAlreadyExistsException;

public interface UserService {

	UserDto signup(UserDto userDto) throws UserAlreadyExistsException, InvalidDataException;
}

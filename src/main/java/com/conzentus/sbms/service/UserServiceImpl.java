package com.conzentus.sbms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.conzentus.sbms.domain.Role;
import com.conzentus.sbms.domain.User;
import com.conzentus.sbms.dto.UserDto;
import com.conzentus.sbms.error.InvalidDataException;
import com.conzentus.sbms.error.UserAlreadyExistsException;
import com.conzentus.sbms.mapper.UserMapper;
import com.conzentus.sbms.repository.BlogUserRepository;
import com.conzentus.sbms.repository.RoleRepository;
import com.conzentus.sbms.util.CommonUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	BlogUserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto signup(UserDto userDto) throws UserAlreadyExistsException, InvalidDataException {
		validateUser(userDto);
		Role role = roleRepository.findByName(userDto.getRole());
		User user = UserMapper.userDtoToUser(userDto, role, passwordEncoder.encode(userDto.getPassword()));
		return UserMapper.userToUserDto(userRepository.save(user));
	}

	private void validateUser(UserDto userDto) throws UserAlreadyExistsException, InvalidDataException {
		
		if(!CommonUtils.isValidEmail(userDto.getEmailId())) {
			throw new InvalidDataException("Invalid email format!");
		}
		
		if (userRepository.existsByUserName(userDto.getUserName())) {
			throw new UserAlreadyExistsException("UserName already exists!");
		}

		if (userRepository.existsBycontactNo(userDto.getContactNo())) {
			throw new UserAlreadyExistsException("Phone no. already exists!");
		}

		if (userRepository.existsByEmailId(userDto.getEmailId())) {
			throw new UserAlreadyExistsException("email already exists!");
		}
	}

}

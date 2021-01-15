package com.cozentus.sbms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cozentus.sbms.domain.Role;
import com.cozentus.sbms.domain.User;
import com.cozentus.sbms.dto.UserRequestDto;
import com.cozentus.sbms.dto.UserResponseDto;
import com.cozentus.sbms.error.InvalidDataException;
import com.cozentus.sbms.error.UserAlreadyExistsException;
import com.cozentus.sbms.mapper.UserMapper;
import com.cozentus.sbms.repository.BlogUserRepository;
import com.cozentus.sbms.repository.RoleRepository;
import com.cozentus.sbms.util.CommonUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	BlogUserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserResponseDto signup(UserRequestDto userDto) throws UserAlreadyExistsException, InvalidDataException {
		validateUser(userDto);
		Optional<Role> role = roleRepository.findById(userDto.getRoleId());
		User user = UserMapper.userDtoToUser(userDto, role.get(), passwordEncoder.encode(userDto.getPassword()));
		return UserMapper.userToUserDto(userRepository.save(user));
	}

	private void validateUser(UserRequestDto userDto) throws UserAlreadyExistsException, InvalidDataException {
		if (!CommonUtils.isValidEmail(userDto.getEmailId())) {
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

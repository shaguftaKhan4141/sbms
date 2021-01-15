package com.cozentus.sbms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cozentus.sbms.domain.User;
import com.cozentus.sbms.dto.UserResponseDto;
import com.cozentus.sbms.enumeration.UserRequestStatus;
import com.cozentus.sbms.error.InvalidDataException;
import com.cozentus.sbms.error.NotFoundException;
import com.cozentus.sbms.repository.BlogUserRepository;

import lombok.extern.slf4j.Slf4j;

/* 
 * Service class which contains all the business
 * logics or functionality for Administrator
 * 
 * @author Shashank
*/

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private BlogUserRepository blogUserRepository;

	@Override
	public List<UserResponseDto> getAllUsersByRoleId(Long roleId) throws NotFoundException {
		log.info("Start executing method to extract users by roleId");
		List<UserResponseDto> usersResponse = new ArrayList<>();
		log.info("Triggering query to extract users from db having roleId : {}", roleId);
		List<User> usersFromdB = blogUserRepository.findByRoleId(roleId);
		if (!CollectionUtils.isEmpty(usersFromdB)) {
			log.info("Users extracted from dB, iterating data to make response");
			usersFromdB.stream().forEach(user -> {
				UserResponseDto userResponseDto = new UserResponseDto();
				// BeanUtils : copy model class data to DTO class
				BeanUtils.copyProperties(user, userResponseDto);
				userResponseDto.setRole(user.getRole().getName());
				userResponseDto.setRequestStatus(user.getStatus());
				usersResponse.add(userResponseDto);
			});
		} else {
			log.error("User not found for roleId : {}", roleId);
			throw new NotFoundException("User not found for roleId : " + roleId);
		}
		log.info("Execution end of extract users by roleId method");
		return usersResponse;
	}

	@Override
	public List<UserResponseDto> getAllUsers(String requestStatus) throws NotFoundException, InvalidDataException {
		List<User> usersFromdB = null;
		List<UserResponseDto> usersResponse = new ArrayList<>();
		if (requestStatus != null) {
			if (requestStatus.equalsIgnoreCase(UserRequestStatus.PENDING.toString())
					|| requestStatus.equalsIgnoreCase(UserRequestStatus.APPROVED.toString())) {
				usersFromdB = blogUserRepository.findByStatus(requestStatus);
			} else {
				log.error("Status must be Pending or Approved");
				throw new InvalidDataException("Status must be Pending or Approved");
			}
		} else {
			usersFromdB = blogUserRepository.findAllUsers();
		}
		if (!CollectionUtils.isEmpty(usersFromdB)) {
			usersFromdB.stream().forEach(user -> {
				UserResponseDto userResponseDto = new UserResponseDto();
				// BeanUtils : copy model class data to DTO class
				BeanUtils.copyProperties(user, userResponseDto);
				userResponseDto.setRole(user.getRole().getName());
				userResponseDto.setRequestStatus(user.getStatus());
				usersResponse.add(userResponseDto);
			});
		} else {
			log.error("No User found in database");
			throw new NotFoundException("No User found in database");
		}
		return usersResponse;
	}

	@Override
	public void approvedUserRequest(Long userId) throws NotFoundException {
		Optional<User> userFromDb = blogUserRepository.findById(userId);
		if (userFromDb.isPresent()) {
			User user = userFromDb.get();
			user.setStatus(UserRequestStatus.APPROVED.toString());
			blogUserRepository.save(user);
		} else {
			throw new NotFoundException("No User found in database for userId : " + userId);
		}
	}
}

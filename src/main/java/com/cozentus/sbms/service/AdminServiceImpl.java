package com.cozentus.sbms.service;

import java.util.ArrayList;
import java.util.Date;
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
		log.info("Start executing method to get all users");
		List<User> usersFromdB = null;
		List<UserResponseDto> usersResponse = new ArrayList<>();
		if (requestStatus != null) {
			log.info("Extracting all users from db having status : {}", requestStatus);
			if (requestStatus.equalsIgnoreCase(UserRequestStatus.PENDING.toString())
					|| requestStatus.equalsIgnoreCase(UserRequestStatus.APPROVED.toString())) {
				usersFromdB = blogUserRepository.findByStatus(requestStatus);
			} else {
				log.error("Status must be Pending or Approved");
				throw new InvalidDataException("Status must be Pending or Approved");
			}
		} else {
			log.info("Extracting all users from DB");
			usersFromdB = blogUserRepository.findAllUsers();
		}
		if (!CollectionUtils.isEmpty(usersFromdB)) {
			log.info("Users extracted from DB, iterating users record to make final response");
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
	public void approvedUserRequest(Long userId, String requestStatus) throws NotFoundException {
		log.info("Start executing method to approve user request");
		log.info("Extracting user from db having userId : {}", userId);
		Optional<User> userFromDb = blogUserRepository.findById(userId);
		if (userFromDb.isPresent()) {
			log.info("User extracted from db going to update user");
			User user = userFromDb.get();
			if(requestStatus.equalsIgnoreCase(UserRequestStatus.APPROVED.toString())) {
				user.setStatus(UserRequestStatus.APPROVED.toString());
			} else if(requestStatus.equalsIgnoreCase(UserRequestStatus.REJECTED.toString())) {
				user.setStatus(UserRequestStatus.REJECTED.toString());
			}
			user.setUpdatedBy("API");
			user.setUpdatedDate(new Date());
			blogUserRepository.save(user);
			log.info("User data updated successfully.");
			// TO DO : Send notification to user regarding requestStatus
		} else {
			log.error("No User found in database for userId : " + userId);
			throw new NotFoundException("No User found in database for userId : " + userId);
		}
	}
}

package com.cozentus.sbms.processor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cozentus.sbms.domain.User;
import com.cozentus.sbms.dto.UserResponseDto;
import com.cozentus.sbms.error.UserNotFoundException;
import com.cozentus.sbms.repository.BlogUserRepository;

import lombok.extern.slf4j.Slf4j;

/* Service class which contains all the business
 * logics or functionality for Administrator
 * 
 * @author Shashank
*/

@Slf4j
@Service
public class AdminProcessor {

	@Autowired
	private BlogUserRepository blogUserRepository;

	public List<UserResponseDto> getAllUsersByRoleId(Long roleId) throws UserNotFoundException {
		log.info("Start executing method to extract users by roleId");
		List<UserResponseDto> usersResponse = new ArrayList<>();
		log.info("Triggering query to extract users from db having roleId : {}", roleId);
		List<User> usersFromdB = blogUserRepository.findByRoleId(roleId);
		if (!CollectionUtils.isEmpty(usersFromdB)) {
			log.info("Users extracted from dB, iterating data to make response");
			usersFromdB.stream().forEach(user -> {
				UserResponseDto userResponseDto = new UserResponseDto();
				// copy model class data to DTO class
				BeanUtils.copyProperties(user, userResponseDto);
				userResponseDto.setRole(user.getRole().getName());
				userResponseDto.setRequestStatus(user.getStatus());
				usersResponse.add(userResponseDto);
			});
		} else {
			log.error("User not found for roleId : {}", roleId);
			throw new UserNotFoundException("User not found for roleId : " + roleId);
		}
		log.info("Execution end of extract users by roleId method");
		return usersResponse;
	}
}

package com.cozentus.sbms.mapper;

import com.cozentus.sbms.domain.Role;
import com.cozentus.sbms.domain.User;
import com.cozentus.sbms.dto.UserRequestDto;
import com.cozentus.sbms.dto.UserResponseDto;
import com.cozentus.sbms.enumeration.UserRequestStatus;
import com.cozentus.sbms.util.CommonUtils;

public class UserMapper {

	public static User userDtoToUser(UserRequestDto userDto, Role role, String encodedPassword) {
		User user = new User();
		user.setContactNo(CommonUtils.getNonNullable(userDto.getContactNo()));
		user.setEmailId(CommonUtils.getNonNullable(userDto.getEmailId()));
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setPassword(encodedPassword);
		user.setRole(role);
		user.setStatus(UserRequestStatus.PENDING.toString());
		user.setUserName(userDto.getUserName());
		return user;
	}
	
	public static UserResponseDto userToUserDto(User user) {
		return UserResponseDto.builder()
				.contactNo(user.getContactNo())
				.emailId(user.getEmailId())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.role(user.getRole().getName())
				.userName(user.getUserName())
				.build();
	}

}

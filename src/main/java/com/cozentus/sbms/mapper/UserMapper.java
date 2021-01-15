package com.cozentus.sbms.mapper;

import com.cozentus.sbms.domain.Role;
import com.cozentus.sbms.domain.User;
import com.cozentus.sbms.domain.UserStatus;
import com.cozentus.sbms.dto.UserDto;
import com.cozentus.sbms.util.CommonUtils;

public class UserMapper {

	public static User userDtoToUser(UserDto userDto, Role role, String encodedPassword) {
		User user = new User();
		user.setContactNo(CommonUtils.getNonNullable(userDto.getContactNo()));
		user.setEmailId(CommonUtils.getNonNullable(userDto.getEmailId()));
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setPassword(encodedPassword);
		user.setRole(role);
		user.setStatus(UserStatus.PENDING.toString());
		user.setUserName(userDto.getUserName());
		return user;
	}
	
	public static UserDto userToUserDto(User user) {
		return UserDto.builder()
				.contactNo(user.getContactNo())
				.emailId(user.getEmailId())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.roleId(user.getRole().getId())
				.userName(user.getUserName())
				.build();
	}

}

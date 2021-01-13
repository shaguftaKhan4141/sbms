package com.conzentus.sbms.mapper;

import com.conzentus.sbms.domain.Role;
import com.conzentus.sbms.domain.User;
import com.conzentus.sbms.dto.UserDto;
import com.conzentus.sbms.util.CommonUtils;

public class UserMapper {

	public static User userDtoToUser(UserDto userDto, Role role, String status) {
		User user = new User();
		user.setContactNo(CommonUtils.getNonNullable(userDto.getContactNo()));
		user.setEmailId(CommonUtils.getNonNullable(userDto.getEmailId()));
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setPassword(userDto.getPassword());
		user.setRole(role);
		user.setStatus(status);
		user.setUserName(userDto.getUserName());
		return user;
	}
	
	public static UserDto userDtoToUser(User user) {
		return UserDto.builder()
				.contactNo(user.getContactNo())
				.emailId(user.getEmailId())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.role(user.getRole().getName())
				.userName(user.getUserName())
				.build();
	}
}

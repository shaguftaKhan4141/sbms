package com.cozentus.sbms.mapper;

import com.cozentus.sbms.domain.Role;
import com.cozentus.sbms.domain.User;
import com.cozentus.sbms.dto.UserDto;
import com.cozentus.sbms.util.CommonUtils;

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
}

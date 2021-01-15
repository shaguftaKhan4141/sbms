package com.cozentus.sbms.service;

import java.util.List;

import com.cozentus.sbms.dto.UserResponseDto;
import com.cozentus.sbms.error.InvalidDataException;
import com.cozentus.sbms.error.NotFoundException;

public interface AdminService {

	List<UserResponseDto> getAllUsersByRoleId(Long roleId) throws NotFoundException;

	List<UserResponseDto> getAllUsers(String requestStatus) throws NotFoundException, InvalidDataException;

	void approvedUserRequest(Long userId, String requestStatus) throws NotFoundException;

}
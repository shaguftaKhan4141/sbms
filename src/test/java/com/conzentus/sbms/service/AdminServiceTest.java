package com.conzentus.sbms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.conzentus.sbms.TestData;
import com.cozentus.sbms.authentication.AuthenticationFacade;
import com.cozentus.sbms.domain.User;
import com.cozentus.sbms.dto.UserResponseDto;
import com.cozentus.sbms.error.NotFoundException;
import com.cozentus.sbms.repository.BlogUserRepository;
import com.cozentus.sbms.service.AdminServiceImpl;


@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

	@Mock
	private BlogUserRepository blogUserRepository;
	
	@InjectMocks
	private AdminServiceImpl adminServiceImpl;
	
	@Mock
	AuthenticationFacade authenticationFacade;
	
	@Test
	void getAllMockUsersByRoleId() throws NotFoundException {
		List<User> users = TestData.getAllMockUsers();
		when(blogUserRepository.findByRoleId(1L)).thenReturn(users);
		List<UserResponseDto> userResponseDtos = adminServiceImpl.getAllUsersByRoleId(1L);
		assertEquals(userResponseDtos.get(0).getFirstName(), users.get(0).getFirstName());
	}
}

package com.cozentus.sbms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

	private String firstName;

	private String lastName;

	private String contactNo;

	private String emailId;

	private String userName;

	private String role;
	
	private String requestStatus;

}

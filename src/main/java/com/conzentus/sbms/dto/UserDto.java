package com.conzentus.sbms.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	@NotEmpty(message = "firstName must not be empty")
	private String firstName;

	@NotEmpty(message = "lastName must not be empty")
	private String lastName;

	@NotEmpty(message = "contactNo must not be empty")
	private String contactNo;

	@NotEmpty(message = "emailId must not be empty")
	private String emailId;

	@NotEmpty(message = "userName must not be empty")
	private String userName;

	@NotEmpty(message = "password must not be empty")
	private String password;

	@NotEmpty(message = "role must not be empty")
	private String role;
}

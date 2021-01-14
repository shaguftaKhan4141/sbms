package com.cozentus.sbms.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

	@JsonProperty("first_name")
	@NotEmpty(message = "firstName must not be empty")
	private String firstName;

	@JsonProperty("last_name")
	@NotEmpty(message = "lastName must not be empty")
	private String lastName;

	@JsonProperty("contact_no")
	@NotEmpty(message = "contactNo must not be empty")
	private String contactNo;

	@JsonProperty("email_id")
	@NotEmpty(message = "emailId must not be empty")
	private String emailId;

	@JsonProperty("user_name")
	@NotEmpty(message = "userName must not be empty")
	private String userName;

	@NotEmpty(message = "password must not be empty")
	private String password;

	@JsonProperty("role_id")
	@NotNull(message = "role_id must not be null")
	private Long roleId;
}

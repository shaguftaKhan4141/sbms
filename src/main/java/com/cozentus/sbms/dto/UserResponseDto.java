package com.cozentus.sbms.dto;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserResponseDto {

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

	@JsonProperty("role")
	@NotEmpty(message = "role must not be empty")
	private String role;
	
	@JsonProperty("request_status")
	@NotEmpty(message = "request_status must not be empty")
	private String requestStatus;
	
	@JsonProperty("blog_document_link")
	private String blogLink;
	
	private String synopsis;

}

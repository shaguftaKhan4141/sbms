package com.conzentus.sbms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.Data;

@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false)
	private String contactNo;
	
	@Column(nullable = false)
	private String emailId;
	
	@Column(nullable = false)
	private String userName;
	
	@Column(nullable = false)
	private String status;
	
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;
}

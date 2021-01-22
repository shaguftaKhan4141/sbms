package com.cozentus.sbms.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "blog_user")
@EqualsAndHashCode(exclude="topics")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

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
	
	@Column(nullable = false)
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			  name = "topic_subscriber", 
			  joinColumns = @JoinColumn(name = "user_id"), 
			  inverseJoinColumns = @JoinColumn(name = "topic_id"))
	Set<Topic> topics;
	
	
	
	@OneToOne
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "created_date", length = 19)
	private Date createdDate;

	@Column(name = "created_by", length = 50)
	private String createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name = "updated_date", length = 19)
	private Date updatedDate;

	@Column(name = "updated_by", length = 50)
	private String updatedBy;
}

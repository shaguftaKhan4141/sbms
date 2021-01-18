package com.cozentus.sbms.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
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

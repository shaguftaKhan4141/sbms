package com.cozentus.sbms.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 100)
	private String name;

	@ManyToMany(mappedBy = "topics")
	Set<User> subscriber;

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

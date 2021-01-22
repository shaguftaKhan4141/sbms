package com.cozentus.sbms.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "blog")
public class Blog {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "title", nullable = false, length = 50)
	private String title;
	
	@Column(name = "synopsis", length = 1000)
	private String synopsis;
	
	@Column(name = "author_id")
	private Long authorId;
	
	@Column(name = "co_author_id")
	private Long coAuthorId;
	
	@Column(name = "blog_link")
	private String blogLink;
	
	@Column(name = "status")
	private String status;
	
	@OneToOne
	@JoinColumn(name = "topic_id", referencedColumnName = "id")
	private Topic topic;
	
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

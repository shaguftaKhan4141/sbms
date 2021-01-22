package com.cozentus.sbms.dto;

import java.util.Date;

import com.cozentus.sbms.domain.Topic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogResponseDto {

	private Long id;

	private String title;

	private String synopsis;

	private Long authorId;

	private Long coAuthorId;

	private String blogLink;

	private String status;

	private TopicDto topic;

	private Date createdDate;

	private String createdBy;

	private Date updatedDate;

	private String updatedBy;
	
}

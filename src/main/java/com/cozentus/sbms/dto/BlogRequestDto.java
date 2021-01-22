package com.cozentus.sbms.dto;

import com.cozentus.sbms.domain.Topic;

import lombok.Data;

@Data
public class BlogRequestDto {

	private String title;

	private String synopsis;

	private Long authorId;

	private Long coAuthorId;

	private String blogLink;

	private String status;

	private Long topicId;

}

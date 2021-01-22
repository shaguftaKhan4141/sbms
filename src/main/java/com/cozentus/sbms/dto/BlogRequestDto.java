package com.cozentus.sbms.dto;

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

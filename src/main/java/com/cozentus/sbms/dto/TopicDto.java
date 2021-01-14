package com.cozentus.sbms.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopicDto {

	@JsonProperty("topic_id")
	@NotNull(message = "topic_id must not be null")
	private Long id;
	
	@JsonProperty("topic_name")
	@NotEmpty(message = "topic_name must not be empty")
	private String topicName;
}

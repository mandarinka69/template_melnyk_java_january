package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.annotation.Generated;

@Accessors(chain = true)
@Data
@Generated("com.robohorse.robopojogenerator")
public class CreateNotification{

	@JsonProperty("query")
	private String queryBodyNotification;

}
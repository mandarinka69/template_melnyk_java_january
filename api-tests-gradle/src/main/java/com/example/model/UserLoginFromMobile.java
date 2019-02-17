package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.annotation.Generated;

@Accessors(chain = true)
@Data
@Generated("com.robohorse.robopojogenerator")
public class UserLoginFromMobile{

	@JsonProperty("password")
	private String passwordAuto;

	@JsonProperty("phone_number")
	private String phoneNumberAuto;

}
package com.jb.registration.controller;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RegistrationResponse {

	@NotNull(message = "UserId must not be null")
	@NotEmpty(message = "Id must not be empty")
	private Integer id;
	private String firstName;
	private String lastName;
	private String userName;
}

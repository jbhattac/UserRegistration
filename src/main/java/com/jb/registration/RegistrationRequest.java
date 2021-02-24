package com.jb.registration;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class RegistrationRequest {

	private String firstName;
	private String lastName;
	@NotNull(message = "UserName must not be null")
	@NotEmpty(message = "UserName must not be empty")
	private String userName;
	private String password;
}

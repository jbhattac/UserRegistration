package com.jb.registration.model;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class UserDO {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password; 
		
	public UserDO(String firstName, String lastName, @NotNull String userName, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
        this.id = userName.hashCode();
	}

	private UserDO() {
		super();
	}
	
}

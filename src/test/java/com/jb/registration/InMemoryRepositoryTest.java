package com.jb.registration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class InMemoryRepositoryTest {
	
	@InjectMocks
	private InMemoryUserRepository inMemoryUserRepository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void createUser_insertValidUser() throws Exception {
		UserDO user = new UserDO("Joy","Deep","joydeep","pass");
		UserDO storedUser = inMemoryUserRepository.createUser(user);
		assertEquals(storedUser.getId(), user.getUserName().hashCode());		
	}
	
	@Test
	public void createUser_insertDuplicateUser() throws Exception {
		UserDO user = new UserDO("Joe","Tow","joetow","pass1");
		Exception exception = assertThrows(UserAlreadyExistsException.class, () -> {
			inMemoryUserRepository.createUser(user);
			inMemoryUserRepository.createUser(user);
	    });		
		String actualMessage = exception.getMessage();
		String expectedMessage = "User already exist "+user.getUserName();
		assertEquals(expectedMessage, actualMessage);
	}
	

}

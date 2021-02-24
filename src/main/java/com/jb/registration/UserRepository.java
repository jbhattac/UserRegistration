package com.jb.registration;


public interface UserRepository{
	
	UserDO createUser( UserDO user ) throws UserAlreadyExistsException;
}

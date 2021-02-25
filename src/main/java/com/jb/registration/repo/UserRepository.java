package com.jb.registration.repo;

import com.jb.registration.error.UserAlreadyExistsException;
import com.jb.registration.model.UserDO;

public interface UserRepository{
	
	UserDO createUser( UserDO user ) throws UserAlreadyExistsException;
}

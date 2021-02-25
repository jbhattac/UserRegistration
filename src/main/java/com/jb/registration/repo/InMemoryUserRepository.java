package com.jb.registration.repo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.jb.registration.error.UserAlreadyExistsException;
import com.jb.registration.model.UserDO;

@Repository
public class InMemoryUserRepository implements UserRepository {

	private  Map<Integer, UserDO> map = new ConcurrentHashMap<>();

	@Override
	public UserDO createUser(UserDO user) throws UserAlreadyExistsException {
		if (null != map.putIfAbsent(user.getId(), user))
			throw new UserAlreadyExistsException("User already exist "+user.getUserName());
		return user;
	}

}

package com.jb.registration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserRepository implements UserRepository {

	private  Map<Integer, UserDO> map = new ConcurrentHashMap<>();

	@Override
	public UserDO createUser(UserDO user) throws UserAlreadyExistsException {
		if (null != map.putIfAbsent(user.getId(), user))
			throw new UserAlreadyExistsException();
		return user;
	}

}

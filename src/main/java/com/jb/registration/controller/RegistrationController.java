package com.jb.registration.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jb.registration.mapper.UserMapper;
import com.jb.registration.model.UserDO;
import com.jb.registration.repo.UserRepository;

@RestController()
@RequestMapping("/userservice/register")
public class RegistrationController {
	
	@Autowired
	private UserRepository UserRepository;
	
	@PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RegistrationResponse createDriver(@Valid @RequestBody RegistrationRequest request)
    {
        UserDO userDO = UserMapper.makeUserDO(request);
        return UserMapper.makeUserResponse(UserRepository.createUser(userDO));
    }

}

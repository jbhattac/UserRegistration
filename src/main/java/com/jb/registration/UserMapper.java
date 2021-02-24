package com.jb.registration;

public class UserMapper {

	public static UserDO makeUserDO(RegistrationRequest request){
        return new UserDO(request.getFirstName(),request.getLastName(),request.getUserName(), request.getPassword());
    }

	public static RegistrationResponse makeUserResponse(UserDO user) {
		return RegistrationResponse.builder().firstName(user.getFirstName())
									.lastName(user.getLastName())
									.id(user.getId())
									.userName(user.getUserName()).build();
	}

}

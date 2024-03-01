package com.smit.springbootblogwebapp.service;



import com.smit.springbootblogwebapp.dto.RegistrationDto;
import com.smit.springbootblogwebapp.entity.User;

public interface UserService {
	
	void saveUser(RegistrationDto registrationDto);

	User findByEmail(String email);
	
}

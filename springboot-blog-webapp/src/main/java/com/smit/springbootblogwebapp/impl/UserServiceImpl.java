package com.smit.springbootblogwebapp.service.impl;



import java.util.Arrays;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smit.springbootblogwebapp.dto.RegistrationDto;
import com.smit.springbootblogwebapp.entity.Role;
import com.smit.springbootblogwebapp.entity.User;
import com.smit.springbootblogwebapp.repository.RoleRepository;
import com.smit.springbootblogwebapp.repository.UserRepository;
import com.smit.springbootblogwebapp.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	
	// dependency
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}



	@Override
	public void saveUser(RegistrationDto registrationDto) {
		
		User user = new User();
		
		user.setName(registrationDto.getFirstName() + registrationDto.getLastName());
		user.setEmail(registrationDto.getEmail());
		// use spring security to encrypt the password
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		
		Role role = roleRepository.findByName("ROlE_GUEST");
		user.setRoles(Arrays.asList(role));
		userRepository.save(user);
	}



	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	
}

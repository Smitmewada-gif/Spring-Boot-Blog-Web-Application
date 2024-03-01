package com.smit.springbootblogwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smit.springbootblogwebapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
}

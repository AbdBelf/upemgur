package com.service.upemgur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.service.upemgur.model.User;

public interface UserRepository  extends JpaRepository<User, String> {

	
	User findByUsername(@Param(value = "username") String username);

}

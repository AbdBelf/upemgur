package com.service.upemgur.service;

import org.springframework.security.access.prepost.PreAuthorize;

import com.service.upemgur.model.User;

public interface IUserService {
	
	public User getUser();
	
	public User findUser(String username);
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void createUser(User user);

	
}

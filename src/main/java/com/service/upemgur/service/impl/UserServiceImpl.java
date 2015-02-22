package com.service.upemgur.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.resthub.common.service.CrudServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.service.upemgur.model.User;
import com.service.upemgur.model.UserRole;
import com.service.upemgur.repository.UserRepository;
import com.service.upemgur.repository.UserRoleRepository;
import com.service.upemgur.service.IUserService;
import com.service.upemgur.utils.Utils;

@Named("userService")
public class UserServiceImpl extends
		CrudServiceImpl<User, String, UserRepository> implements IUserService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Inject
	@Named("userRepository")
	private UserRepository userRepository;

	@Inject
	@Named("userRoleRepository")
	private UserRoleRepository userRoleRepository;

	@Override
	public User getUser() {
		// TODO Auto-generated method stub

		final User user = userRepository.findByUsername(Utils.getUsername());
		return user;
	}

	@Override
	public User findUser(String username) {

		return userRepository.findByUsername(username);

	}

	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub
		
		UserRole role = new UserRole();
		role.setRole(UserRole.ROLE_USER);
		role.setUser(user);
		userRepository.saveAndFlush(user);
		userRoleRepository.saveAndFlush(role);
	}

}

package com.sanyam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.sanyam.dao.UserDao;
import com.sanyam.entity.User;
import com.sanyam.entity.UserDetails;
import com.sanyam.service.UserService;

public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	public String register(UserDetails user) {
		return userDao.register(user);
	}

	public UserDetails validateUser(User userCheck) {
		return userDao.validateUser(userCheck);
	}

}

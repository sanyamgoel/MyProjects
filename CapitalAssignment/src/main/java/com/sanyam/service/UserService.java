package com.sanyam.service;

import com.sanyam.entity.User;
import com.sanyam.entity.UserDetails;

public interface UserService {

	String register(UserDetails user);
	UserDetails validateUser(User userCheck);
}

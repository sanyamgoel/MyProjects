package com.sanyam.dao;

import com.sanyam.entity.User;
import com.sanyam.entity.UserDetails;

public interface UserDao {

	String register(UserDetails user);
	UserDetails validateUser(User userCheck);

}
package com.xiu.fastWeb.service;

import java.util.List;

import com.xiu.fastWeb.model.User;


public interface UserService {

	
	public User saveUser();
	
	public List<User> getUserList(String id);
	
}

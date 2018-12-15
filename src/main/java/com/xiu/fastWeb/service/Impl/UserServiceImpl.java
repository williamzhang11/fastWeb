package com.xiu.fastWeb.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiu.fastWeb.dao.UserDao;
import com.xiu.fastWeb.model.User;
import com.xiu.fastWeb.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;

	@Override
	public User saveUser() {
		
		User person = new User();
		
		person.setName("zhangsan");
		person.setAge(28);
		
		User person2 = userDao.save(person);
		System.err.println(person2.toString());
		return person2;
		
	}

	@Override
	public List<User> getUserList(String id) {
		
		return userDao.getUserList( Long.parseLong(id));
	}

	@Override
	public Object get(Long id) {

		return userDao.get(id);
	}
}

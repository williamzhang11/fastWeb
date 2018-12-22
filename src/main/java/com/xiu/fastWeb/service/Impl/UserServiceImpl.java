package com.xiu.fastWeb.service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xiu.fastWeb.dao.UserDao;
import com.xiu.fastWeb.model.User;
import com.xiu.fastWeb.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	UserDao userDao;

	@Autowired
	RedisTemplate redisTemplate;

	@Autowired
	StringRedisTemplate stringRedisTemplate;

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

		if(redisTemplate.opsForValue().get(id.toString())==null){
			Object user = userDao.get(id);
			redisTemplate.opsForValue().set(id.toString(),user);
			log.info("===========set==============");
			return user;
		}else{

			Object ob = redisTemplate.opsForValue().get(id.toString());
			log.info("===========get=============="+ob);
			return ob;
		}
	}
}

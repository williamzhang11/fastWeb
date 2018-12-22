package com.xiu.fastWeb.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xiu.fastWeb.dao.AddressDao;
import com.xiu.fastWeb.model.Address;
import com.xiu.fastWeb.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	AddressDao addressDao;

	@Override
	public Address saveAddress(Address address) {

		// TODO Auto-generated method stub
		return addressDao.saveAddress(address);
	}


}

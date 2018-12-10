package com.xiu.fastWeb.dao.Impl;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;

import com.xiu.fastWeb.dao.AddressDao;
import com.xiu.fastWeb.model.Address;

@Repository
public class AddressDaoImpl implements AddressDao{

	@PersistenceContext(unitName="manEntityManagerFactory")
	private EntityManager entityManager;

	@Override
	public Address saveAddress(Address address) {
		
		entityManager.persist(address);
		
		return  null;
	}
	
	
}

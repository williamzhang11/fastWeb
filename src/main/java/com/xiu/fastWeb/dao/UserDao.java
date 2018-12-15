package com.xiu.fastWeb.dao;

import java.util.List;

import com.xiu.fastWeb.repository.MyRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xiu.fastWeb.model.User;



@Repository
public interface UserDao extends MyRepository<User,Long>{


	@Query("from User where id = :id")
	public List<User> getUserList(@Param("id") Long id);
}

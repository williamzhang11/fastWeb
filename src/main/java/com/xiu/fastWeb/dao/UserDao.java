package com.xiu.fastWeb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xiu.fastWeb.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long>{

	@Query("from User where id = :id")
	public List<User> getUserList(@Param("id") Long id);
}

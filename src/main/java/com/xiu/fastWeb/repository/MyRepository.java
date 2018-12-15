package com.xiu.fastWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface MyRepository<T,ID extends Serializable> extends JpaRepository<T,ID> {

    public Object get(ID id);
}

package com.xiu.fastWeb.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
/*
@Configuration*/
public class DataSourceConfig {
	
	/*@Bean(name="myDataSource")
	@Primary
	@ConfigurationProperties(prefix="my.datasource")*/
	public DataSource buildDataSource() {
		
		return DataSourceBuilder.create().build();
	}

}

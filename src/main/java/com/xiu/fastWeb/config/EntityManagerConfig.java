package com.xiu.fastWeb.config;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		 entityManagerFactoryRef = "entityManageFactoryPrimary",
	        transactionManagerRef = "transactionManagerPrimary",
	        basePackages = {"com.xiu.fastWeb"})
*/public class EntityManagerConfig {

/*	@Autowired
	@Qualifier("myDataSource")
	private DataSource myDataSource;
	
	
	 
    @Bean(name = "entityManagerPrimary")
    @Primary
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactory(builder).getObject().createEntityManager();
    }
	 
	
	 @Bean(name = "entityManageFactoryPrimary")
	 @Primary
	 public LocalContainerEntityManagerFactoryBean entityManagerFactory (EntityManagerFactoryBuilder builder) {
		 
		 LocalContainerEntityManagerFactoryBean entityManagerFactory =
				 builder.dataSource(myDataSource).
				 packages("com.xiu.fastWeb").build();
		 
		 Properties jpaProperties = new Properties();
		 jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	        jpaProperties.put("hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
	        jpaProperties.put("hibernate.connection.charSet", "utf-8");
	        jpaProperties.put("hibernate.show_sql", "true");
	        jpaProperties.put("hibernate.ddl-auto", "update");
	        entityManagerFactory.setJpaProperties(jpaProperties);
	        
	        return entityManagerFactory;
	 }
	 
	 
    @Bean(name = "transactionManagerPrimary")
    @Primary
    PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactory(builder).getObject());
    }
	 */
	 
	 
	 
	 
	
}

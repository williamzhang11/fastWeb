package com.xiu.fastWeb.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
//https://blog.csdn.net/mickjoust/article/details/80352795
//https://blog.csdn.net/qq_36288536/article/details/78696900

@Configuration
@EnableJpaRepositories( //0
        basePackages = "com.xiu.fastWeb",
        entityManagerFactoryRef = "manEntityManagerFactory",
        transactionManagerRef = "manTransactionManager"
)
public class ManDBConfig {

    @Autowired
    private Environment env; //1

    @Bean
    @ConfigurationProperties(prefix="my.datasource")
    public DataSourceProperties manDataSourceProperties() { //2
        return new DataSourceProperties();
    }


    @Bean
    public DataSource manDataSource() { //3
        DataSourceProperties manDataSourceProperties = manDataSourceProperties();
        return DataSourceBuilder.create()
                .driverClassName(manDataSourceProperties.getDriverClassName())
                .url(manDataSourceProperties.getUrl())
                .username(manDataSourceProperties.getUsername())
                .password(manDataSourceProperties.getPassword())
                .build();
    }

    @Bean
    public PlatformTransactionManager manTransactionManager() { //4
        EntityManagerFactory factory = manEntityManagerFactory().getObject();
        return new JpaTransactionManager(factory);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean manEntityManagerFactory() {//5
        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(manDataSource());
        factory.setPackagesToScan("com.xiu.fastWeb");
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
                jpaProperties.put("hibernate.show-sql", env.getProperty("hibernate.show-sql"));
                jpaProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
                
               
        factory.setJpaProperties(jpaProperties);
        return factory;
    }

    @Bean
    public DataSourceInitializer manDataSourceInitializer() {//6
        DataSourceInitializer dsInitializer = new DataSourceInitializer();
        dsInitializer.setDataSource(manDataSource());
        ResourceDatabasePopulator dbPopulator = new ResourceDatabasePopulator();
       // dbPopulator.addScript(new ClassPathResource("security-data.sql"));
        dsInitializer.setDatabasePopulator(dbPopulator);
        dsInitializer.setEnabled(env.getProperty("my.datasource.initialize",
                Boolean.class, false) );
        return dsInitializer;
    }

}

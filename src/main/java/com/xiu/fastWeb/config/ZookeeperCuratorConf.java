package com.xiu.fastWeb.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
//基于curator的zookeeper配置
//@Configuration
//@PropertySource("classpath:zookeeper.properties")
public class ZookeeperCuratorConf {

	@Value("${spring.zookeeper.address}")
	private String address;
	
	@Bean
    public CuratorFramework getCuratorFramework(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(address,retryPolicy);
        client.start();
        return client;
    }
	
}

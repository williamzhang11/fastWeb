package com.xiu.fastWeb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import io.lettuce.core.event.connection.ConnectedEvent;

import java.io.IOException;
import java.nio.file.Watchable;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//原生zookeeper配置
//@Configuration
//@PropertySource("classpath:zookeeper.properties")
public class ZookeeperConfig {
	
	private static final Logger log = LoggerFactory.getLogger(ZookeeperConfig.class);

	private static CountDownLatch connectedLock = new CountDownLatch(1);

	private ZooKeeper zooKeeper;
	
	@Value("${spring.zookeeper.address}")
	private String address;
	
	@Value("${spring.zookeeper.timeout}")
	private String timeout;
	
	@Bean("myZookeeperConfig")
	public void myZookeeperConfig() {
		try {
			this.zooKeeper = new ZooKeeper(address, Integer.parseInt(timeout), new ZookeeperWatcher());
			log.info(String.valueOf(zooKeeper.getState()));
			
			try {
				connectedLock.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.info("Zookeeper session established...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private class ZookeeperWatcher implements Watcher{

		@Override
		public void process(WatchedEvent event) {
			if(KeeperState.SyncConnected == event.getState()) {
				connectedLock.countDown();
			}
		}
		
		
	}
	
	
}

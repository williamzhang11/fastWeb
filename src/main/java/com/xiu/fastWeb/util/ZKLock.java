package com.xiu.fastWeb.util;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component("zklock")
@PropertySource("classpath:zookeeper.properties")
public class ZKLock implements Lock{

	private CuratorFramework zkClient;
	
	@Value("${spring.zookeeper.localPath}")
	private String lockPath;
	
	private String currentPath;
	
	private String beforePath;
	
	
	@Override
	public void lock() {
		
		if(!tryLock()) {
			waitForLock();
			lock();
		}
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean tryLock() {
		
		try {
			if(zkClient.checkExists().forPath(lockPath)==null) {
				System.out.println("初始化根节点==========>" + lockPath);
				zkClient.create().creatingParentsIfNeeded().forPath(lockPath);
			}
			System.out.println("当前线程"+Thread.currentThread().getName()+"初始化根节点"+lockPath);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		if(currentPath == null) {
			try {
				
				currentPath = this.zkClient.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath(lockPath+"/");
				
			}catch (Exception e) {
				// TODO: handle exception
				return false;
			}
		}
		
		try {
			
			List<String> childrens = this.zkClient.getChildren().forPath(lockPath);
			Collections.sort(childrens);
			if(currentPath.equals(lockPath+"/"+ childrens.get(0))) {
				System.out.println("当前线程获得锁" + currentPath);
                return true;
			}else {
				int curIndex = childrens.indexOf(currentPath.substring(lockPath.length() +1));
				beforePath = lockPath + "/" +childrens.get(curIndex -1);
			}
		}catch (Exception e) {
			return false;
		}
		
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		
			
		return false;
	}
	private void waitForLock() {
		
		CountDownLatch cdLatch = new CountDownLatch(1);
		NodeCache nodeCache = new NodeCache(zkClient, beforePath);
		try {
			nodeCache.start(true);
			nodeCache.getListenable().addListener(new NodeCacheListener() {
				
				@Override
				public void nodeChanged() throws Exception {
					cdLatch.countDown();
                    System.out.println(beforePath + "节点监听事件触发，重新获得节点内容为：" + new String(nodeCache.getCurrentData().getData()));
					
				}
			});
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			
			if(zkClient.checkExists().forPath(beforePath)==null) {
				cdLatch.await();
				
			}
		}catch (Exception e) {
			
			
		}finally {
			try {
				nodeCache.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void unlock() {
		try {
            zkClient.delete().guaranteed().deletingChildrenIfNeeded().forPath(currentPath);
        } catch (Exception e) {
        }
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.xiu.fastWeb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

//基于redis的分布式锁
public class MethodLockRedisUtil {
	
	 private static Logger log = LoggerFactory.getLogger(MethodLockUtil.class);
	 
	 private static volatile RedisTemplate<String, String> redisTemplates = null;
	
	
	public static boolean lock(String key, String value) {
		
		if(redisTemplates==null) {
			redisTemplates = (RedisTemplate<String, String>) SpringContextUtil.getBean("redisTemplate");
        }
		
		if(redisTemplates.opsForValue().setIfAbsent(key, value)) {
			return true;
		}
		String currentValue = redisTemplates.opsForValue().get(key);
		if(!StringUtil.isNull(currentValue)&&Long.parseLong(currentValue)< System.currentTimeMillis()) {
			String oldValue = redisTemplates.opsForValue().getAndSet(key, value);
			if(!StringUtil.isNull(oldValue)&&oldValue.equals(currentValue)) {
				return true;
			}
		}
		return false;
	}
	
	public static void unlock(String key, String value) {
		
		if(redisTemplates==null) {
			redisTemplates = (RedisTemplate<String, String>) SpringContextUtil.getBean("redisTemplates");
        }
		
		try {
			String currentValue = redisTemplates.opsForValue().get(key);
			if(!StringUtil.isNull(currentValue)&&currentValue.equals(value)) {
				redisTemplates.opsForValue().getOperations().delete(key);
			}
		}catch (Exception e) {
			log.error("解锁异常");
		}
	}
	
	
}

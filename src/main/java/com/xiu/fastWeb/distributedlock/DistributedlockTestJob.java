package com.xiu.fastWeb.distributedlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.xiu.fastWeb.util.MethodLockRedisUtil;
//基于redis的分布式锁
@Component
public class DistributedlockTestJob {

	Logger log = LoggerFactory.getLogger(getClass());
	private static Long TIME_OUT = 60000L;
	private static String DOING_JOB_KEY = "doingjobkey";
	private static boolean running = false;
	private static Object locked = new Object();
     
    //@Scheduled(cron = "0/1 * * * * ?")
    public void test(){
        if(running) {
            log.info("=================Job  is Running===================");
            return;
        }

		synchronized (locked) {
			running = true;
			
			long time = System.currentTimeMillis()+TIME_OUT;
			if(!MethodLockRedisUtil.lock(DOING_JOB_KEY, String.valueOf(time))) {
				log.info("=================connot get lock===================");
				running = false;
				return;
			}
			try {
				
				log.info("...........exec.........");
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				running = false;
				MethodLockRedisUtil.unlock(DOING_JOB_KEY, String.valueOf(time));
			}

			log.info(" -----------------Job end  -------------------------");
		}


    }
	
}

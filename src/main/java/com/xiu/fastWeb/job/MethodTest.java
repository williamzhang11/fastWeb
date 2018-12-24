package com.xiu.fastWeb.job;


import com.xiu.fastWeb.util.MethodLockUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class MethodTest {
    Logger log = LoggerFactory.getLogger(getClass());


    private long expiredTime = 48 * 60 * 60 * 1000L;

    //@Scheduled(cron = "0/1 * * * * ?")
    public void test(){
        if(MethodLockStatus.running) {
            log.info("=================Job  is Running===================");
            return;
        }

        long time = System.currentTimeMillis() + expiredTime;
        try {
            if (!MethodLockUtil.tryGetLock(time)) {
                log.info("---------------------------定时器未获取到锁-------------------------------");
                return;
            }
        } catch (Exception e) {
            log.info("---------------------------定时器 尝试获取锁异常-------------------------------");
            e.printStackTrace();
            return;
        }

        log.info("----------------------获取锁----------------------------------------------");
        try {
            log.info("=================Job  is Start===================");
            MethodLockStatus.running =true;
            Thread.sleep(10000);
        }catch (Exception e) {
            log.info("=================Job  Error===================");
            e.printStackTrace();
        }finally {

            try {
                MethodLockUtil.unLock(time);
            } catch (Exception e) {
                log.error("--------------------Unlock Error------------------------------------");
                e.printStackTrace();
            }

            MethodLockStatus.running = false;
            log.info("=================Job  is End===================");
        }


    }

}

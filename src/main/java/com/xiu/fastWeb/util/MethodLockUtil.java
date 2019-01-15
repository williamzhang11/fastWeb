package com.xiu.fastWeb.util;

import com.xiu.fastWeb.exception.MethodLockException;
import com.xiu.fastWeb.service.MethodLockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.UnexpectedRollbackException;

import java.net.InetAddress;
import java.net.UnknownHostException;
//基于数据库分布式锁
public class MethodLockUtil {

    private static Logger log = LoggerFactory.getLogger(MethodLockUtil.class);

    private static volatile MethodLockService methodLockService = null;


    /**
     * 尝试获取锁
     * @param下一次的超时时间
     * @return 是否获取锁
     */
    public static Boolean tryGetLock(Long time) {

        if(methodLockService==null) {
            methodLockService = (MethodLockService) SpringContextUtil.getBean("methodLockService");
        }

        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        try {
            if(methodName.trim().length() >0 ) {
                if(methodLockService.saveMethodLock(methodName, time) != null) {
                    return true;
                }
            }
        }catch (Exception e) {
            log.error("-------------------------------已锁定----------------------------");
        }

        Long currentValue =methodLockService.getUpdateTimeByMethodName(methodName);
        log.info("currentValue="+currentValue);
        log.info("currentTimeMillis="+System.currentTimeMillis());
        if(currentValue != null &&currentValue < System.currentTimeMillis()) {
            log.error("-------------------------------锁超时----------------------------");
            Long oldTime = methodLockService.getAndSetByMethodName(methodName, time);
            if(oldTime != null&&oldTime.equals(currentValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解锁
     * @param time 下一次超时时间
     * @throws MethodLockException
     */
    public static void unLock(Long time) throws MethodLockException {
        String methodName = "";
        try {

            methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
            Long currentValue =methodLockService.getUpdateTimeByMethodName(methodName);


            if(currentValue != null && currentValue.equals(time)) {
                methodLockService.deleteMethodLockByMethodName(methodName);
            }
        }catch (Exception e) {

            e.printStackTrace();
            methodLockService.updateMethodLockByMethodName(methodName, e.getMessage());
            throw new MethodLockException("解锁异常");
        }

        log.info("-------------------------------解锁----------------------------");

    }

    /**
     *
     * @return 本机内网ip
     */
    public static String getHostIp() {
        String hostIp="";
        try {

            hostIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            hostIp = "127.0.0.1";
        }
        return hostIp;
    }

}

package com.xiu.fastWeb.service.Impl;

import com.xiu.fastWeb.dao.MethodLockDao;
import com.xiu.fastWeb.model.MethodLock;
import com.xiu.fastWeb.service.MethodLockService;
import com.xiu.fastWeb.util.MethodLockUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("methodLockService")
public class MethodLockServiceImpl  implements MethodLockService {
    Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private MethodLockDao methodLockDao;



    @Override
    public MethodLock saveMethodLock(String methodName ,Long time) {

        try {

            if(methodName.trim().length() > 0 ) {

                MethodLock methodLock = new MethodLock();
                methodLock.setMethodName(methodName);
                methodLock.setUpdateTime(time);
                methodLock.setDescription(methodName + " operator");
                methodLock.setHostIp(MethodLockUtil.getHostIp());
                return methodLockDao.save(methodLock);
            }

        }catch (Exception e) {
            log.error("获取锁失败");
        }

        return null;
    }

    @Override
    public Long getUpdateTimeByMethodName(String methodName) {
        // TODO Auto-generated method stub
        return methodLockDao.getUpdateTimeByMethodName(methodName);
    }

    @Override
    public Long getAndSetByMethodName(String methodName ,Long time) {

        Long updateTime = getUpdateTimeByMethodName(methodName);
        if(updateTime == null) {
            MethodLock methodLock = new MethodLock();
            methodLock.setMethodName(methodName);
            methodLock.setUpdateTime(time);
            methodLock.setDescription(methodName + " operator");
            methodLock.setHostIp(MethodLockUtil.getHostIp());
            methodLockDao.save(methodLock);
            return null;
        }
        updateTimeByMethodName(methodName,time);
        return updateTime;
    }

    @Override
    public void updateTimeByMethodName(String methodName,Long time) {

        methodLockDao.updateTimeByMethodName(methodName, time);
    }

    @Override
    public void deleteMethodLockByMethodName(String methodName) {

        methodLockDao.deleteMethodLockByMethodName(methodName);
    }

    public MethodLockDao getMethodLockDao() {
        return methodLockDao;
    }

    public void setMethodLockDao(MethodLockDao methodLockDao) {
        this.methodLockDao = methodLockDao;
    }

    @Override
    public void updateMethodLockByMethodName(String methodName, String description) {
        methodLockDao.updateMethodLockByMethodName(methodName, description);
    }



    @Override
    public MethodLock getMethodLockById(Long id) {
        return methodLockDao.getMethodLockById(id);
    }

    @Override
    public void deleteAllMethodLockById(List<MethodLock> mlIdList) {
        methodLockDao.deleteAllMethodLockById(mlIdList);
    }

    @Override
    public void deleteMethodLockById(String id) {
        // TODO Auto-generated method stub
        methodLockDao.deleteMethodLockById(id);
    }




}

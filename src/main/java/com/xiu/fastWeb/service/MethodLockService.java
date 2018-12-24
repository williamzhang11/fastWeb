package com.xiu.fastWeb.service;

import com.xiu.fastWeb.model.MethodLock;

import java.util.List;

public interface MethodLockService  {

    public MethodLock saveMethodLock(String methodName ,Long time);

    public Long getUpdateTimeByMethodName(String methodName);

    public Long getAndSetByMethodName(String methodName ,Long time);

    public void updateTimeByMethodName(String methodName,Long time);

    public MethodLock getMethodLockById(Long id);

    public void deleteMethodLockByMethodName(String methodName);

    public void deleteAllMethodLockById(List<MethodLock> mlIdList);

    void updateMethodLockByMethodName(String methodName, String description);

    public void deleteMethodLockById(String id);


}

package com.xiu.fastWeb.dao;

import com.xiu.fastWeb.model.MethodLock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface  MethodLockDao extends JpaRepository<MethodLock,Long>{


    @Query("select updateTime from MethodLock where methodName = :methodName")
    public Long getUpdateTimeByMethodName(@Param("methodName") String methodName);

    @Modifying()
    @Query("update MethodLock set updateTime = :updateTime where methodName = :methodName")
    public void updateTimeByMethodName(@Param("methodName")String methodName,@Param("updateTime")Long updateTime);
    @Modifying()
    @Query("delete from MethodLock where methodName = :methodName")
    public void deleteMethodLockByMethodName(@Param("methodName")String methodName);
    @Modifying()
    @Query("update MethodLock set description = :description where methodName = :methodName")
    public void updateMethodLockByMethodName(@Param("methodName")String methodName, @Param("description")String description);

    @Query("from MethodLock where id = :id")
    public MethodLock getMethodLockById(@Param("id")Long id);

    public void deleteAllMethodLockById(List<MethodLock> mlIdList);

    public void deleteMethodLockById(String id);
}

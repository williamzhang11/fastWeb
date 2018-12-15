package com.xiu.fastWeb.threadpool.springasyncpool;

import com.xiu.fastWeb.threadpool.ThreadPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
//第一种修改框架中默认线程池参数
@Configuration
public class AsyncThreadPool {

    @Autowired
    private ThreadPoolConfig asyncThreadPoolConfig;

    @Bean
    public Executor myAsyncThreadPool(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(asyncThreadPoolConfig.getCorePoolSize());
        executor.setMaxPoolSize(asyncThreadPoolConfig.getMaxPoolSize());
        executor.setKeepAliveSeconds(asyncThreadPoolConfig.getKeepAliveSeconds());
        executor.setQueueCapacity(asyncThreadPoolConfig.getQueueCapacity());
        executor.setThreadNamePrefix("MyAsyncExecutor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }



}

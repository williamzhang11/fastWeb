package com.xiu.fastWeb.threadpool.customthreadpool;

import com.xiu.fastWeb.threadpool.ThreadPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;
//第二种方法：自定义线程池
@Component
public class ThreadPool {

    protected final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    ThreadPoolConfig threadPoolConfig;

    @Bean("customThreadPool")
    public ExecutorService createThreadPool(){

        return new ThreadPoolExecutor(
                threadPoolConfig.getCorePoolSize(),
                threadPoolConfig.getMaxPoolSize(),
                threadPoolConfig.getKeepAliveSeconds(),
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(threadPoolConfig.getMaxPoolSize()),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        log.error("setRejectedExecutionHandler Executor[AtvCnt=" + executor.getActiveCount() + ",Q.Size="
                                + executor.getQueue().size() + "]");
                        r.run();
                    }
                }

        );


    }
}

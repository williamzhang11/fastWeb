package com.xiu.fastWeb.threadpool.springasyncpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTask {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Async("myAsyncThreadPool")
    public void echoTest(){
        logger.info("------2-----");
    }

}

package com.xiu.fastWeb.threadpool.customthreadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CustomRunnble implements Runnable{

    private Logger log = LoggerFactory.getLogger(getClass());

    public CustomRunnble() {

    }

    @Override
    public void run() {

        log.info("====CustomRunnble running=======");
    }
}

package com.xiu.fastWeb;

import com.xiu.fastWeb.threadpool.customthreadpool.CustomRunnble;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomThreadPoolTest {
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired()
    @Qualifier("customThreadPool")
    ExecutorService executorService;
    @Test
    public void customThreadTest(){

        int i =10;
        while(i>0) {
            executorService.submit(new CustomRunnble());
            i--;
        }


    }

}

package com.xiu.fastWeb;

import com.xiu.fastWeb.threadpool.springasyncpool.AsyncTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	//@Autowired
	//PersonService personService;
	@Autowired
	AsyncTask asyncTask;
	@Test
	public void contextLoads() {
		
		//personService.savePerson();
	}

	@Test
   public void AsyncThreadToolTest(){

		logger.info("----1------");
		int i = 0;
		while(true) {
			asyncTask.echoTest();
			i++;
			if(i == 10){
				break;
			}
		}
		logger.info("----3------");

		try {

			Thread.sleep(5000L);
		}catch (InterruptedException e){

		}
   }





}

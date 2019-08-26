package com.xiu.fastWeb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiu.fastWeb.model.DeadTest;
import com.xiu.fastWeb.service.DeadTestService;

@RestController
@RequestMapping(value="/dead")
public class DeadTestController {
	
	@Autowired
	DeadTestService deadTestService;
	
	Executor executor = Executors.newCachedThreadPool();
	
	@GetMapping(value="add")
	public String add() {
		List<DeadTest> deadTests = new ArrayList<>();
		Integer i=10000;
		while(i>0) {
			DeadTest deadTest = new DeadTest();
			deadTest.setContentId(i.toString());
			deadTest.setPublishStatus(0);
			deadTests.add(deadTest);
			i--;
		}
		
		deadTestService.saveDeadTestList(deadTests);
		return "ok";
	}
	
	
	@GetMapping(value="publish")	
	public void publish() {
		
		while(true) {
			DeadTest deadTest = deadTestService.getDeadTest();
			if(deadTest!=null) {
				deadTestService.updateDeadTest(2,deadTest);
				DeadThread deadThread = new DeadThread(deadTestService,deadTest);
				
				executor.execute(deadThread);
			}else {
				
				break;
			}
		}
	}
	
	
	
	
	

}

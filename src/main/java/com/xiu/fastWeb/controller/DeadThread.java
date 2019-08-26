package com.xiu.fastWeb.controller;

import com.xiu.fastWeb.model.DeadTest;
import com.xiu.fastWeb.service.DeadTestService;

public class DeadThread implements Runnable{
	
	DeadTestService deadTestService;
	DeadTest deadTest;
	
	public DeadThread(DeadTestService deadTestService,DeadTest deadTest) {
		
		this.deadTestService = deadTestService;
		this.deadTest = deadTest;
	}
	@Override
	public void run() {
			
			System.out.println("===start====");
			
			if(deadTest !=null) {
				deadTestService.updateDeadTest(1,deadTest);
				System.out.println("===publish success====: "+deadTest.getContentId());
				
			}
			else {
				System.out.println("===end====");	
			}
		
	}

	
	
}

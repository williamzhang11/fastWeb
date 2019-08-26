package com.xiu.fastWeb.service;

import java.util.List;

import com.xiu.fastWeb.model.DeadTest;

public interface DeadTestService {

	public void saveDeadTestList(List<DeadTest> deadTests);
	
	public DeadTest getDeadTest();
	
	public void updateDeadTest(Integer publishStatus,DeadTest deadTest);
}

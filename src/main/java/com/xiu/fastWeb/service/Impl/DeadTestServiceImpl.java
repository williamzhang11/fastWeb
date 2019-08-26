package com.xiu.fastWeb.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiu.fastWeb.dao.DeadTestDao;
import com.xiu.fastWeb.model.DeadTest;
import com.xiu.fastWeb.service.DeadTestService;

@Service
@Transactional
public class DeadTestServiceImpl implements DeadTestService{

	@Autowired
	DeadTestDao deadTestDao;
	
	@Override
	public void saveDeadTestList(List<DeadTest> deadTests) {
		
		deadTestDao.saveAll(deadTests);
		
	}
	@Override
	public DeadTest getDeadTest() {
		List<DeadTest> deadTests = deadTestDao.getDeadTestsss();
		if(deadTests != null && deadTests.size()>0) {
			return deadTests.get(0);
		}
		
		return null;
	}
	@Override
	public void updateDeadTest(Integer publishStatus,DeadTest deadTest) {
		deadTestDao.updateDeadTest(publishStatus,deadTest.getContentId());
	}

	
}

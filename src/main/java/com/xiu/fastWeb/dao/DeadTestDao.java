package com.xiu.fastWeb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xiu.fastWeb.model.DeadTest;

@Repository
public interface DeadTestDao extends JpaRepository<DeadTest, Long>{

	@Query("select d from DeadTest d where d.publishStatus=0")
	public List<DeadTest> getDeadTestsss();
	
    @Modifying()
	@Query("update DeadTest set publishStatus = :publishStatus where contentId = :contentId")
	public void updateDeadTest(@Param("publishStatus")Integer publishStatus,@Param("contentId")String contentId);
	
}

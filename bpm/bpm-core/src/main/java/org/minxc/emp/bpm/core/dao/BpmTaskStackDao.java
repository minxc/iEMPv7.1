package org.minxc.emp.bpm.core.dao;


import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.bpm.core.model.BpmTaskStack;
import org.minxc.emp.common.db.dao.CommonDao;

@Mapper
public interface BpmTaskStackDao extends CommonDao<String, BpmTaskStack> {
	public BpmTaskStack getByTaskId(String taskId);

	public void removeByInstanceId(String instanceId);
}
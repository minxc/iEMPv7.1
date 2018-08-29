package org.minxc.emp.bpm.core.dao;

import org.minxc.emp.common.db.dao.CommonDao;

import org.minxc.emp.bpm.core.model.BpmTaskStack;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BpmTaskStackDao extends CommonDao<String, BpmTaskStack> {
	public BpmTaskStack getByTaskId(String taskId);

	public void removeByInstanceId(String instanceId);
}
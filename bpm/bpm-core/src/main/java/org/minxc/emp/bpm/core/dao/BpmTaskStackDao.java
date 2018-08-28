package org.minxc.emp.bpm.core.dao;

import com.dstz.base.dao.BaseDao;

import org.minxc.emp.bpm.core.model.BpmTaskStack;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface BpmTaskStackDao extends BaseDao<String, BpmTaskStack> {
	public BpmTaskStack getByTaskId(String taskId);

	public void removeByInstanceId(String instanceId);
}
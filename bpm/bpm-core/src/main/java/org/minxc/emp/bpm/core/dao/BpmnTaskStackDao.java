package org.minxc.emp.bpm.core.dao;


import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.bpm.core.model.BpmnTaskStack;
import org.minxc.emp.common.db.dao.CommonDao;

@Mapper
public interface BpmnTaskStackDao extends CommonDao<String, BpmnTaskStack> {
	public BpmnTaskStack getByTaskId(String taskId);

	public void removeByInstanceId(String instanceId);
}
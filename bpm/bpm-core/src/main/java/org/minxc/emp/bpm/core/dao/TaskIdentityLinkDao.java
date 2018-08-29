package org.minxc.emp.bpm.core.dao;

import org.minxc.emp.common.db.dao.CommonDao;

import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Param;
import org.minxc.emp.bpm.core.model.TaskIdentityLink;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskIdentityLinkDao extends CommonDao<String, TaskIdentityLink> {
	public void removeByInstanceId(String var1);

	public void removeByTaskId(String var1);

	public void bulkCreate(List<TaskIdentityLink> var1);

	public int checkUserOperatorPermission(@Param(value = "rights") Set<String> var1,
			@Param(value = "taskId") String var2, @Param(value = "instanceId") String var3);

	public List<TaskIdentityLink> getByTaskId(String var1);
}
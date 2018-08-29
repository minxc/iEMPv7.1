package org.minxc.emp.bpm.core.dao;

import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.core.api.query.QueryFilter;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.minxc.emp.bpm.core.model.BpmTask;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BpmTaskDao extends CommonDao<String, BpmTask> {
	public List<BpmTask> getByInstIdNodeId(@Param(value = "instId") String var1, @Param(value = "nodeId") String var2);

	public List<BpmTask> getTodoList(QueryFilter var1);
}
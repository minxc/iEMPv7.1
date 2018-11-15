package org.minxc.emp.bpm.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.minxc.emp.bpm.core.model.BpmnTaskImpl;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.core.api.query.QueryFilter;

@Mapper
public interface BpmnTaskDao extends CommonDao<String, BpmnTaskImpl> {

	public List<BpmnTaskImpl> getByInstIdNodeId(@Param(value = "instId") String instId, @Param(value = "nodeId") String nodeId);

	public List<BpmnTaskImpl> getTodoList(QueryFilter queryFilter);
}
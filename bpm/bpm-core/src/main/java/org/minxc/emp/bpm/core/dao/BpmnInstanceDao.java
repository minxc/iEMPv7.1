package org.minxc.emp.bpm.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.bpm.core.model.BpmnInstanceImpl;
import org.minxc.emp.bpm.core.model.BpmnTaskApprove;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.core.api.query.QueryFilter;

@Mapper
public interface BpmnInstanceDao extends CommonDao<String, BpmnInstanceImpl> {

	public List<BpmnInstanceImpl> getApplyList(QueryFilter queryFilter);

	public List<BpmnTaskApprove> getApproveHistoryList(QueryFilter queryFilter);
}
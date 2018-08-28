package org.minxc.emp.bpm.core.dao;

import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.dao.BaseDao;

import java.util.List;

import org.minxc.emp.bpm.core.model.BpmInstance;
import org.minxc.emp.bpm.core.model.BpmTaskApprove;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface BpmInstanceDao extends BaseDao<String, BpmInstance> {
	public List<BpmInstance> getApplyList(QueryFilter var1);

	public List<BpmTaskApprove> getApproveHistoryList(QueryFilter var1);
}
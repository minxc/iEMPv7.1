package org.minxc.emp.bpm.core.manager;

import org.minxc.emp.core.api.query.QueryFilter;
import com.dstz.base.manager.Manager;

import java.util.List;

import org.minxc.emp.bpm.api.model.def.IBpmDefinition;
import org.minxc.emp.bpm.core.model.BpmInstance;
import org.minxc.emp.bpm.core.model.BpmTaskApprove;

public interface BpmInstanceManager extends Manager<String, BpmInstance> {
	public boolean isSuspendByInstId(String isntanceId);

	public List<BpmInstance> getApplyList(String var1, QueryFilter queryFilter);

	public List<BpmTaskApprove> getApproveHistoryList(String var1, QueryFilter queryFilter);

	public BpmInstance getTopInstance(BpmInstance bpmInstance);

	public BpmInstance genInstanceByDefinition(IBpmDefinition bpmDefinition);
}
package org.minxc.emp.bpm.core.manager;


import java.util.List;

import org.minxc.emp.bpm.api.model.def.IBpmDefinition;
import org.minxc.emp.bpm.core.model.BpmInstance;
import org.minxc.emp.bpm.core.model.BpmTaskApprove;
import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.core.api.query.QueryFilter;

public interface BpmInstanceManager extends Manager<String, BpmInstance> {
	public boolean isSuspendByInstId(String isntanceId);

	public List<BpmInstance> getApplyList(String var1, QueryFilter queryFilter);

	public List<BpmTaskApprove> getApproveHistoryList(String var1, QueryFilter queryFilter);

	public BpmInstance getTopInstance(BpmInstance bpmInstance);

	public BpmInstance genInstanceByDefinition(IBpmDefinition bpmDefinition);
}
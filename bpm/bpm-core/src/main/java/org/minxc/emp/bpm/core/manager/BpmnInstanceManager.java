package org.minxc.emp.bpm.core.manager;


import java.util.List;

import org.minxc.emp.bpm.api.model.def.BpmnDefinition;
import org.minxc.emp.bpm.core.model.BpmnInstanceImpl;
import org.minxc.emp.bpm.core.model.BpmnTaskApprove;
import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.core.api.query.QueryFilter;

public interface BpmnInstanceManager extends Manager<String, BpmnInstanceImpl> {
	public boolean isSuspendByInstId(String isntanceId);

	public List<BpmnInstanceImpl> getApplyList(String var1, QueryFilter queryFilter);

	public List<BpmnTaskApprove> getApproveHistoryList(String var1, QueryFilter queryFilter);

	public BpmnInstanceImpl getTopInstance(BpmnInstanceImpl bpmInstance);

	public BpmnInstanceImpl genInstanceByDefinition(BpmnDefinition bpmDefinition);
}
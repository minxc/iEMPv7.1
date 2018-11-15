package org.minxc.emp.bpm.core.manager;

import java.util.List;

import org.minxc.emp.bpm.api.engine.action.cmd.TaskActionCmd;
import org.minxc.emp.bpm.api.model.inst.BpmnInstance;
import org.minxc.emp.bpm.core.model.BpmnTaskOpinion;
import org.minxc.emp.common.manager.Manager;

public interface BpmnTaskOpinionManager extends Manager<String, BpmnTaskOpinion> {
	public BpmnTaskOpinion getByTaskId(String var1);

	public void createOpinionByInstance(BpmnInstance var1, String var2, boolean var3);

	public void createOpinionByTask(TaskActionCmd var1);

	public List<BpmnTaskOpinion> getByInstAndNode(String var1, String var2);

	public List<BpmnTaskOpinion> getByInstId(String var1);
}
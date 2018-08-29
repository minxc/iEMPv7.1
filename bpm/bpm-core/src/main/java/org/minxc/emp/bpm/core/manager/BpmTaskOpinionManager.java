package org.minxc.emp.bpm.core.manager;

import java.util.List;

import org.minxc.emp.bpm.api.engine.action.cmd.TaskActionCmd;
import org.minxc.emp.bpm.api.model.inst.IBpmInstance;
import org.minxc.emp.bpm.core.model.BpmTaskOpinion;
import org.minxc.emp.common.manager.Manager;

public interface BpmTaskOpinionManager extends Manager<String, BpmTaskOpinion> {
	public BpmTaskOpinion getByTaskId(String var1);

	public void createOpinionByInstance(IBpmInstance var1, String var2, boolean var3);

	public void createOpinionByTask(TaskActionCmd var1);

	public List<BpmTaskOpinion> getByInstAndNode(String var1, String var2);

	public List<BpmTaskOpinion> getByInstId(String var1);
}
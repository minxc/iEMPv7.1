package org.minxc.emp.bpm.engine.action.handler.task;


import javax.annotation.Resource;

import org.minxc.emp.bpm.act.service.ActTaskService;
import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.constant.NodeType;
import org.minxc.emp.bpm.api.engine.plugin.cmd.TaskCommand;
import org.minxc.emp.bpm.api.exception.BpmnStatusCode;
import org.minxc.emp.bpm.api.model.inst.BpmnInstance;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.minxc.emp.bpm.core.manager.BpmnTaskManager;
import org.minxc.emp.bpm.core.model.BpmnTaskImpl;
import org.minxc.emp.bpm.engine.action.cmd.DefualtTaskActionCmd;
import org.minxc.emp.bpm.engine.action.handler.AbsActionHandler;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.util.StringUtil;

public abstract class AbstractTaskActionHandler<T extends DefualtTaskActionCmd> extends AbsActionHandler<T> {
	@Resource
	protected ActTaskService ax;
	@Resource
	protected BpmnTaskManager ay;
	@Resource
	protected TaskCommand az;

	public void a(T actionModel) {
		BpmnTaskImpl bpmnTask = (BpmnTaskImpl) actionModel.getBpmTask();
		String taskId = bpmnTask.getTaskId();
		String destinationNode = bpmnTask.getBackNode();
		if (StringUtil.isEmpty(destinationNode)) {
			destinationNode = actionModel.getDestination();
		}

		if (StringUtil.isEmpty(destinationNode)) {
			this.ax.completeTask(taskId);
		} else {
			this.ax.completeTask(taskId, new String[]{destinationNode});
		}

	}

	protected void b(T data) {
		if (data.getBpmTask() == null) {
			BpmnTaskImpl task = (BpmnTaskImpl) this.ay.get(data.getTaskId());
			if (task == null) {
				throw new BusinessException(BpmnStatusCode.TASK_NOT_FOUND);
			} else {
				data.setBpmTask(task);
				data.setDefId(task.getDefId());
				data.setBpmInstance((BpmnInstance) this.f.get(task.getInstId()));
				this.l(data);
				this.a(data, this.a.getBpmNodeDef(task.getDefId(), task.getNodeId()));
			}
		}
	}

	public Boolean isSupport(BpmNodeDef nodeDef) {
		NodeType nodeType = nodeDef.getType();
		return nodeType != NodeType.USERTASK && nodeType != NodeType.SIGNTASK ? false : true;
	}

	protected void c(DefualtTaskActionCmd actionModel) {
		this.az.execute(EventType.TASK_PRE_COMPLETE_EVENT, actionModel);
	}

	public String getDefaultGroovyScript() {
		return "";
	}
}
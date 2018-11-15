package org.minxc.emp.bpm.engine.action.handler.task;

import java.util.List;

import org.minxc.emp.bpm.api.constant.ActionType;
import org.minxc.emp.bpm.api.model.def.NodeProperties;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.minxc.emp.bpm.engine.action.cmd.DefualtTaskActionCmd;
import org.springframework.stereotype.Component;

@Component
public class TaskReject2StartActionHandler extends TaskRejectActionHandler {
	protected String a(DefualtTaskActionCmd actionModel, NodeProperties nodeProperties) {
		List nodeDefs = this.a.getStartNodes(actionModel.getDefId());
		if (nodeDefs.size() > 1) {
			// empty if block
		}
		return ((BpmNodeDef) nodeDefs.get(0)).getNodeId();
	}

	public ActionType getActionType() {
		return ActionType.REJECT2START;
	}

	public int getSn() {
		return 4;
	}

	public Boolean isDefault() {
		return false;
	}

	public String getConfigPage() {
		return "/bpm/task/taskOpinionDialog.html";
	}
}
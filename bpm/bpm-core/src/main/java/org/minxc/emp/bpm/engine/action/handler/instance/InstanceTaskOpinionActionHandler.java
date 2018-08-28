package org.minxc.emp.bpm.engine.action.handler.instance;

import org.minxc.emp.bpm.api.constant.ActionType;
import org.minxc.emp.bpm.api.constant.NodeType;
import org.minxc.emp.bpm.api.engine.action.cmd.ActionCmd;
import org.minxc.emp.bpm.api.engine.action.handler.ActionHandler;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.springframework.stereotype.Component;

@Component
public class InstanceTaskOpinionActionHandler implements ActionHandler {
	public void execute(ActionCmd model) {
	}

	public ActionType getActionType() {
		return ActionType.TASKOPINION;
	}

	public int getSn() {
		return 5;
	}

	public Boolean isSupport(BpmNodeDef nodeDef) {
		if (nodeDef.getType() == NodeType.START) {
			return false;
		}
		return true;
	}

	public Boolean isDefault() {
		return true;
	}

	public String getConfigPage() {
		return "/bpm/instance/taskOpinionHistoryDialog.html";
	}

	public String getDefaultGroovyScript() {
		return "";
	}

	public String getDefaultBeforeScript() {
		return "";
	}
}
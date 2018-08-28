package org.minxc.emp.bpm.engine.action.handler.instance;

import org.minxc.emp.bpm.api.constant.ActionType;
import org.minxc.emp.bpm.api.constant.NodeType;
import org.minxc.emp.bpm.api.engine.action.cmd.ActionCmd;
import org.minxc.emp.bpm.api.engine.action.handler.ActionHandler;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.springframework.stereotype.Component;

@Component
public class InstanceManualEndActionHandler implements ActionHandler {
	public void execute(ActionCmd model) {
	}

	public ActionType getActionType() {
		return ActionType.MANUALEND;
	}

	public int getSn() {
		return 6;
	}

	public Boolean isSupport(BpmNodeDef nodeDef) {
		NodeType nodeType = nodeDef.getType();
		if (nodeType == NodeType.USERTASK || nodeType == NodeType.SIGNTASK) {
			return true;
		}
		return false;
	}

	public Boolean isDefault() {
		return false;
	}

	public String getConfigPage() {
		return "/bpm/task/taskOpinionDialog.html";
	}

	public String getDefaultGroovyScript() {
		return "";
	}

	public String getDefaultBeforeScript() {
		return "";
	}
}
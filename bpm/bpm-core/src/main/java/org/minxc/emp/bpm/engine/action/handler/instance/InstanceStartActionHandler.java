package org.minxc.emp.bpm.engine.action.handler.instance;


import javax.annotation.Resource;

import org.minxc.emp.bpm.act.service.ActInstanceService;
import org.minxc.emp.bpm.api.constant.ActionType;
import org.minxc.emp.bpm.api.constant.NodeType;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.minxc.emp.bpm.api.service.BpmnProcessDefinitionService;
import org.minxc.emp.bpm.core.manager.BpmnInstanceManager;
import org.minxc.emp.bpm.core.model.BpmnInstanceImpl;
import org.minxc.emp.bpm.engine.action.cmd.DefaultInstanceActionCmd;
import org.minxc.emp.core.util.StringUtil;
import org.springframework.stereotype.Component;

@Component
public class InstanceStartActionHandler extends InstanceSaveActionHandler {
	@Resource
    BpmnInstanceManager f;
	@Resource
	ActInstanceService aw;
	@Resource
    BpmnProcessDefinitionService a;

	public void a(DefaultInstanceActionCmd startActionModel) {
		String destination = startActionModel.getDestination();
		BpmnInstanceImpl instance = (BpmnInstanceImpl) startActionModel.getBpmInstance();
		String actInstId = null;
		if (StringUtil.isEmpty(destination)) {
			actInstId = this.aw.startProcessInstance(instance.getActDefId(), instance.getBizKey(),
					startActionModel.getActionVariables());
		} else {
			actInstId = this.aw.startProcessInstance(instance.getActDefId(), instance.getBizKey(),
					startActionModel.getActionVariables(), new String[]{destination});
		}

		instance.setActInstId(actInstId);
		this.e(startActionModel);
	}

	protected void d(DefaultInstanceActionCmd actionModel) {
	}

	public int getSn() {
		return 1;
	}

	public ActionType getActionType() {
		return ActionType.START;
	}

	public Boolean isSupport(BpmNodeDef nodeDef) {
		return nodeDef.getType() == NodeType.START;
	}
}
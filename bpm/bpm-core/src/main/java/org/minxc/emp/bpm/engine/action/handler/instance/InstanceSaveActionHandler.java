package org.minxc.emp.bpm.engine.action.handler.instance;


import java.io.Serializable;

import org.minxc.emp.bpm.api.constant.ActionType;
import org.minxc.emp.bpm.api.constant.InstanceStatus;
import org.minxc.emp.bpm.api.constant.NodeType;
import org.minxc.emp.bpm.api.engine.action.cmd.BaseActionCmd;
import org.minxc.emp.bpm.api.model.def.IBpmDefinition;
import org.minxc.emp.bpm.api.model.inst.IBpmInstance;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.minxc.emp.bpm.api.service.BpmProcessDefService;
import org.minxc.emp.bpm.core.manager.BpmInstanceManager;
import org.minxc.emp.bpm.core.model.BpmInstance;
import org.minxc.emp.bpm.engine.action.cmd.DefaultInstanceActionCmd;
import org.minxc.emp.bpm.engine.action.handler.AbsActionHandler;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.util.StringUtil;
import org.springframework.stereotype.Component;

@Component
public class InstanceSaveActionHandler extends AbsActionHandler<DefaultInstanceActionCmd> {
	
	protected void a(DefaultInstanceActionCmd model) {
		BpmInstance instance = (BpmInstance) model.getBpmInstance();
		instance.setStatus(InstanceStatus.STATUS_DRAFT.getKey());
	}

	protected void b(DefaultInstanceActionCmd actionModel) {
	}

	protected void c(DefaultInstanceActionCmd data) {
		data.setBpmDefinition(this.a.getDefinitionById(data.getDefId()));
		this.f(data);
		this.l(data);
		this.a(data, this.a.getStartEvent(data.getDefId()));
	}

	protected void d(DefaultInstanceActionCmd actionModel) {
		this.e(actionModel);
	}

	protected void e(DefaultInstanceActionCmd actionModel) {
		BpmInstance instance = (BpmInstance) actionModel.getBpmInstance();
		if (instance.hasCreate()) {
			this.f.update(instance);
		} else {
			this.f.create(instance);
		}

	}

	protected void f(DefaultInstanceActionCmd intanceCmdData) {
		String instId = intanceCmdData.getInstanceId();
		BpmInstance instance = null;
		if (StringUtil.isNotEmpty(instId)) {
			instance = (BpmInstance) this.f.get(instId);
			if (StringUtil.isNotEmpty(instance.getActInstId())) {
				throw new BusinessException("草稿已经启动，请勿多次启动该草稿！");
			}
		}

		if (instance == null) {
			IBpmDefinition bpmDefinition = intanceCmdData.getBpmDefinition();
			instance = this.f.genInstanceByDefinition(bpmDefinition);
		}

		intanceCmdData.setBpmInstance(instance);
	}

	public ActionType getActionType() {
		return ActionType.DRAFT;
	}

	public int getSn() {
		return 2;
	}

	public Boolean isSupport(BpmNodeDef nodeDef) {
		return nodeDef.getType() == NodeType.START ? true : false;
	}

	public String getConfigPage() {
		return null;
	}

	public String getDefaultGroovyScript() {
		return "";
	}

	@Override
	protected void h(DefaultInstanceActionCmd var1) {
		this.e(var1);
	}

	@Override
	protected void i(DefaultInstanceActionCmd var1) {
		this.b(var1);
	}

}
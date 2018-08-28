package org.minxc.emp.bpm.engine.listener;

import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.constant.InstanceStatus;
import org.minxc.emp.bpm.api.constant.ScriptType;
import org.minxc.emp.bpm.api.engine.action.cmd.BaseActionCmd;
import org.minxc.emp.bpm.api.engine.action.cmd.InstanceActionCmd;
import org.minxc.emp.bpm.api.engine.context.BpmContext;
import org.minxc.emp.bpm.api.model.def.IBpmDefinition;
import org.minxc.emp.bpm.api.model.inst.IBpmInstance;
import org.minxc.emp.bpm.core.manager.BpmInstanceManager;
import org.minxc.emp.bpm.core.manager.BpmTaskOpinionManager;
import org.minxc.emp.bpm.core.manager.BpmTaskStackManager;
import org.minxc.emp.bpm.core.model.BpmInstance;
import org.minxc.emp.bpm.engine.action.cmd.DefaultInstanceActionCmd;
import org.minxc.emp.bpm.engine.listener.AbstractInstanceListener;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class InstanceEndEventListener extends AbstractInstanceListener {
	@Resource
	BpmTaskOpinionManager aO;
	@Resource
	BpmInstanceManager f;
	@Resource
	BpmTaskStackManager aA;

	public EventType getBeforeTriggerEventType() {
		return EventType.END_EVENT;
	}

	public EventType getAfterTriggerEventType() {
		return EventType.END_POST_EVENT;
	}

	public void a(InstanceActionCmd instanceActionModel) {
		this.LOG.debug("流程实例【{}】,ID【{}】开始触发终止事件", (Object) instanceActionModel.getBpmInstance().getSubject(),
				(Object) instanceActionModel.getBpmInstance().getId());
	}

	public void b(InstanceActionCmd instanceActionModel) {
		this.aO.createOpinionByInstance(instanceActionModel.getBpmInstance(), instanceActionModel.getFormId(), false);
		BpmInstance instance = (BpmInstance) instanceActionModel.getBpmInstance();
		instance.setStatus(InstanceStatus.STATUS_END.getKey());
		instance.setEndTime(new Date());
		instance.setDuration(Long.valueOf(instance.getEndTime().getTime() - instance.getCreateTime().getTime()));
		this.f.update(instance);
	}

	public void c(InstanceActionCmd instanceActionModel) {
		this.aA.removeByInstanceId(instanceActionModel.getInstanceId());
		this.LOG.debug("流程实例【{}】,ID【{}】已经终止", (Object) instanceActionModel.getBpmInstance().getSubject(),
				(Object) instanceActionModel.getBpmInstance().getId());
	}

	protected ScriptType getScriptType() {
		return ScriptType.END;
	}

	protected InstanceActionCmd a(ExecutionEntity excutionEntity) {
		BaseActionCmd actionModle = (BaseActionCmd) BpmContext.getActionModel();
		DefaultInstanceActionCmd instanceModel = new DefaultInstanceActionCmd();
		instanceModel.setBpmInstance(actionModle.getBpmInstance());
		instanceModel.setBpmDefinition(actionModle.getBpmDefinition());
		instanceModel.setBizDataMap(actionModle.getBizDataMap());
		instanceModel.setBusinessKey(actionModle.getBusinessKey());
		instanceModel.setActionName("end");
		return instanceModel;
	}
}
package org.minxc.emp.bpm.engine.listener;

import java.util.Date;
import javax.annotation.Resource;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.constant.InstanceStatus;
import org.minxc.emp.bpm.api.constant.ScriptType;
import org.minxc.emp.bpm.api.engine.action.cmd.BaseActionCmd;
import org.minxc.emp.bpm.api.engine.action.cmd.InstanceActionCmd;
import org.minxc.emp.bpm.api.engine.context.BpmnContext;
import org.minxc.emp.bpm.core.manager.BpmnInstanceManager;
import org.minxc.emp.bpm.core.manager.BpmnTaskOpinionManager;
import org.minxc.emp.bpm.core.manager.BpmnTaskStackManager;
import org.minxc.emp.bpm.core.model.BpmnInstanceImpl;
import org.minxc.emp.bpm.engine.action.cmd.DefaultInstanceActionCmd;
import org.springframework.stereotype.Component;

@Component
public class InstanceEndEventListener extends AbstractInstanceListener {
	@Resource
	BpmnTaskOpinionManager aO;
	@Resource
    BpmnInstanceManager f;
	@Resource
	BpmnTaskStackManager aA;

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
		BpmnInstanceImpl instance = (BpmnInstanceImpl) instanceActionModel.getBpmInstance();
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
		BaseActionCmd actionModle = (BaseActionCmd) BpmnContext.getActionModel();
		DefaultInstanceActionCmd instanceModel = new DefaultInstanceActionCmd();
		instanceModel.setBpmInstance(actionModle.getBpmInstance());
		instanceModel.setBpmDefinition(actionModle.getBpmDefinition());
		instanceModel.setBizDataMap(actionModle.getBizDataMap());
		instanceModel.setBusinessKey(actionModle.getBusinessKey());
		instanceModel.setActionName("end");
		return instanceModel;
	}
}
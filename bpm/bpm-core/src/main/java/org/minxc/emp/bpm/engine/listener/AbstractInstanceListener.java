package org.minxc.emp.bpm.engine.listener;

import javax.annotation.Resource;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.impl.ActivitiEntityEventImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.minxc.emp.bpm.act.listener.ActEventListener;
import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.constant.ScriptType;
import org.minxc.emp.bpm.api.engine.action.cmd.InstanceActionCmd;
import org.minxc.emp.bpm.api.engine.plugin.cmd.ExecutionCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractInstanceListener implements ActEventListener {
	protected Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Resource
	private ExecutionCommand aL;

	public abstract EventType getBeforeTriggerEventType();

	public abstract EventType getAfterTriggerEventType();

	public abstract void a(InstanceActionCmd var1);

	public abstract void b(InstanceActionCmd var1);

	public abstract void c(InstanceActionCmd var1);

	public void notify(ActivitiEvent event) {
		ActivitiEntityEventImpl processEvent = (ActivitiEntityEventImpl) event;
		ExecutionEntity excutionEntity = (ExecutionEntity) processEvent.getEntity();
		InstanceActionCmd actionModel = this.a(excutionEntity);
		this.a(actionModel);
		this.LOG.debug("============【{}】插件执行开始=============", (Object) this.getBeforeTriggerEventType().getValue());
		if (this.getBeforeTriggerEventType() != null) {
			this.aL.execute(this.getBeforeTriggerEventType(), actionModel);
		}
		this.LOG.debug("============【{}】插件执行完毕============", (Object) this.getBeforeTriggerEventType().getValue());
		this.b(actionModel);
		this.LOG.debug("============【{}】插件执行开始=============", (Object) this.getAfterTriggerEventType().getValue());
		if (this.getAfterTriggerEventType() != null) {
			this.aL.execute(this.getAfterTriggerEventType(), actionModel);
		}
		this.LOG.debug("============【{}】插件执行完毕============", (Object) this.getAfterTriggerEventType().getValue());
		this.c(actionModel);
	}

	protected abstract InstanceActionCmd a(ExecutionEntity var1);

	protected abstract ScriptType getScriptType();
}
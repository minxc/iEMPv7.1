package org.minxc.emp.bpm.engine.listener;

import javax.annotation.Resource;
import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.minxc.emp.bpm.act.listener.ActEventListener;
import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.constant.ScriptType;
import org.minxc.emp.bpm.api.engine.action.cmd.TaskActionCmd;
import org.minxc.emp.bpm.api.engine.plugin.cmd.TaskCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractTaskListener<T extends TaskActionCmd> implements ActEventListener {
	protected Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Resource
	protected TaskCommand az;

	public abstract EventType getBeforeTriggerEventType();

	public abstract EventType getAfterTriggerEventType();

	public abstract void a(T var1);

	public abstract void b(T var1);

	public abstract void c(T var1);

	public void notify(ActivitiEvent event) {
		ActivitiEntityEvent entityEvent = (ActivitiEntityEvent) event;
		TaskEntity taskEntity = (TaskEntity) entityEvent.getEntity();
		T model = this.a(taskEntity);
		this.a(model);
		if (this.getBeforeTriggerEventType() != null) {
			this.LOG.debug("============【{}】插件执行开始=============", (Object) this.getBeforeTriggerEventType().getValue());
			this.az.execute(this.getBeforeTriggerEventType(), model);
			this.LOG.debug("============【{}】插件执行完毕=============", (Object) this.getBeforeTriggerEventType().getValue());
		}
		this.b(model);
		if (this.getAfterTriggerEventType() != null) {
			this.LOG.debug("============【{}】插件执行开始=============", (Object) this.getAfterTriggerEventType().getValue());
			this.az.execute(this.getAfterTriggerEventType(), model);
			this.LOG.debug("============【{}】插件执行完毕=============", (Object) this.getAfterTriggerEventType().getValue());
		}
		this.c(model);
	}

	protected abstract ScriptType getScriptType();

	public abstract T a(TaskEntity var1);
}
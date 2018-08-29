package org.minxc.emp.bpm.engine.listener;

import com.dstz.base.api.constant.IStatusCode;
import org.minxc.emp.core.api.exception.BusinessException;
import com.dstz.base.core.util.StringUtil;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Resource;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.impl.ActivitiActivityEventImpl;
import org.minxc.emp.bpm.act.listener.ActEventListener;
import org.minxc.emp.bpm.api.engine.action.cmd.ActionCmd;
import org.minxc.emp.bpm.api.engine.action.cmd.BaseActionCmd;
import org.minxc.emp.bpm.api.engine.context.BpmContext;
import org.minxc.emp.bpm.api.exception.BpmStatusCode;
import org.minxc.emp.bpm.api.model.def.IBpmDefinition;
import org.minxc.emp.bpm.api.model.inst.IBpmInstance;
import org.minxc.emp.bpm.core.manager.BpmDefinitionManager;
import org.minxc.emp.bpm.core.manager.BpmInstanceManager;
import org.minxc.emp.bpm.core.manager.BpmTaskStackManager;
import org.minxc.emp.bpm.core.model.BpmDefinition;
import org.minxc.emp.bpm.core.model.BpmInstance;
import org.minxc.emp.bpm.core.model.BpmTaskStack;
import org.minxc.emp.bpm.engine.action.cmd.DefualtTaskActionCmd;
import org.springframework.stereotype.Component;

@Component
public class ActivityComplatedListener implements ActEventListener {
	@Resource
	private BpmInstanceManager aM;
	@Resource
	private BpmDefinitionManager c;
	@Resource
	private BpmTaskStackManager aA;

	public void notify(ActivitiEvent event) {
		if (!(event instanceof ActivitiActivityEventImpl)) {
			return;
		}
		ActivitiActivityEventImpl activitEvent = (ActivitiActivityEventImpl) event;
		if (activitEvent.getActivityType().equals("callActivity")) {
			this.a(activitEvent);
		}
	}

	private void a(ActivitiActivityEventImpl activitEvent) {
		BaseActionCmd actionCmd = (BaseActionCmd) BpmContext.getActionModel();
		IBpmInstance childInstance = actionCmd.getBpmInstance();
		if (StringUtil.isZeroEmpty((String) childInstance.getParentInstId())) {
			throw new BusinessException("子流程提供的线程变量中，流程实例信息异常！", (IStatusCode) BpmStatusCode.ACTIONCMD_ERROR);
		}
		BpmInstance bpmInstance = (BpmInstance) this.aM.get(childInstance.getParentInstId());
		if (!bpmInstance.getActInstId().equals(activitEvent.getProcessInstanceId())) {
			throw new BusinessException("子流程提供的父流程实例，与外部子流程ACTVITI actInstanceID 不一致！",
					(IStatusCode) BpmStatusCode.ACTIONCMD_ERROR);
		}
		BpmTaskStack bpmTaskStack = this.c(activitEvent.getExecutionId());
		BpmDefinition bpmDefinition = (BpmDefinition) this.c.get(bpmInstance.getDefId());
		DefualtTaskActionCmd callActiviti = new DefualtTaskActionCmd();
		callActiviti.setBpmDefinition((IBpmDefinition) bpmDefinition);
		callActiviti.setBpmInstance((IBpmInstance) bpmInstance);
		callActiviti.setTaskStack(bpmTaskStack);
		BpmContext.setActionModel((ActionCmd) callActiviti);
	}

	private BpmTaskStack c(String executionId) {
		BpmTaskStack bpmTaskStack = this.aA.getByTaskId(executionId);
		bpmTaskStack.setEndTime(new Date());
		this.aA.update(bpmTaskStack);
		return bpmTaskStack;
	}
}
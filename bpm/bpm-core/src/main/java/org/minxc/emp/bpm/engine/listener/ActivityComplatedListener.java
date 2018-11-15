package org.minxc.emp.bpm.engine.listener;


import java.util.Date;
import javax.annotation.Resource;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.impl.ActivitiActivityEventImpl;
import org.minxc.emp.bpm.act.listener.ActEventListener;
import org.minxc.emp.bpm.api.engine.action.cmd.ActionCmd;
import org.minxc.emp.bpm.api.engine.action.cmd.BaseActionCmd;
import org.minxc.emp.bpm.api.engine.context.BpmnContext;
import org.minxc.emp.bpm.api.exception.BpmnStatusCode;
import org.minxc.emp.bpm.api.model.def.BpmnDefinition;
import org.minxc.emp.bpm.api.model.inst.BpmnInstance;
import org.minxc.emp.bpm.core.manager.BpmnDefinitionManager;
import org.minxc.emp.bpm.core.manager.BpmnInstanceManager;
import org.minxc.emp.bpm.core.manager.BpmnTaskStackManager;
import org.minxc.emp.bpm.core.model.BpmnDefinitionImpl;
import org.minxc.emp.bpm.core.model.BpmnInstanceImpl;
import org.minxc.emp.bpm.core.model.BpmnTaskStack;
import org.minxc.emp.bpm.engine.action.cmd.DefualtTaskActionCmd;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.util.StringUtil;
import org.springframework.stereotype.Component;

@Component
public class ActivityComplatedListener implements ActEventListener {
	@Resource
	private BpmnInstanceManager aM;
	@Resource
	private BpmnDefinitionManager c;
	@Resource
	private BpmnTaskStackManager aA;

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
		BaseActionCmd actionCmd = (BaseActionCmd) BpmnContext.getActionModel();
		BpmnInstance childInstance = actionCmd.getBpmInstance();
		if (StringUtil.isZeroEmpty((String) childInstance.getParentInstId())) {
			throw new BusinessException("子流程提供的线程变量中，流程实例信息异常！", BpmnStatusCode.ACTIONCMD_ERROR);
		}
		BpmnInstanceImpl bpmInstance = (BpmnInstanceImpl) this.aM.get(childInstance.getParentInstId());
		if (!bpmInstance.getActInstId().equals(activitEvent.getProcessInstanceId())) {
			throw new BusinessException("子流程提供的父流程实例，与外部子流程ACTVITI actInstanceID 不一致！",
					BpmnStatusCode.ACTIONCMD_ERROR);
		}
		BpmnTaskStack bpmnTaskStack = this.c(activitEvent.getExecutionId());
		BpmnDefinitionImpl bpmnDefinition = (BpmnDefinitionImpl) this.c.get(bpmInstance.getDefId());
		DefualtTaskActionCmd callActiviti = new DefualtTaskActionCmd();
		callActiviti.setBpmDefinition((BpmnDefinition) bpmnDefinition);
		callActiviti.setBpmInstance((BpmnInstance) bpmInstance);
		callActiviti.setTaskStack(bpmnTaskStack);
		BpmnContext.setActionModel((ActionCmd) callActiviti);
	}

	private BpmnTaskStack c(String executionId) {
		BpmnTaskStack bpmnTaskStack = this.aA.getByTaskId(executionId);
		bpmnTaskStack.setEndTime(new Date());
		this.aA.update(bpmnTaskStack);
		return bpmnTaskStack;
	}
}
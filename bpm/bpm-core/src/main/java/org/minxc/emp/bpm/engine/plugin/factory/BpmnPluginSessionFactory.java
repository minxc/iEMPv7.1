package org.minxc.emp.bpm.engine.plugin.factory;

import org.activiti.engine.delegate.VariableScope;
import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.engine.action.cmd.ActionCmd;
import org.minxc.emp.bpm.api.engine.action.cmd.InstanceActionCmd;
import org.minxc.emp.bpm.api.engine.action.cmd.TaskActionCmd;
import org.minxc.emp.bpm.api.engine.context.BpmnContext;
import org.minxc.emp.bpm.engine.action.cmd.DefaultInstanceActionCmd;
import org.minxc.emp.bpm.engine.action.cmd.DefualtTaskActionCmd;
import org.minxc.emp.bpm.engine.plugin.session.*;
import org.minxc.emp.bpm.engine.plugin.session.BpmnUserCalcPluginSession;
import org.minxc.emp.bpm.engine.plugin.session.BpmnTaskPluginSession;
import org.minxc.emp.bpm.engine.plugin.session.impl.DefaultBpmnExecutionPluginSession;
import org.minxc.emp.bpm.engine.plugin.session.impl.DefaultBpmnTaskPluginSession;
import org.minxc.emp.bpm.engine.plugin.session.impl.DefaultBpmnUserCalcPluginSession;

public class BpmnPluginSessionFactory {
	public static BpmnExecutionPluginSession buildExecutionPluginSession(InstanceActionCmd actionModel,
                                                                         EventType eventType) {
		DefaultBpmnExecutionPluginSession executionPluginSession = new DefaultBpmnExecutionPluginSession();
		executionPluginSession.setBoDatas(actionModel.getBizDataMap());
		executionPluginSession.setBpmInstance(actionModel.getBpmInstance());
		executionPluginSession.setEventType(eventType);
		executionPluginSession
				.setVariableScope((VariableScope) ((DefaultInstanceActionCmd) actionModel).getExecutionEntity());
		return executionPluginSession;
	}

	public static BpmnTaskPluginSession buildTaskPluginSession(TaskActionCmd actionModel, EventType eventType) {
		DefualtTaskActionCmd taskActionModel = (DefualtTaskActionCmd) actionModel;
		DefaultBpmnTaskPluginSession session = new DefaultBpmnTaskPluginSession();
		session.setBoDatas(actionModel.getBizDataMap());
		session.setBpmInstance(actionModel.getBpmInstance());
		session.setEventType(eventType);
		session.setVariableScope((VariableScope) taskActionModel.getDelagateTask());
		session.setBpmTask(taskActionModel.getBpmTask());
		return session;
	}

	public static DefaultBpmnExecutionPluginSession buildExecutionPluginSession(TaskActionCmd actionModel,
                                                                                EventType eventType) {
		DefualtTaskActionCmd taskActionModel = (DefualtTaskActionCmd) actionModel;
		DefaultBpmnExecutionPluginSession executionPluginSession = new DefaultBpmnExecutionPluginSession();
		executionPluginSession.setBoDatas(actionModel.getBizDataMap());
		executionPluginSession.setBpmInstance(actionModel.getBpmInstance());
		executionPluginSession.setVariableScope((VariableScope) taskActionModel.getDelagateTask());
		executionPluginSession.setEventType(eventType);
		return executionPluginSession;
	}

	public static BpmnUserCalcPluginSession buildBpmUserCalcPluginSession(BpmnPluginSession pluginSession) {
		ActionCmd action;
		DefaultBpmnExecutionPluginSession plugin = (DefaultBpmnExecutionPluginSession) pluginSession;
		DefaultBpmnUserCalcPluginSession userCalcPluginSession = new DefaultBpmnUserCalcPluginSession();
		userCalcPluginSession.setBoDatas(pluginSession.getBoDatas());
		userCalcPluginSession.setVariableScope(plugin.getVariableScope());
		if (pluginSession instanceof BpmnTaskPluginSession) {
			BpmnTaskPluginSession taskSession = (BpmnTaskPluginSession) pluginSession;
			userCalcPluginSession.setBpmTask(taskSession.getBpmTask());
		}
		if ((action = BpmnContext.getActionModel()) != null && action instanceof TaskActionCmd) {
			TaskActionCmd taskCmd = (TaskActionCmd) action;
			userCalcPluginSession.setBpmTask(taskCmd.getBpmTask());
		}
		return userCalcPluginSession;
	}
}
package org.minxc.emp.bpm.engine.plugin.cmd;


import javax.annotation.Resource;

import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.engine.action.cmd.InstanceActionCmd;
import org.minxc.emp.bpm.api.engine.plugin.cmd.ExecutionCommand;
import org.minxc.emp.bpm.api.engine.plugin.context.BpmnPluginContext;
import org.minxc.emp.bpm.api.engine.plugin.def.BpmnExecutionPluginDef;
import org.minxc.emp.bpm.api.engine.plugin.def.BpmnPluginDef;
import org.minxc.emp.bpm.api.exception.BpmnStatusCode;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.minxc.emp.bpm.api.service.BpmnProcessDefinitionService;
import org.minxc.emp.bpm.engine.model.DefaultBpmnProcessDefinition;
import org.minxc.emp.bpm.engine.plugin.factory.BpmnPluginFactory;
import org.minxc.emp.bpm.engine.plugin.factory.BpmnPluginSessionFactory;
import org.minxc.emp.bpm.engine.plugin.runtime.BpmnExecutionPlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmnExecutionPluginSession;
import org.minxc.emp.core.api.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExecutionPluginCommand implements ExecutionCommand {
	protected Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Resource
	BpmnProcessDefinitionService a;

	public void execute(EventType eventType, InstanceActionCmd actionModel) {
		String defId = actionModel.getDefId();
		DefaultBpmnProcessDefinition bpmProcessDef = (DefaultBpmnProcessDefinition) this.a.getBpmProcessDef(defId);
		for (Object bpmPluginContext : bpmProcessDef.getBpmPluginContexts()) {
			this.a((BpmnPluginContext) bpmPluginContext, actionModel, eventType);
		}
		BpmNodeDef nodeDef = null;
		if (eventType == EventType.START_POST_EVENT || eventType == EventType.START_EVENT) {
			nodeDef = this.a.getStartEvent(defId);
		} else if (eventType == EventType.END_EVENT || eventType == EventType.END_POST_EVENT) {
			nodeDef = (BpmNodeDef) this.a.getEndEvents(defId).get(0);
		}
		if (nodeDef != null && nodeDef.getBpmnPluginContexts() != null) {
			for (BpmnPluginContext bpmnPluginContext : nodeDef.getBpmnPluginContexts()) {
				this.a(bpmnPluginContext, actionModel, eventType);
			}
		}
	}

	private void a(BpmnPluginContext bpmnPluginContext, InstanceActionCmd actionModel, EventType eventType) {
		BpmnPluginDef bpmnPluginDef = bpmnPluginContext.getBpmPluginDef();
		if (bpmnPluginDef instanceof BpmnExecutionPluginDef) {
			BpmnExecutionPluginDef bpmExecutionPluginDef = (BpmnExecutionPluginDef) bpmnPluginDef;
			BpmnExecutionPlugin bpmnExecutionPlugin = BpmnPluginFactory
					.buildExecutionPlugin((BpmnPluginContext) bpmnPluginContext, (EventType) eventType);
			BpmnExecutionPluginSession session = BpmnPluginSessionFactory
					.buildExecutionPluginSession((InstanceActionCmd) actionModel, (EventType) eventType);
			if (bpmnExecutionPlugin != null) {
				try {
					bpmnExecutionPlugin.execute((Object) session, (Object) bpmExecutionPluginDef);
					this.LOG.debug("==>执行插件【{}】", (Object) bpmnPluginContext.getTitle());
				} catch (Exception e) {
					this.LOG.error("执行插件【{}】出现异常:{}", new Object[]{bpmnPluginContext.getTitle(), e.getMessage(), e});
					throw new BusinessException(bpmnPluginContext.getTitle(), BpmnStatusCode.PLUGIN_ERROR,
							(Throwable) e);
				}
			}
		}
	}
}
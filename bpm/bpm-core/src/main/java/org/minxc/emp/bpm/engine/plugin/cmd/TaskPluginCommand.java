package org.minxc.emp.bpm.engine.plugin.cmd;


import javax.annotation.Resource;

import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.engine.action.cmd.TaskActionCmd;
import org.minxc.emp.bpm.api.engine.plugin.cmd.TaskCommand;
import org.minxc.emp.bpm.api.engine.plugin.context.BpmnPluginContext;
import org.minxc.emp.bpm.api.engine.plugin.def.BpmnExecutionPluginDef;
import org.minxc.emp.bpm.api.engine.plugin.def.BpmnPluginDef;
import org.minxc.emp.bpm.api.engine.plugin.def.BpmnTaskPluginDef;
import org.minxc.emp.bpm.api.exception.BpmnStatusCode;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.minxc.emp.bpm.api.service.BpmnProcessDefinitionService;
import org.minxc.emp.bpm.engine.model.DefaultBpmnProcessDefinition;
import org.minxc.emp.bpm.engine.plugin.factory.BpmnPluginFactory;
import org.minxc.emp.bpm.engine.plugin.factory.BpmnPluginSessionFactory;
import org.minxc.emp.bpm.engine.plugin.runtime.BpmnExecutionPlugin;
import org.minxc.emp.bpm.engine.plugin.runtime.BpmnTaskPlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmnTaskPluginSession;
import org.minxc.emp.bpm.engine.plugin.session.impl.DefaultBpmnExecutionPluginSession;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.util.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TaskPluginCommand implements TaskCommand {
	protected Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Resource
    BpmnProcessDefinitionService a;

	public void execute(EventType eventType, TaskActionCmd actionModel) {
		BpmnExecutionPlugin bpmnExecutionPlugin;
		String defId = actionModel.getBpmTask().getDefId();
		BpmNodeDef bpmNodeDef = this.a.getBpmNodeDef(defId, actionModel.getNodeId());
		BpmnTaskPluginSession bpmTaskSession = BpmnPluginSessionFactory
				.buildTaskPluginSession((TaskActionCmd) actionModel, (EventType) eventType);
		DefaultBpmnExecutionPluginSession executionSession = BpmnPluginSessionFactory
				.buildExecutionPluginSession((TaskActionCmd) actionModel, (EventType) eventType);
		for (BpmnPluginContext bpmnPluginContext : bpmNodeDef.getBpmnPluginContexts()) {
			if (BeanUtils.isEmpty(bpmnPluginContext.getEventTypes()))
				continue;
			BpmnPluginDef bpmnPluginDef = bpmnPluginContext.getBpmPluginDef();
			if (bpmnPluginDef instanceof BpmnTaskPluginDef) {
				BpmnTaskPluginDef bpmnTaskPluginDef = (BpmnTaskPluginDef) bpmnPluginDef;
				BpmnTaskPlugin bpmnTaskPlugin = BpmnPluginFactory.buildTaskPlugin((BpmnPluginContext) bpmnPluginContext,
						(EventType) eventType);
				if (bpmnTaskPlugin == null)
					continue;
				try {
					this.LOG.debug("==>执行任务插件【{}】", (Object) bpmnPluginContext.getTitle());
					bpmnTaskPlugin.execute((Object) bpmTaskSession, (Object) bpmnTaskPluginDef);
				} catch (Exception e) {
					this.LOG.error("执行任务插件【{}】出现异常:{}", new Object[]{bpmnPluginContext.getTitle(), e.getMessage(), e});
					throw new BusinessException(bpmnPluginContext.getTitle(), BpmnStatusCode.PLUGIN_ERROR,
							(Throwable) e);
				}
			}
			if (!(bpmnPluginDef instanceof BpmnExecutionPluginDef) || (bpmnExecutionPlugin = BpmnPluginFactory
					.buildExecutionPlugin((BpmnPluginContext) bpmnPluginContext, (EventType) eventType)) == null)
				continue;
			try {
				this.LOG.debug("==>执行节点实例插件【{}】", (Object) bpmnPluginContext.getTitle());
				bpmnExecutionPlugin.execute((Object) executionSession, (Object) bpmnPluginContext.getBpmPluginDef());
			} catch (Exception e) {
				this.LOG.error("节点实例插件【{}】出现异常:{}", new Object[]{bpmnPluginContext.getTitle(), e.getMessage(), e});
				throw new BusinessException(bpmnPluginContext.getTitle(), BpmnStatusCode.PLUGIN_ERROR,
						(Throwable) e);
			}
		}
		DefaultBpmnProcessDefinition bpmProcessDef = (DefaultBpmnProcessDefinition) this.a.getBpmProcessDef(defId);
		for (BpmnPluginContext globalCtx : bpmProcessDef.getBpmPluginContexts()) {
			bpmnExecutionPlugin = BpmnPluginFactory.buildExecutionPlugin((BpmnPluginContext) globalCtx,
					(EventType) eventType);
			if (bpmnExecutionPlugin == null)
				continue;
			try {
				this.LOG.debug("==>【{}】节点执行全局插件【{}】", (Object) bpmNodeDef.getName(), (Object) globalCtx.getTitle());
				bpmnExecutionPlugin.execute((Object) executionSession, (Object) globalCtx.getBpmPluginDef());
			} catch (Exception e) {
				this.LOG.error("【{}】节点执行全局插件【{}】出现异常:{}",
						new Object[]{bpmNodeDef.getName(), globalCtx.getTitle(), e.getMessage(), e});
				throw new BusinessException(globalCtx.getTitle(), BpmnStatusCode.PLUGIN_ERROR,
						(Throwable) e);
			}
		}
	}
}
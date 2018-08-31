package org.minxc.emp.bpm.engine.plugin.cmd;


import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.engine.action.cmd.InstanceActionCmd;
import org.minxc.emp.bpm.api.engine.plugin.cmd.ExecutionCommand;
import org.minxc.emp.bpm.api.engine.plugin.context.BpmPluginContext;
import org.minxc.emp.bpm.api.engine.plugin.def.BpmExecutionPluginDef;
import org.minxc.emp.bpm.api.engine.plugin.def.BpmPluginDef;
import org.minxc.emp.bpm.api.exception.BpmStatusCode;
import org.minxc.emp.bpm.api.model.def.BpmProcessDef;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.minxc.emp.bpm.api.service.BpmProcessDefService;
import org.minxc.emp.bpm.engine.model.DefaultBpmProcessDef;
import org.minxc.emp.bpm.engine.plugin.factory.BpmPluginFactory;
import org.minxc.emp.bpm.engine.plugin.factory.BpmPluginSessionFactory;
import org.minxc.emp.bpm.engine.plugin.runtime.BpmExecutionPlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmExecutionPluginSession;
import org.minxc.emp.core.api.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExecutionPluginCommand implements ExecutionCommand {
	protected Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Resource
	BpmProcessDefService a;

	public void execute(EventType eventType, InstanceActionCmd actionModel) {
		String defId = actionModel.getDefId();
		DefaultBpmProcessDef bpmProcessDef = (DefaultBpmProcessDef) this.a.getBpmProcessDef(defId);
		for (Object bpmPluginContext : bpmProcessDef.getBpmPluginContexts()) {
			this.a((BpmPluginContext) bpmPluginContext, actionModel, eventType);
		}
		BpmNodeDef nodeDef = null;
		if (eventType == EventType.START_POST_EVENT || eventType == EventType.START_EVENT) {
			nodeDef = this.a.getStartEvent(defId);
		} else if (eventType == EventType.END_EVENT || eventType == EventType.END_POST_EVENT) {
			nodeDef = (BpmNodeDef) this.a.getEndEvents(defId).get(0);
		}
		if (nodeDef != null && nodeDef.getBpmPluginContexts() != null) {
			for (BpmPluginContext bpmPluginContext : nodeDef.getBpmPluginContexts()) {
				this.a(bpmPluginContext, actionModel, eventType);
			}
		}
	}

	private void a(BpmPluginContext bpmPluginContext, InstanceActionCmd actionModel, EventType eventType) {
		BpmPluginDef bpmPluginDef = bpmPluginContext.getBpmPluginDef();
		if (bpmPluginDef instanceof BpmExecutionPluginDef) {
			BpmExecutionPluginDef bpmExecutionPluginDef = (BpmExecutionPluginDef) bpmPluginDef;
			BpmExecutionPlugin bpmExecutionPlugin = BpmPluginFactory
					.buildExecutionPlugin((BpmPluginContext) bpmPluginContext, (EventType) eventType);
			BpmExecutionPluginSession session = BpmPluginSessionFactory
					.buildExecutionPluginSession((InstanceActionCmd) actionModel, (EventType) eventType);
			if (bpmExecutionPlugin != null) {
				try {
					bpmExecutionPlugin.execute((Object) session, (Object) bpmExecutionPluginDef);
					this.LOG.debug("==>执行插件【{}】", (Object) bpmPluginContext.getTitle());
				} catch (Exception e) {
					this.LOG.error("执行插件【{}】出现异常:{}", new Object[]{bpmPluginContext.getTitle(), e.getMessage(), e});
					throw new BusinessException(bpmPluginContext.getTitle(), BpmStatusCode.PLUGIN_ERROR,
							(Throwable) e);
				}
			}
		}
	}
}
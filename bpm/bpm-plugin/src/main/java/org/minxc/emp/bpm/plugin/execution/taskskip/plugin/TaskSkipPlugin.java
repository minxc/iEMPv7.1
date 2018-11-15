package org.minxc.emp.bpm.plugin.execution.taskskip.plugin;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.minxc.emp.basis.api.groovy.GroovyScriptEngine;
import org.minxc.emp.basis.api.model.SystemIdentity;
import org.minxc.emp.bpm.api.engine.action.cmd.TaskActionCmd;
import org.minxc.emp.bpm.api.engine.context.BpmnContext;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.minxc.emp.bpm.api.service.BpmnProcessDefinitionService;
import org.minxc.emp.bpm.engine.action.cmd.DefualtTaskActionCmd;
import org.minxc.emp.bpm.engine.constant.TaskSkipType;
import org.minxc.emp.bpm.engine.plugin.runtime.abstact.AbstractBpmnExecutionPlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmnExecutionPluginSession;
import org.minxc.emp.bpm.plugin.execution.taskskip.def.TaskSkipPluginDefinition;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.core.util.StringUtil;
import org.minxc.emp.system.util.ContextUtil;
import org.springframework.stereotype.Component;

@Component
public class TaskSkipPlugin extends AbstractBpmnExecutionPlugin<BpmnExecutionPluginSession, TaskSkipPluginDefinition> {
	@Resource
	GroovyScriptEngine g;
	@Resource
    BpmnProcessDefinitionService h;

	@Override
	public Void execute(BpmnExecutionPluginSession pluginSession, TaskSkipPluginDefinition pluginDef) {
		DefualtTaskActionCmd model = (DefualtTaskActionCmd) BpmnContext.getActionModel();
		TaskSkipType isSkip = this.b(pluginSession, pluginDef);
		model.setHasSkipThisTask(isSkip);
		return null;
	}

	private TaskSkipType b(BpmnExecutionPluginSession pluginSession, TaskSkipPluginDefinition pluginDef) {
		String[] skip;
		TaskActionCmd model = (TaskActionCmd) BpmnContext.getActionModel();
		TaskSkipType skipResult = TaskSkipType.NO_SKIP;
		if (StringUtil.isEmpty((String) pluginDef.getSkipTypeArr())) {
			return skipResult;
		}
		for (String typeStr : skip = pluginDef.getSkipTypeArr().split(",")) {
			TaskSkipType type = TaskSkipType.fromKey((String) typeStr);
			block0 : switch (type) {
				case ALL_SKIP : {
					skipResult = TaskSkipType.ALL_SKIP;
					break;
				}
				case SCRIPT_SKIP : {
					boolean isSkip = this.g.executeBoolean(pluginDef.getScript(), (Map) pluginSession);
					if (!isSkip)
						break;
					skipResult = TaskSkipType.SCRIPT_SKIP;
					break;
				}
				case SAME_USER_SKIP : {
					List identityList = model.getBpmIdentity(model.getBpmTask().getNodeId());
					if (BeanUtils.isEmpty((Object) identityList) || identityList.size() > 1) {
						return skipResult;
					}
					String userId = ContextUtil.getCurrentUserId();
					SystemIdentity identity = (SystemIdentity) identityList.get(0);
					if (!identity.getId().equals(userId))
						break;
					skipResult = TaskSkipType.SAME_USER_SKIP;
					break;
				}
				case USER_EMPTY_SKIP : {
					List identityList1 = model.getBpmIdentity(model.getBpmTask().getNodeId());
					if (!BeanUtils.isEmpty((Object) identityList1))
						break;
					skipResult = TaskSkipType.USER_EMPTY_SKIP;
					break;
				}
				case FIRSTNODE_SKIP : {
					List<BpmNodeDef> list = this.h.getStartNodes(model.getBpmTask().getDefId());
					for (BpmNodeDef def : list) {
						if (!def.getNodeId().equals(model.getBpmTask().getNodeId()))
							continue;
						skipResult = TaskSkipType.FIRSTNODE_SKIP;
						break block0;
					}
					break;
				}
			}
			if (skipResult == TaskSkipType.NO_SKIP)
				continue;
			this.LOG.info("{}节点【{}】设置了【{}】，将跳过当前任务",
					new Object[]{model.getBpmTask().getId(), model.getBpmTask().getName(), skipResult.getValue()});
			return skipResult;
		}
		return skipResult;
	}

//	public Object execute(Object object, Object object2) {
//		return this.a((BpmnExecutionPluginSession) object, (TaskSkipPluginDefinition) object2);
//	}

}
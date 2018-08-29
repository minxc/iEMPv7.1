package org.minxc.emp.bpm.plugin.execution.taskskip.plugin;

import com.minxc.emp.core.util.BeanUtils;
import com.dstz.base.core.util.StringUtil;
import org.minxc.emp.bpm.api.engine.action.cmd.TaskActionCmd;
import org.minxc.emp.bpm.api.engine.context.BpmContext;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.minxc.emp.bpm.api.model.task.IBpmTask;
import org.minxc.emp.bpm.api.service.BpmProcessDefService;
import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
import com.dstz.bpm.engine.constant.TaskSkipType;
import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractBpmExecutionPlugin;
import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;
import com.dstz.sys.api.groovy.IGroovyScriptEngine;
import com.dstz.sys.api.model.SysIdentity;
import org.minxc.emp.basis.impl.util.ContextUtil;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.minxc.emp.bpm.plugin.execution.taskskip.def.TaskSkipPluginDef;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class TaskSkipPlugin extends AbstractBpmExecutionPlugin<BpmExecutionPluginSession, TaskSkipPluginDef> {
	@Resource
	IGroovyScriptEngine g;
	@Resource
	BpmProcessDefService h;

	@Override
	public Void execute(BpmExecutionPluginSession pluginSession, TaskSkipPluginDef pluginDef) {
		DefualtTaskActionCmd model = (DefualtTaskActionCmd) BpmContext.getActionModel();
		TaskSkipType isSkip = this.b(pluginSession, pluginDef);
		model.setHasSkipThisTask(isSkip);
		return null;
	}

	private TaskSkipType b(BpmExecutionPluginSession pluginSession, TaskSkipPluginDef pluginDef) {
		String[] skip;
		TaskActionCmd model = (TaskActionCmd) BpmContext.getActionModel();
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
					SysIdentity identity = (SysIdentity) identityList.get(0);
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
//		return this.a((BpmExecutionPluginSession) object, (TaskSkipPluginDef) object2);
//	}

}
package org.minxc.emp.bpm.plugin.task.ruleskip.plugin;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.minxc.emp.basis.api.groovy.GroovyScriptEngine;
import org.minxc.emp.bpm.api.engine.action.cmd.TaskActionCmd;
import org.minxc.emp.bpm.api.engine.context.BpmContext;
import org.minxc.emp.bpm.engine.plugin.runtime.abstact.AbstractBpmTaskPlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmTaskPluginSession;
import org.minxc.emp.bpm.plugin.task.ruleskip.def.JumpRule;
import org.minxc.emp.bpm.plugin.task.ruleskip.def.RuleSkipPluginDef;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.core.util.StringUtil;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class RuleSkipPlugin extends AbstractBpmTaskPlugin<BpmTaskPluginSession, RuleSkipPluginDef> {
	@Resource
	private GroovyScriptEngine g;

	public Void a(BpmTaskPluginSession pluginSession, RuleSkipPluginDef pluginDef) {
		if (BeanUtils.isEmpty((Object) pluginDef.getJumpRules())) {
			return null;
		}
		TaskActionCmd taskAction = (TaskActionCmd) BpmContext.getActionModel();
		if (StringUtil.isNotEmpty((String) taskAction.getDestination())) {
			LOG.info("任务【{}】已经指定了跳转节点【{}】，规则跳转将忽略", (Object) pluginSession.getBpmTask().getName(),
					(Object) taskAction.getDestination());
			return null;
		}
		for (JumpRule jumpRule : pluginDef.getJumpRules()) {
			boolean isJump;
			if (StringUtil.isEmpty((String) jumpRule.getScript()) || StringUtil.isEmpty((String) jumpRule.getScript())
					|| !(isJump = this.g.executeBoolean(jumpRule.getScript(), (Map) pluginSession)))
				continue;
			taskAction.setDestination(jumpRule.getTargetNode());
			LOG.info("节点【{}】规则跳转【{}】条件满足，即将跳转至【{}】",
					new Object[]{pluginSession.getBpmTask().getName(), jumpRule.getName(), jumpRule.getTargetNode()});
			LOG.debug(jumpRule.getScript());
			return null;
		}
		LOG.info("节点{}规则跳转，共{}条，均不符合条件，将正常跳转", (Object) pluginSession.getBpmTask().getName(),
				(Object) pluginDef.getJumpRules().size());
		return null;
	}


	@Override
	public Void execute(BpmTaskPluginSession pluginSession, RuleSkipPluginDef pluginDef) {
		return this.a(pluginSession, pluginDef);
	}
}
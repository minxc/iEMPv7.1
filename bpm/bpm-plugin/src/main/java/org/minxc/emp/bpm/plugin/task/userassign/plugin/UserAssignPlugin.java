package org.minxc.emp.bpm.plugin.task.userassign.plugin;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.basis.api.groovy.IGroovyScriptEngine;
import org.minxc.emp.basis.api.model.SystemIdentity;
import org.minxc.emp.bpm.api.engine.action.cmd.TaskActionCmd;
import org.minxc.emp.bpm.api.engine.context.BpmContext;
import org.minxc.emp.bpm.api.engine.plugin.def.BpmTaskPluginDef;
import org.minxc.emp.bpm.engine.plugin.factory.BpmPluginSessionFactory;
import org.minxc.emp.bpm.engine.plugin.runtime.abstact.AbstractBpmTaskPlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmPluginSession;
import org.minxc.emp.bpm.engine.plugin.session.BpmTaskPluginSession;
import org.minxc.emp.bpm.engine.plugin.session.BpmUserCalcPluginSession;
import org.minxc.emp.bpm.plugin.task.userassign.def.UserAssignPluginDef;
import org.minxc.emp.bpm.plugin.task.userassign.plugin.UserAssignRuleCalc;
import org.minxc.emp.core.util.BeanUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class UserAssignPlugin extends AbstractBpmTaskPlugin<BpmTaskPluginSession, BpmTaskPluginDef> {
	@Resource
	IGroovyScriptEngine groovyScriptEngine;

	public Void execute(BpmTaskPluginSession pluginSession, BpmTaskPluginDef pluginDef) {
		UserAssignPluginDef assignPluginDef = (UserAssignPluginDef) pluginDef;
		TaskActionCmd model = (TaskActionCmd) BpmContext.getActionModel();
		List identityList = model.getBpmIdentity(model.getNodeId());
		if (BeanUtils.isNotEmpty((Object) identityList)) {
			return null;
		}
		List ruleList = assignPluginDef.getRuleList();
		if (BeanUtils.isEmpty((Object) ruleList)) {
			return null;
		}
		BpmUserCalcPluginSession bpmUserCalcPluginSession = BpmPluginSessionFactory
				.buildBpmUserCalcPluginSession((BpmPluginSession) pluginSession);
		List<SystemIdentity> bpmIdentities = UserAssignRuleCalc.a((BpmUserCalcPluginSession) bpmUserCalcPluginSession, (List) ruleList,
				(Boolean) false);
		ArrayList<SystemIdentity> identitieList = new ArrayList<SystemIdentity>();
		for (SystemIdentity identity : bpmIdentities) {
			if (identity == null)
				continue;
			identitieList.add(identity);
		}
		LOG.debug("用户计算插件执行完毕，解析到【{}】条有效人员信息。节点:{}", (Object) identitieList.size(), (Object) model.getNodeId());
		LOG.trace(JSON.toJSONString(identitieList));
		model.setBpmIdentity(model.getNodeId(), identitieList);
		return null;
	}

//	public Object execute(Object object, Object object2) {
//		return this.a((BpmTaskPluginSession) object, (BpmTaskPluginDef) object2);
//	}
}
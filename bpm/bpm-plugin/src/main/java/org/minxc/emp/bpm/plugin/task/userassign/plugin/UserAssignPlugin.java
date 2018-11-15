package org.minxc.emp.bpm.plugin.task.userassign.plugin;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.minxc.emp.basis.api.groovy.GroovyScriptEngine;
import org.minxc.emp.basis.api.model.SystemIdentity;
import org.minxc.emp.bpm.api.engine.action.cmd.TaskActionCmd;
import org.minxc.emp.bpm.api.engine.context.BpmnContext;
import org.minxc.emp.bpm.api.engine.plugin.def.BpmnTaskPluginDef;
import org.minxc.emp.bpm.engine.plugin.factory.BpmnPluginSessionFactory;
import org.minxc.emp.bpm.engine.plugin.runtime.abstact.AbstractBpmnTaskPlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmnPluginSession;
import org.minxc.emp.bpm.engine.plugin.session.BpmnTaskPluginSession;
import org.minxc.emp.bpm.engine.plugin.session.BpmnUserCalcPluginSession;
import org.minxc.emp.bpm.plugin.task.userassign.def.UserAssignPluginDefinition;
import org.minxc.emp.core.util.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserAssignPlugin extends AbstractBpmnTaskPlugin<BpmnTaskPluginSession, BpmnTaskPluginDef> {
	@Resource
	GroovyScriptEngine groovyScriptEngine;

	public Void execute(BpmnTaskPluginSession pluginSession, BpmnTaskPluginDef pluginDef) {
		UserAssignPluginDefinition assignPluginDef = (UserAssignPluginDefinition) pluginDef;
		TaskActionCmd model = (TaskActionCmd) BpmnContext.getActionModel();
		List identityList = model.getBpmIdentity(model.getNodeId());
		if (BeanUtils.isNotEmpty((Object) identityList)) {
			return null;
		}
		List ruleList = assignPluginDef.getRuleList();
		if (BeanUtils.isEmpty((Object) ruleList)) {
			return null;
		}
		BpmnUserCalcPluginSession bpmUserCalcPluginSession = BpmnPluginSessionFactory
				.buildBpmUserCalcPluginSession((BpmnPluginSession) pluginSession);
		List<SystemIdentity> bpmIdentities = UserAssignRuleCalc.a((BpmnUserCalcPluginSession) bpmUserCalcPluginSession, (List) ruleList,
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
//		return this.a((BpmnTaskPluginSession) object, (BpmnTaskPluginDef) object2);
//	}
}
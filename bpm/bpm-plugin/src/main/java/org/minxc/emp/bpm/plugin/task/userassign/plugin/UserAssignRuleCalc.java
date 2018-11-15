package org.minxc.emp.bpm.plugin.task.userassign.plugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

import org.minxc.emp.basis.api.groovy.GroovyScriptEngine;
import org.minxc.emp.basis.api.model.SystemIdentity;
import org.minxc.emp.bpm.api.constant.ExtractType;
import org.minxc.emp.bpm.api.engine.constant.LogicType;
import org.minxc.emp.bpm.api.engine.plugin.context.UserCalculatePluginContext;
import org.minxc.emp.bpm.api.engine.plugin.def.BpmnTaskPluginDef;
import org.minxc.emp.bpm.api.engine.plugin.def.BpmnUserCalculatePluginDefinition;
import org.minxc.emp.bpm.api.engine.plugin.def.UserAssignRule;
import org.minxc.emp.bpm.api.exception.BpmnStatusCode;
import org.minxc.emp.bpm.api.exception.WorkFlowException;
import org.minxc.emp.bpm.engine.plugin.runtime.abstact.AbstractUserCalculatePlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmnUserCalcPluginSession;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.util.AppContextUtil;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.core.util.StringUtil;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class UserAssignRuleCalc {

	public static List<SystemIdentity> a(BpmnUserCalcPluginSession bpmUserCalcPluginSession, List<UserAssignRule> ruleList,
                                         Boolean forceExtract) {
		ArrayList<SystemIdentity> bpmIdentities = new ArrayList<SystemIdentity>();
		Collections.sort(ruleList);
		for (UserAssignRule userRule : ruleList) {
			if (bpmIdentities.size() > 0)
				break;
			boolean isValid = UserAssignRuleCalc.a(userRule.getCondition(), bpmUserCalcPluginSession);
			if (!isValid)
				continue;
			List<UserCalculatePluginContext> calcList = userRule.getCalcPluginContextList();
			for (UserCalculatePluginContext context : calcList) {
				AbstractUserCalculatePlugin plugin = (AbstractUserCalculatePlugin) AppContextUtil
						.getBean((Class) context.getPluginClass());
				if (plugin == null) {
					throw new WorkFlowException("请检查该插件是否注入成功：" + context.getPluginClass(),
							BpmnStatusCode.PLUGIN_ERROR);
				}
				BpmnUserCalculatePluginDefinition pluginDef = (BpmnUserCalculatePluginDefinition) context.getBpmPluginDef();
				if (forceExtract.booleanValue()) {
					pluginDef.setExtract(ExtractType.EXACT_EXACT_USER);
				}
				List biList = plugin.execute(bpmUserCalcPluginSession, (BpmnTaskPluginDef) pluginDef);
				log.debug("执行用户计算插件【{}】，解析到【{}】条人员信息，插件计算逻辑：{}",
						new Object[]{context.getTitle(), biList.size(), pluginDef.getLogicCal()});
				if (BeanUtils.isEmpty((Object) biList))
					continue;
				if (BeanUtils.isEmpty(bpmIdentities)) {
					bpmIdentities.addAll(biList);
					continue;
				}
				UserAssignRuleCalc.a(bpmIdentities, biList, pluginDef.getLogicCal());
			}
		}
		return bpmIdentities;
	}

	private static void a(List<SystemIdentity> existBpmIdentities, List<SystemIdentity> newBpmIdentities, LogicType logic) {
		switch (logic) {
			case OR : {
				LinkedHashSet<SystemIdentity> set = new LinkedHashSet<SystemIdentity>();
				set.addAll(existBpmIdentities);
				set.addAll(newBpmIdentities);
				existBpmIdentities.clear();
				existBpmIdentities.addAll(set);
				break;
			}
			case AND : {
				ArrayList<SystemIdentity> rtnList = new ArrayList<SystemIdentity>();
				for (SystemIdentity identity : existBpmIdentities) {
					for (SystemIdentity tmp : newBpmIdentities) {
						if (!identity.equals((Object) tmp))
							continue;
						rtnList.add(identity);
					}
				}
				existBpmIdentities.clear();
				existBpmIdentities.addAll(rtnList);
				break;
			}
			default : {
				for (SystemIdentity tmp : newBpmIdentities) {
					existBpmIdentities.remove((Object) tmp);
				}
			}
		}
	}

	private static boolean a(String script, BpmnUserCalcPluginSession bpmUserCalcPluginSession) {
		if (StringUtil.isEmpty((String) script)) {
			return true;
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.putAll(bpmUserCalcPluginSession.getBoDatas());
		map.put("bpmTask", (Object) bpmUserCalcPluginSession.getBpmTask());
		map.put("bpmInstance", (Object) bpmUserCalcPluginSession.getBpmInstance());
		map.put("variableScope", (Object) bpmUserCalcPluginSession.getVariableScope());
		try {
			return ((GroovyScriptEngine) AppContextUtil.getBean(GroovyScriptEngine.class)).executeBoolean(script, map);
		} catch (Exception e) {
			log.error("人员前置脚本解析失败,脚本：{},可能原因为：{}", new Object[]{script, e.getMessage(), e});
			throw new BusinessException(BpmnStatusCode.PLUGIN_USERCALC_RULE_CONDITION_ERROR);
		}
	}

}
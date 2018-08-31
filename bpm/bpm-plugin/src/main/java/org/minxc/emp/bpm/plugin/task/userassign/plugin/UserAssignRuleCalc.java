package org.minxc.emp.bpm.plugin.task.userassign.plugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import org.activiti.engine.delegate.VariableScope;
import org.minxc.emp.basis.api.groovy.IGroovyScriptEngine;
import org.minxc.emp.basis.api.model.SysIdentity;
import org.minxc.emp.bpm.api.constant.ExtractType;
import org.minxc.emp.bpm.api.engine.constant.LogicType;
import org.minxc.emp.bpm.api.engine.plugin.context.UserCalcPluginContext;
import org.minxc.emp.bpm.api.engine.plugin.def.BpmTaskPluginDef;
import org.minxc.emp.bpm.api.engine.plugin.def.BpmUserCalcPluginDef;
import org.minxc.emp.bpm.api.engine.plugin.def.UserAssignRule;
import org.minxc.emp.bpm.api.exception.BpmStatusCode;
import org.minxc.emp.bpm.api.exception.WorkFlowException;
import org.minxc.emp.bpm.engine.plugin.runtime.abstact.AbstractBpmTaskPlugin;
import org.minxc.emp.bpm.engine.plugin.runtime.abstact.AbstractUserCalcPlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmUserCalcPluginSession;
import org.minxc.emp.core.api.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minxc.emp.core.util.AppContextUtil;
import com.minxc.emp.core.util.BeanUtils;
import com.minxc.emp.core.util.StringUtil;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class UserAssignRuleCalc {

	public static List<SysIdentity> a(BpmUserCalcPluginSession bpmUserCalcPluginSession, List<UserAssignRule> ruleList,
			Boolean forceExtract) {
		ArrayList<SysIdentity> bpmIdentities = new ArrayList<SysIdentity>();
		Collections.sort(ruleList);
		for (UserAssignRule userRule : ruleList) {
			if (bpmIdentities.size() > 0)
				break;
			boolean isValid = UserAssignRuleCalc.a(userRule.getCondition(), bpmUserCalcPluginSession);
			if (!isValid)
				continue;
			List<UserCalcPluginContext> calcList = userRule.getCalcPluginContextList();
			for (UserCalcPluginContext context : calcList) {
				AbstractUserCalcPlugin plugin = (AbstractUserCalcPlugin) AppContextUtil
						.getBean((Class) context.getPluginClass());
				if (plugin == null) {
					throw new WorkFlowException("请检查该插件是否注入成功：" + context.getPluginClass(),
							BpmStatusCode.PLUGIN_ERROR);
				}
				BpmUserCalcPluginDef pluginDef = (BpmUserCalcPluginDef) context.getBpmPluginDef();
				if (forceExtract.booleanValue()) {
					pluginDef.setExtract(ExtractType.EXACT_EXACT_USER);
				}
				List biList = plugin.execute(bpmUserCalcPluginSession, (BpmTaskPluginDef) pluginDef);
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

	private static void a(List<SysIdentity> existBpmIdentities, List<SysIdentity> newBpmIdentities, LogicType logic) {
		switch (logic) {
			case OR : {
				LinkedHashSet<SysIdentity> set = new LinkedHashSet<SysIdentity>();
				set.addAll(existBpmIdentities);
				set.addAll(newBpmIdentities);
				existBpmIdentities.clear();
				existBpmIdentities.addAll(set);
				break;
			}
			case AND : {
				ArrayList<SysIdentity> rtnList = new ArrayList<SysIdentity>();
				for (SysIdentity identity : existBpmIdentities) {
					for (SysIdentity tmp : newBpmIdentities) {
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
				for (SysIdentity tmp : newBpmIdentities) {
					existBpmIdentities.remove((Object) tmp);
				}
			}
		}
	}

	private static boolean a(String script, BpmUserCalcPluginSession bpmUserCalcPluginSession) {
		if (StringUtil.isEmpty((String) script)) {
			return true;
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.putAll(bpmUserCalcPluginSession.getBoDatas());
		map.put("bpmTask", (Object) bpmUserCalcPluginSession.getBpmTask());
		map.put("bpmInstance", (Object) bpmUserCalcPluginSession.getBpmInstance());
		map.put("variableScope", (Object) bpmUserCalcPluginSession.getVariableScope());
		try {
			return ((IGroovyScriptEngine) AppContextUtil.getBean(IGroovyScriptEngine.class)).executeBoolean(script, map);
		} catch (Exception e) {
			log.error("人员前置脚本解析失败,脚本：{},可能原因为：{}", new Object[]{script, e.getMessage(), e});
			throw new BusinessException(BpmStatusCode.PLUGIN_USERCALC_RULE_CONDITION_ERROR);
		}
	}

}
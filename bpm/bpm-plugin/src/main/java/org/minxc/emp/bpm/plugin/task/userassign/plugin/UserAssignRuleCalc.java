package org.minxc.emp.bpm.plugin.task.userassign.plugin;

import com.dstz.base.api.constant.IStatusCode;
import com.dstz.base.api.exception.BusinessException;
import com.dstz.base.core.util.AppUtil;
import com.dstz.base.core.util.BeanUtils;
import com.dstz.base.core.util.StringUtil;
import com.dstz.bpm.api.constant.ExtractType;
import com.dstz.bpm.api.engine.constant.LogicType;
import com.dstz.bpm.api.engine.plugin.context.UserCalcPluginContext;
import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
import com.dstz.bpm.api.engine.plugin.def.BpmTaskPluginDef;
import com.dstz.bpm.api.engine.plugin.def.BpmUserCalcPluginDef;
import com.dstz.bpm.api.engine.plugin.def.UserAssignRule;
import com.dstz.bpm.api.exception.BpmStatusCode;
import com.dstz.bpm.api.exception.WorkFlowException;
import com.dstz.bpm.api.model.inst.IBpmInstance;
import com.dstz.bpm.api.model.task.IBpmTask;
import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractBpmTaskPlugin;
import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractUserCalcPlugin;
import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
import com.dstz.sys.api.groovy.IGroovyScriptEngine;
import com.dstz.sys.api.model.SysIdentity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import org.activiti.engine.delegate.VariableScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserAssignRuleCalc {
	protected static final Logger LOG = LoggerFactory.getLogger(AbstractBpmTaskPlugin.class);

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
				AbstractUserCalcPlugin plugin = (AbstractUserCalcPlugin) AppUtil
						.getBean((Class) context.getPluginClass());
				if (plugin == null) {
					throw new WorkFlowException("请检查该插件是否注入成功：" + context.getPluginClass(),
							(IStatusCode) BpmStatusCode.PLUGIN_ERROR);
				}
				BpmUserCalcPluginDef pluginDef = (BpmUserCalcPluginDef) context.getBpmPluginDef();
				if (forceExtract.booleanValue()) {
					pluginDef.setExtract(ExtractType.EXACT_EXACT_USER);
				}
				List biList = plugin.execute(bpmUserCalcPluginSession, (BpmTaskPluginDef) pluginDef);
				LOG.debug("执行用户计算插件【{}】，解析到【{}】条人员信息，插件计算逻辑：{}",
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
			return ((IGroovyScriptEngine) AppUtil.getBean(IGroovyScriptEngine.class)).executeBoolean(script, map);
		} catch (Exception e) {
			LOG.error("人员前置脚本解析失败,脚本：{},可能原因为：{}", new Object[]{script, e.getMessage(), e});
			throw new BusinessException((IStatusCode) BpmStatusCode.PLUGIN_USERCALC_RULE_CONDITION_ERROR);
		}
	}

}
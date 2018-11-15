package org.minxc.emp.bpm.plugin.task.userassign.context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.engine.plugin.context.UserCalculatePluginContext;
import org.minxc.emp.bpm.api.engine.plugin.context.UserQueryPluginContext;
import org.minxc.emp.bpm.api.engine.plugin.def.UserAssignRule;
import org.minxc.emp.bpm.api.engine.plugin.runtime.RunTimePlugin;
import org.minxc.emp.bpm.engine.plugin.context.AbstractBpmnTaskPluginContext;
import org.minxc.emp.bpm.engine.plugin.context.AbstractUserCalculatePluginContext;
import org.minxc.emp.bpm.plugin.task.userassign.def.UserAssignPluginDefinition;
import org.minxc.emp.bpm.plugin.task.userassign.plugin.UserAssignPlugin;
import org.minxc.emp.core.util.AppContextUtil;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.core.util.JsonUtil;
import org.minxc.emp.core.util.ThreadMsgUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class UserAssignPluginContext extends AbstractBpmnTaskPluginContext<UserAssignPluginDefinition>
		implements
			UserQueryPluginContext {
	public Class getPluginClass() {
		return UserAssignPlugin.class;
	}

	public Class<? extends RunTimePlugin> getUserQueryPluginClass() {
		return UserAssignPlugin.class;
	}

	public List<EventType> getEventTypes() {
		ArrayList<EventType> eventTypes = new ArrayList<EventType>();
		eventTypes.add(EventType.TASK_CREATE_EVENT);
		return eventTypes;
	}

	public JSON getJson() {
		List ruleList = ((UserAssignPluginDefinition) this.getBpmPluginDef()).getRuleList();
		if (BeanUtils.isEmpty((Object) ruleList)) {
			return (JSON) JSON.parse((String) "[]");
		}
		return (JSON) JSON.toJSON((Object) ruleList);
	}

	protected UserAssignPluginDefinition parseFromJson(JSON pluginJson) {
		UserAssignPluginDefinition def = new UserAssignPluginDefinition();
		JSONArray userRuleList = null;
		if (pluginJson instanceof JSONObject) {
			JSONObject json = (JSONObject) pluginJson;
			if (!json.containsKey((Object) "ruleList")) {
				ThreadMsgUtil.addMsg((String) "人员条件不完整！");
				return def;
			}
			userRuleList = json.getJSONArray("ruleList");
		} else {
			userRuleList = (JSONArray) pluginJson;
		}
		ArrayList<UserAssignRule> ruleList = new ArrayList<UserAssignRule>();
		for (int i = 0; i < userRuleList.size(); ++i) {
			JSONObject ruleJson = userRuleList.getJSONObject(i);
			UserAssignRule rule = (UserAssignRule) JSON.toJavaObject((JSON) ruleJson, UserAssignRule.class);
			ruleList.add(rule);
			if (!ruleJson.containsKey((Object) "calcs")) {
				ThreadMsgUtil.addMsg((String) "人员条件不完整！");
				continue;
			}
			JSONArray calcAry = ruleJson.getJSONArray("calcs");
			ArrayList<UserCalculatePluginContext> calcPluginContextList = new ArrayList<UserCalculatePluginContext>();
			for (Object obj : calcAry) {
				JSONObject calcObj = (JSONObject) obj;
				String pluginContext = JsonUtil.getString((JSONObject) calcObj, (String) "pluginType")
						+ "PluginContext";
				AbstractUserCalculatePluginContext ctx = (AbstractUserCalculatePluginContext) AppContextUtil
						.getBean((String) pluginContext);
				if (ctx == null) {
					this.LOG.warn("插件{}查找失败！", (Object) pluginContext);
					continue;
				}
				ctx.parse((JSON) calcObj);
				calcPluginContextList.add(ctx);
			}
			rule.setCalcPluginContextList(calcPluginContextList);
		}
		def.setRuleList(ruleList);
		return def;
	}

	public String getTitle() {
		return "用户分配插件";
	}

//	protected BpmnPluginDef parseFromJson(JSON jSON) {
//		return this.g(jSON);
//	}
}
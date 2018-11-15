package org.minxc.emp.bpm.plugin.usercalc.group.context;

import com.alibaba.fastjson.JSONObject;

import org.minxc.emp.bpm.api.engine.plugin.def.BpmnUserCalculatePluginDefinition;
import org.minxc.emp.bpm.api.engine.plugin.runtime.RunTimePlugin;
import org.minxc.emp.bpm.engine.plugin.context.AbstractUserCalculatePluginContext;
import org.minxc.emp.bpm.plugin.usercalc.group.def.GroupPluginDefinition;
import org.minxc.emp.bpm.plugin.usercalc.group.runtime.GroupPlugin;
import org.minxc.emp.core.util.JsonUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class GroupPluginContext extends AbstractUserCalculatePluginContext {
	private static final long serialVersionUID = -6084686546165511275L;

	public String getDescription() {
		GroupPluginDefinition def = (GroupPluginDefinition) this.getBpmPluginDef();
		if (def == null) {
			return "";
		}
		return String.format("%s[%s]", def.getTypeName(), def.getGroupName());
	}

	public String getTitle() {
		return "组";
	}

	public Class<? extends RunTimePlugin> getPluginClass() {
		return GroupPlugin.class;
	}

	protected BpmnUserCalculatePluginDefinition parseJson(JSONObject pluginJson) {
		GroupPluginDefinition def = new GroupPluginDefinition();
		String groupType = JsonUtil.getString((JSONObject) pluginJson, (String) "type");
		String groupTypeName = JsonUtil.getString((JSONObject) pluginJson, (String) "typeName");
		def.setType(groupType);
		def.setTypeName(groupTypeName);
		String groupKey = JsonUtil.getString((JSONObject) pluginJson, (String) "groupKey");
		String groupName = JsonUtil.getString((JSONObject) pluginJson, (String) "groupName");
		def.setGroupKey(groupKey);
		def.setGroupName(groupName);
		return def;
	}
}
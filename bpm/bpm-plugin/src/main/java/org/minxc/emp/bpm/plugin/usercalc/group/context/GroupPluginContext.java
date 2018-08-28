package org.minxc.emp.bpm.plugin.usercalc.group.context;

import com.alibaba.fastjson.JSONObject;
import com.dstz.base.core.util.JsonUtil;
import com.dstz.bpm.api.engine.plugin.def.BpmUserCalcPluginDef;
import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
import com.dstz.bpm.engine.plugin.context.AbstractUserCalcPluginContext;

import org.minxc.emp.bpm.plugin.usercalc.group.def.GroupPluginDef;
import org.minxc.emp.bpm.plugin.usercalc.group.runtime.GroupPlugin;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class GroupPluginContext extends AbstractUserCalcPluginContext {
	private static final long serialVersionUID = -6084686546165511275L;

	public String getDescription() {
		GroupPluginDef def = (GroupPluginDef) this.getBpmPluginDef();
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

	protected BpmUserCalcPluginDef parseJson(JSONObject pluginJson) {
		GroupPluginDef def = new GroupPluginDef();
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
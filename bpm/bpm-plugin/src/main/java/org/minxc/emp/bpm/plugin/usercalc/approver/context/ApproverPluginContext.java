package org.minxc.emp.bpm.plugin.usercalc.approver.context;

import com.alibaba.fastjson.JSONObject;

import org.minxc.emp.bpm.api.engine.plugin.runtime.RunTimePlugin;
import org.minxc.emp.bpm.engine.plugin.context.AbstractUserCalculatePluginContext;
import org.minxc.emp.bpm.plugin.usercalc.approver.def.ApproverPluginDefinition;
import org.minxc.emp.bpm.plugin.usercalc.approver.runtime.ApproverPlugin;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class ApproverPluginContext extends AbstractUserCalculatePluginContext<ApproverPluginDefinition> {
	private static final long serialVersionUID = 2164348894650802414L;

	public String getDescription() {
		return "流程历史审批人";
	}

	public String getTitle() {
		return "流程历史审批人";
	}

	public Class<? extends RunTimePlugin> getPluginClass() {
		return ApproverPlugin.class;
	}

	protected ApproverPluginDefinition parseJson(JSONObject pluginJson) {
		ApproverPluginDefinition def = new ApproverPluginDefinition();
		return def;
	}

//	protected BpmnUserCalculatePluginDefinition parseJson(JSONObject jSONObject) {
//		return this.a(jSONObject);
//	}
}
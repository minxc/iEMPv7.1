package org.minxc.emp.bpm.plugin.usercalc.approver.context;

import com.alibaba.fastjson.JSONObject;
import com.dstz.bpm.api.engine.plugin.def.BpmUserCalcPluginDef;
import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
import com.dstz.bpm.engine.plugin.context.AbstractUserCalcPluginContext;

import org.minxc.emp.bpm.plugin.usercalc.approver.def.ApproverPluginDef;
import org.minxc.emp.bpm.plugin.usercalc.approver.runtime.ApproverPlugin;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class ApproverPluginContext extends AbstractUserCalcPluginContext<ApproverPluginDef> {
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

	protected ApproverPluginDef parseJson(JSONObject pluginJson) {
		ApproverPluginDef def = new ApproverPluginDef();
		return def;
	}

//	protected BpmUserCalcPluginDef parseJson(JSONObject jSONObject) {
//		return this.a(jSONObject);
//	}
}
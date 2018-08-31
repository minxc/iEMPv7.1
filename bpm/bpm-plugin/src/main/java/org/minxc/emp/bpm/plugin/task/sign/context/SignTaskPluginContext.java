package org.minxc.emp.bpm.plugin.task.sign.context;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.engine.plugin.runtime.RunTimePlugin;
import org.minxc.emp.bpm.engine.plugin.context.AbstractBpmTaskPluginContext;
import org.minxc.emp.bpm.plugin.task.sign.def.SignTaskPluginDef;
import org.minxc.emp.bpm.plugin.task.sign.plugin.SignTaskPlugin;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class SignTaskPluginContext extends AbstractBpmTaskPluginContext<SignTaskPluginDef> {
	private static final long serialVersionUID = 8784633971785686365L;

	public List getEventTypes() {
		ArrayList<EventType> eventTypes = new ArrayList<EventType>();
		eventTypes.add(EventType.TASK_CREATE_EVENT);
		return eventTypes;
	}

	public Class<? extends RunTimePlugin> getPluginClass() {
		return SignTaskPlugin.class;
	}

	public String getTitle() {
		return "会签任务插件";
	}

	protected SignTaskPluginDef parseFromJson(JSON json) {
		SignTaskPluginDef def = (SignTaskPluginDef) JSON.parseObject((String) json.toJSONString(),
				SignTaskPluginDef.class);
		return def;
	}

//	protected BpmPluginDef parseFromJson(JSON jSON) {
//		return this.f(jSON);
//	}
}
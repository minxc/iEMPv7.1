package org.minxc.emp.bpm.plugin.task.sign.context;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.engine.plugin.runtime.RunTimePlugin;
import org.minxc.emp.bpm.engine.plugin.context.AbstractBpmnTaskPluginContext;
import org.minxc.emp.bpm.plugin.task.sign.def.SignTaskPluginDefinition;
import org.minxc.emp.bpm.plugin.task.sign.plugin.SignTaskPlugin;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class SignTaskPluginContext extends AbstractBpmnTaskPluginContext<SignTaskPluginDefinition> {
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

	protected SignTaskPluginDefinition parseFromJson(JSON json) {
		SignTaskPluginDefinition def = (SignTaskPluginDefinition) JSON.parseObject((String) json.toJSONString(),
				SignTaskPluginDefinition.class);
		return def;
	}

//	protected BpmnPluginDef parseFromJson(JSON jSON) {
//		return this.f(jSON);
//	}
}
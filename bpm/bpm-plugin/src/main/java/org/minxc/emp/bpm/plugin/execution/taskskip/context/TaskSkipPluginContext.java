package org.minxc.emp.bpm.plugin.execution.taskskip.context;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.engine.plugin.runtime.RunTimePlugin;
import org.minxc.emp.bpm.engine.plugin.context.AbstractBpmnExecutionPluginContext;
import org.minxc.emp.bpm.plugin.execution.taskskip.def.TaskSkipPluginDefinition;
import org.minxc.emp.bpm.plugin.execution.taskskip.plugin.TaskSkipPlugin;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class TaskSkipPluginContext extends AbstractBpmnExecutionPluginContext<TaskSkipPluginDefinition> {
	private static final long serialVersionUID = -8171025388788811778L;

	public List<EventType> getEventTypes() {
		ArrayList<EventType> list = new ArrayList<EventType>();
		list.add(EventType.TASK_POST_CREATE_EVENT);
		return list;
	}

	public Class<? extends RunTimePlugin> getPluginClass() {
		return TaskSkipPlugin.class;
	}

	protected TaskSkipPluginDefinition parseFromJson(JSON pluginJson) {
		TaskSkipPluginDefinition def = (TaskSkipPluginDefinition) JSON.toJavaObject((JSON) pluginJson, TaskSkipPluginDefinition.class);
		return def;
	}

	public String getTitle() {
		return "任务跳过";
	}

//	protected BpmnPluginDef parseFromJson(JSON jSON) {
//		return this.c(jSON);
//	}
}
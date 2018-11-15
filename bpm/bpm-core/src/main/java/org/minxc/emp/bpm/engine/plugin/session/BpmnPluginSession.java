package org.minxc.emp.bpm.engine.plugin.session;

import java.util.Map;
import org.activiti.engine.delegate.VariableScope;
import org.minxc.emp.biz.api.model.BusinessData;
import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.model.inst.BpmnInstance;

public interface BpmnPluginSession extends Map<String, Object> {
	public BpmnInstance getBpmInstance();

	public Map<String, BusinessData> getBoDatas();

	public VariableScope getVariableScope();

	public EventType getEventType();
}
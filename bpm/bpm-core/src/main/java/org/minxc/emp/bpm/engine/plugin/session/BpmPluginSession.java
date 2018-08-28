package org.minxc.emp.bpm.engine.plugin.session;

import com.dstz.bus.api.model.IBusinessData;
import java.util.Map;
import org.activiti.engine.delegate.VariableScope;
import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.model.inst.IBpmInstance;

public interface BpmPluginSession extends Map<String, Object> {
	public IBpmInstance getBpmInstance();

	public Map<String, IBusinessData> getBoDatas();

	public VariableScope getVariableScope();

	public EventType getEventType();
}
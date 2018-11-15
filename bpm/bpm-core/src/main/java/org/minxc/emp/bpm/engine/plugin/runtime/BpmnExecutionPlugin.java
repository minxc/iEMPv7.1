package org.minxc.emp.bpm.engine.plugin.runtime;

import org.minxc.emp.bpm.api.engine.plugin.def.BpmnExecutionPluginDef;
import org.minxc.emp.bpm.api.engine.plugin.runtime.RunTimePlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmnExecutionPluginSession;

public interface BpmnExecutionPlugin<S extends BpmnExecutionPluginSession, M extends BpmnExecutionPluginDef>
		extends
			RunTimePlugin<S, M, Void> {
}
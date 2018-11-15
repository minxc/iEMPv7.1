package org.minxc.emp.bpm.engine.plugin.runtime;

import org.minxc.emp.bpm.api.engine.plugin.def.BpmnTaskPluginDef;
import org.minxc.emp.bpm.api.engine.plugin.runtime.RunTimePlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmnTaskPluginSession;

public interface BpmnTaskPlugin<S extends BpmnTaskPluginSession, M extends BpmnTaskPluginDef>
		extends
			RunTimePlugin<S, M, Void> {
}
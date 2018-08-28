package org.minxc.emp.bpm.engine.plugin.runtime;

import org.minxc.emp.bpm.api.engine.plugin.def.BpmExecutionPluginDef;
import org.minxc.emp.bpm.api.engine.plugin.runtime.RunTimePlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmExecutionPluginSession;

public interface BpmExecutionPlugin<S extends BpmExecutionPluginSession, M extends BpmExecutionPluginDef>
		extends
			RunTimePlugin<S, M, Void> {
}
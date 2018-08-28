package org.minxc.emp.bpm.engine.plugin.runtime;

import org.minxc.emp.bpm.api.engine.plugin.def.BpmTaskPluginDef;
import org.minxc.emp.bpm.api.engine.plugin.runtime.RunTimePlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmTaskPluginSession;

public interface BpmTaskPlugin<S extends BpmTaskPluginSession, M extends BpmTaskPluginDef>
		extends
			RunTimePlugin<S, M, Void> {
}
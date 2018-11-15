package org.minxc.emp.bpm.engine.plugin.runtime;

import java.util.List;

import org.minxc.emp.basis.api.model.SystemIdentity;
import org.minxc.emp.bpm.api.engine.plugin.def.BpmnPluginDef;
import org.minxc.emp.bpm.api.engine.plugin.runtime.RunTimePlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmnUserCalcPluginSession;

public interface BpmnUserCalculatePlugin<M extends BpmnPluginDef>
		extends
			RunTimePlugin<BpmnUserCalcPluginSession, M, List<SystemIdentity>> {
}
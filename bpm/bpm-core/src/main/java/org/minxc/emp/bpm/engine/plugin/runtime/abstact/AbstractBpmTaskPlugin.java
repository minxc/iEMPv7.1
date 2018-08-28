package org.minxc.emp.bpm.engine.plugin.runtime.abstact;

import org.minxc.emp.bpm.api.engine.plugin.def.BpmTaskPluginDef;
import org.minxc.emp.bpm.engine.plugin.runtime.BpmTaskPlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmTaskPluginSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractBpmTaskPlugin<S extends BpmTaskPluginSession, M extends BpmTaskPluginDef>
		implements
			BpmTaskPlugin<S, M> {
	protected static final Logger LOG = LoggerFactory.getLogger(AbstractBpmTaskPlugin.class);
}
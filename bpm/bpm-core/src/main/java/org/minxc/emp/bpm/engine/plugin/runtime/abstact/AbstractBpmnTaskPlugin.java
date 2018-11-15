package org.minxc.emp.bpm.engine.plugin.runtime.abstact;

import org.minxc.emp.bpm.api.engine.plugin.def.BpmnTaskPluginDef;
import org.minxc.emp.bpm.engine.plugin.runtime.BpmnTaskPlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmnTaskPluginSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractBpmnTaskPlugin<S extends BpmnTaskPluginSession, M extends BpmnTaskPluginDef>
		implements
        BpmnTaskPlugin<S, M> {
	protected static final Logger LOG = LoggerFactory.getLogger(AbstractBpmnTaskPlugin.class);
}
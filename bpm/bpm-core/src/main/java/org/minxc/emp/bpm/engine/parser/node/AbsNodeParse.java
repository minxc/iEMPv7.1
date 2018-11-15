package org.minxc.emp.bpm.engine.parser.node;

import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.minxc.emp.bpm.api.model.nodedef.impl.BaseBpmnNodeDefinition;
import org.minxc.emp.bpm.engine.parser.BaseBpmnDefParser;

public abstract class AbsNodeParse<T> extends BaseBpmnDefParser<T, BaseBpmnNodeDefinition> {
	public boolean a(BpmNodeDef nodeDef) {
		return true;
	}
}
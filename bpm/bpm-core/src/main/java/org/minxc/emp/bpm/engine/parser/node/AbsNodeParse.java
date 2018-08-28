package org.minxc.emp.bpm.engine.parser.node;

import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.minxc.emp.bpm.api.model.nodedef.impl.BaseBpmNodeDef;
import org.minxc.emp.bpm.engine.parser.BaseBpmDefParser;

public abstract class AbsNodeParse<T> extends BaseBpmDefParser<T, BaseBpmNodeDef> {
	public boolean a(BpmNodeDef nodeDef) {
		return true;
	}
}
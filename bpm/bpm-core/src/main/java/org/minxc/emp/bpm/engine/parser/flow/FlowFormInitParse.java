package org.minxc.emp.bpm.engine.parser.flow;

import java.util.List;

import org.minxc.emp.bpm.api.engine.plugin.def.BpmDef;
import org.minxc.emp.bpm.api.model.def.NodeInit;
import org.minxc.emp.bpm.engine.model.DefaultBpmProcessDef;
import org.minxc.emp.bpm.engine.parser.flow.AbsFlowParse;
import org.springframework.stereotype.Component;

@Component
public class FlowFormInitParse extends AbsFlowParse<NodeInit> {
	public String getKey() {
		return "nodeInitList";
	}

	public void setDefParam(DefaultBpmProcessDef bpmProcessDef, Object object) {
		List list = (List) object;
		bpmProcessDef.setNodeInitList(list);
	}

	public boolean isArray() {
		return true;
	}

}
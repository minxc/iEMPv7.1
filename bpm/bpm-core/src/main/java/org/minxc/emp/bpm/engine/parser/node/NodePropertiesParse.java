package org.minxc.emp.bpm.engine.parser.node;

import org.minxc.emp.bpm.api.engine.plugin.def.BpmDef;
import org.minxc.emp.bpm.api.model.def.NodeProperties;
import org.minxc.emp.bpm.api.model.nodedef.impl.BaseBpmNodeDef;
import org.minxc.emp.bpm.engine.parser.node.AbsNodeParse;
import org.springframework.stereotype.Component;

@Component
public class NodePropertiesParse extends AbsNodeParse<NodeProperties> {
	public String getKey() {
		return "propertie";
	}

	public void setDefParam(BaseBpmNodeDef userNodeDef, Object object) {
		NodeProperties prop = (NodeProperties) object;
		userNodeDef.setNodeProperties(prop);
	}

}
package org.minxc.emp.bpm.engine.parser.flow;

import java.util.HashSet;
import java.util.List;

import org.minxc.emp.bpm.api.model.def.BpmVariableDef;
import org.minxc.emp.bpm.engine.model.DefaultBpmnProcessDefinition;
import org.minxc.emp.bpm.engine.model.DefaultBpmnVariableDef;
import org.springframework.stereotype.Component;

@Component
public class FlowVariablesParse extends AbsFlowParse<DefaultBpmnVariableDef> {
	public String getKey() {
		return "variableList";
	}

	public String validate(Object object) {
		List<BpmVariableDef> varList = (List) object;
		HashSet<String> keys = new HashSet<String>();
		for (BpmVariableDef def : varList) {
			String key = def.getKey();
			if (keys.contains(key)) {
				throw new RuntimeException("解析流程变量出错：" + key + "在流程中重复！");
			}
			keys.add(def.getKey());
		}
		return "";
	}
	@Override
	public void setDefParam(DefaultBpmnProcessDefinition bpmProcessDef, Object object) {
		List varList = (List) object;
		bpmProcessDef.setVarList(varList);
	}

	public boolean isArray() {
		return true;
	}

}
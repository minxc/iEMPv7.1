package org.minxc.emp.bpm.engine.parser.node;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

import org.minxc.emp.bpm.api.model.nodedef.Button;
import org.minxc.emp.bpm.api.model.nodedef.impl.BaseBpmnNodeDefinition;
import org.minxc.emp.core.util.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ButtonsParse extends AbsNodeParse<Button> {
	public String getKey() {
		return "btnList";
	}
	@Override
	public void setDefParam(BaseBpmnNodeDefinition userNodeDef, Object object) {
		List btnList = (List) object;
		userNodeDef.setButtons(btnList);
	}

	public boolean isArray() {
		return true;
	}

	public void JSONAmend(BaseBpmnNodeDefinition userNodeDef, Object object, JSON thisJson) {
		JSONObject jsonConfig = (JSONObject) thisJson;
		if (BeanUtils.isEmpty((Object) object)) {
			jsonConfig.put("btnList", JSON.toJSON((Object) userNodeDef.getButtons()));
		}
	}

//	public void JSONAmend(BpmDef bpmDef, Object object, JSON jSON) {
//		this.a((BaseBpmnNodeDefinition) bpmDef, object, jSON);
//	}

}
package org.minxc.emp.bpm.engine.parser.node;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dstz.base.core.util.BeanUtils;

import java.util.List;

import org.minxc.emp.bpm.api.engine.plugin.def.BpmDef;
import org.minxc.emp.bpm.api.model.nodedef.Button;
import org.minxc.emp.bpm.api.model.nodedef.impl.BaseBpmNodeDef;
import org.minxc.emp.bpm.engine.parser.node.AbsNodeParse;
import org.springframework.stereotype.Component;

@Component
public class ButtonsParse extends AbsNodeParse<Button> {
	public String getKey() {
		return "btnList";
	}
	@Override
	public void setDefParam(BaseBpmNodeDef userNodeDef, Object object) {
		List btnList = (List) object;
		userNodeDef.setButtons(btnList);
	}

	public boolean isArray() {
		return true;
	}

	public void JSONAmend(BaseBpmNodeDef userNodeDef, Object object, JSON thisJson) {
		JSONObject jsonConfig = (JSONObject) thisJson;
		if (BeanUtils.isEmpty((Object) object)) {
			jsonConfig.put("btnList", JSON.toJSON((Object) userNodeDef.getButtons()));
		}
	}

//	public void JSONAmend(BpmDef bpmDef, Object object, JSON jSON) {
//		this.a((BaseBpmNodeDef) bpmDef, object, jSON);
//	}

}
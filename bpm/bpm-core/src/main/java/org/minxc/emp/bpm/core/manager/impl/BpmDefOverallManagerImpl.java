package org.minxc.emp.bpm.core.manager.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.minxc.emp.bpm.core.manager.BpmDefOverallManager;
import org.minxc.emp.bpm.core.manager.BpmDefinitionManager;
import org.minxc.emp.bpm.core.model.BpmDefinition;
import org.minxc.emp.bpm.core.model.BpmOverallView;
import org.springframework.stereotype.Service;

@Service
public class BpmDefOverallManagerImpl implements BpmDefOverallManager {
	@Resource
	private BpmDefinitionManager c;

	public BpmOverallView getBpmOverallView(String defId) {
		BpmDefinition def = (BpmDefinition) this.c.get(defId);
		BpmOverallView overallView = new BpmOverallView();
		overallView.setDefId(def.getId());
		overallView.setBpmDefinition(def);
		overallView.setDefSetting(JSON.parseObject((String) def.getDefSetting()));
		return overallView;
	}

	public void saveBpmOverallView(BpmOverallView overAllView) {
	}

	public Map<String, List<BpmOverallView>> importPreview(String flowXml) throws Exception {
		return null;
	}

	public void importSave(List<BpmOverallView> overAllView) {
	}
}
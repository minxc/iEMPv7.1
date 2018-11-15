package org.minxc.emp.bpm.core.manager.impl;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.minxc.emp.bpm.core.manager.BpmnDefOverallManager;
import org.minxc.emp.bpm.core.manager.BpmnDefinitionManager;
import org.minxc.emp.bpm.core.model.BpmnDefinitionImpl;
import org.minxc.emp.bpm.core.model.BpmnOverallView;
import org.springframework.stereotype.Service;

@Service
public class BpmnDefOverallManagerImpl implements BpmnDefOverallManager {
	@Resource
	private BpmnDefinitionManager c;

	public BpmnOverallView getBpmOverallView(String defId) {
		BpmnDefinitionImpl def = (BpmnDefinitionImpl) this.c.get(defId);
		BpmnOverallView overallView = new BpmnOverallView();
		overallView.setDefId(def.getId());
		overallView.setBpmDefinition(def);
		overallView.setDefSetting(JSON.parseObject((String) def.getDefSetting()));
		return overallView;
	}

	public void saveBpmOverallView(BpmnOverallView overAllView) {
	}

	public Map<String, List<BpmnOverallView>> importPreview(String flowXml) throws Exception {
		return null;
	}

	public void importSave(List<BpmnOverallView> overAllView) {
	}
}
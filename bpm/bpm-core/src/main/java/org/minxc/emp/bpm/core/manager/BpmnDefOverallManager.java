package org.minxc.emp.bpm.core.manager;

import java.util.List;
import java.util.Map;

import org.minxc.emp.bpm.core.model.BpmnOverallView;

public interface BpmnDefOverallManager {
	public BpmnOverallView getBpmOverallView(String var1);

	public void saveBpmOverallView(BpmnOverallView var1);

	public Map<String, List<BpmnOverallView>> importPreview(String var1) throws Exception;

	public void importSave(List<BpmnOverallView> var1);
}
package org.minxc.emp.bpm.core.manager;

import java.util.List;
import java.util.Map;

import org.minxc.emp.bpm.core.model.BpmOverallView;

public interface BpmDefOverallManager {
	public BpmOverallView getBpmOverallView(String var1);

	public void saveBpmOverallView(BpmOverallView var1);

	public Map<String, List<BpmOverallView>> importPreview(String var1) throws Exception;

	public void importSave(List<BpmOverallView> var1);
}
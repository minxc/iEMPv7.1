package org.minxc.emp.bpm.core.manager;


import java.util.List;
import org.activiti.engine.repository.Model;
import org.minxc.emp.bpm.core.model.BpmnDefinitionImpl;
import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.core.api.query.QueryFilter;
import org.springframework.util.MultiValueMap;

public interface BpmnDefinitionManager extends Manager<String, BpmnDefinitionImpl> {
	public void updateBpmnModel(Model model, MultiValueMap<String, String> var2) throws Exception;

	public BpmnDefinitionImpl getMainDefByActModelId(String actModelId);

	public BpmnDefinitionImpl getDefinitionByActDefId(String actDefId);

	public BpmnDefinitionImpl getByKey(String key);

	public List<BpmnDefinitionImpl> getMyDefinitionList(String var1, QueryFilter queryFilter);
}
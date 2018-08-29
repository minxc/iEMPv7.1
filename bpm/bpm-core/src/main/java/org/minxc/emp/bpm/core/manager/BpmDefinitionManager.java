package org.minxc.emp.bpm.core.manager;


import java.util.List;
import org.activiti.engine.repository.Model;
import org.minxc.emp.bpm.core.model.BpmDefinition;
import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.core.api.query.QueryFilter;
import org.springframework.util.MultiValueMap;

public interface BpmDefinitionManager extends Manager<String, BpmDefinition> {
	public void updateBpmnModel(Model model, MultiValueMap<String, String> var2) throws Exception;

	public BpmDefinition getMainDefByActModelId(String actModelId);

	public BpmDefinition getDefinitionByActDefId(String actDefId);

	public BpmDefinition getByKey(String key);

	public List<BpmDefinition> getMyDefinitionList(String var1, QueryFilter queryFilter);
}
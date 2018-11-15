package org.minxc.emp.bpm.core.listener;

import javax.annotation.Resource;

import org.minxc.emp.bpm.act.cache.ActivitiDefCache;
import org.minxc.emp.bpm.api.engine.event.BpmnDefinitionUpdateEvent;
import org.minxc.emp.bpm.api.service.BpmnProcessDefinitionService;
import org.minxc.emp.bpm.core.model.BpmnDefinitionImpl;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class BpmnDefinitionUpdateEventListener implements ApplicationListener<BpmnDefinitionUpdateEvent> {
	@Resource
	private BpmnProcessDefinitionService bpmnProcessDefinitionService;
	
	@Override
	public void onApplicationEvent(BpmnDefinitionUpdateEvent event) {
		BpmnDefinitionImpl bpmDef = (BpmnDefinitionImpl) event.getSource();
		this.bpmnProcessDefinitionService.clean(bpmDef.getId());
		ActivitiDefCache.clearByDefId((String) bpmDef.getActDefId());
	}
}
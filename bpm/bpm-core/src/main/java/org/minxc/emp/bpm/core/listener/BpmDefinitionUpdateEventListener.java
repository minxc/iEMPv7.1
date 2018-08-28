package org.minxc.emp.bpm.core.listener;

import javax.annotation.Resource;

import org.minxc.emp.bpm.act.cache.ActivitiDefCache;
import org.minxc.emp.bpm.api.engine.event.BpmDefinitionUpdateEvent;
import org.minxc.emp.bpm.api.service.BpmProcessDefService;
import org.minxc.emp.bpm.core.model.BpmDefinition;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class BpmDefinitionUpdateEventListener implements ApplicationListener<BpmDefinitionUpdateEvent> {
	@Resource
	private BpmProcessDefService bpmProcessDefService;
	
	@Override
	public void onApplicationEvent(BpmDefinitionUpdateEvent event) {
		BpmDefinition bpmDef = (BpmDefinition) event.getSource();
		this.bpmProcessDefService.clean(bpmDef.getId());
		ActivitiDefCache.clearByDefId((String) bpmDef.getActDefId());
	}
}
package org.minxc.emp.bpm.api.engine.event;

import org.minxc.emp.bpm.api.model.def.BpmnDefinition;
import org.springframework.context.ApplicationEvent;

public class BpmnDefinitionUpdateEvent extends ApplicationEvent {
    private static final long serialVersionUID = 550560932524738231L;

    public BpmnDefinitionUpdateEvent(BpmnDefinition source) {
        super(source);

    }
}

package org.minxc.emp.bpm.api.engine.data.result;

import org.minxc.emp.bpm.api.model.inst.BpmnInstance;


public class BpmnFlowInstanceData extends BpmnFlowData {
    private BpmnInstance instance;

    public BpmnInstance getInstance() {
        return instance;
    }

    public void setInstance(BpmnInstance instance) {
        this.instance = instance;
        this.defId = instance.getDefId();
    }

}

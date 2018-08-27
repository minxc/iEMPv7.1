package org.minxc.emp.bpm.api.engine.data.result;

import org.minxc.emp.bpm.api.model.inst.BpmInstance;


public class BpmnFlowInstanceData extends BpmnFlowData {
    private BpmInstance instance;

    public BpmInstance getInstance() {
        return instance;
    }

    public void setInstance(BpmInstance instance) {
        this.instance = instance;
        this.defId = instance.getDefId();
    }

}

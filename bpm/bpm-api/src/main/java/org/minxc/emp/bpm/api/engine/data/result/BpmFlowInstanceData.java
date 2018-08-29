package org.minxc.emp.bpm.api.engine.data.result;

import org.minxc.emp.bpm.api.model.inst.IBpmInstance;


public class BpmFlowInstanceData extends BpmFlowData {
    private IBpmInstance instance;

    public IBpmInstance getInstance() {
        return instance;
    }

    public void setInstance(IBpmInstance instance) {
        this.instance = instance;
        this.defId = instance.getDefId();
    }

}

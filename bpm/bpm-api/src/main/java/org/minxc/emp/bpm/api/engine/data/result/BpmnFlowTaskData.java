package org.minxc.emp.bpm.api.engine.data.result;

import org.minxc.emp.bpm.api.model.task.BpmnTask;

public class BpmnFlowTaskData extends BpmnFlowData {
    private BpmnTask task;

    public BpmnTask getTask() {
        return task;
    }

    public void setTask(BpmnTask task) {
        this.task = task;
        this.defId = task.getDefId();
    }

}

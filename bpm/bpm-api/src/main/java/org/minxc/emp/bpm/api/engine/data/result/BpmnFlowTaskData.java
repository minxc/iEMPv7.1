package org.minxc.emp.bpm.api.engine.data.result;

import org.minxc.emp.bpm.api.model.task.BpmTask;

public class BpmnFlowTaskData extends BpmnFlowData {
    private BpmTask task;

    public BpmTask getTask() {
        return task;
    }

    public void setTask(BpmTask task) {
        this.task = task;
        this.defId = task.getDefId();
    }

}

package org.minxc.emp.bpm.api.engine.data.result;

import org.minxc.emp.bpm.api.model.task.IBpmTask;

public class BpmFlowTaskData extends BpmFlowData {
    private IBpmTask task;

    public IBpmTask getTask() {
        return task;
    }

    public void setTask(IBpmTask task) {
        this.task = task;
        this.defId = task.getDefId();
    }

}

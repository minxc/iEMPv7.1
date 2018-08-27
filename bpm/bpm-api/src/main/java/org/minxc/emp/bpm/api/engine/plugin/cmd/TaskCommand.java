package org.minxc.emp.bpm.api.engine.plugin.cmd;

import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.engine.action.cmd.TaskActionCmd;

public interface TaskCommand {
    public void execute(EventType eventType, TaskActionCmd model);
}

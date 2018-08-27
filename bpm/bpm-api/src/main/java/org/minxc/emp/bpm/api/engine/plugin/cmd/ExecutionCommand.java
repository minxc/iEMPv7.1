package org.minxc.emp.bpm.api.engine.plugin.cmd;

import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.engine.action.cmd.InstanceActionCmd;

public interface ExecutionCommand {

    public void execute(EventType eventType, InstanceActionCmd actionModel);
}

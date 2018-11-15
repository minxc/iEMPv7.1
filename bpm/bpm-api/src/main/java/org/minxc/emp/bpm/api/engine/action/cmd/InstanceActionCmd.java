package org.minxc.emp.bpm.api.engine.action.cmd;

import org.minxc.emp.bpm.api.model.inst.BpmnInstance;


/**
 * 流程实例对象命令对象
 */
public interface InstanceActionCmd extends ActionCmd {

    public String getSubject();

    public String getBusinessKey();

    public String getInstanceId();

    public BpmnInstance getBpmInstance();

}

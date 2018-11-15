package org.minxc.emp.bpm.api.model.nodedef.impl;

/**
 * 外部子流程扩展。
 */
public class CallActivityNodeDefinition extends BaseBpmnNodeDefinition {
	private static final long serialVersionUID = -7321180599360290218L;
	/**
     * 子流程流程定义KEY
     */
    private String flowKey = "";
    private String flowName = "";

    public String getFlowKey() {
        return flowKey;
    }

    public void setFlowKey(String flowKey) {
        this.flowKey = flowKey;
    }

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

}

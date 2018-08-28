package org.minxc.emp.bpm.engine.action.cmd;

import com.alibaba.fastjson.JSONObject;
import com.dstz.base.api.constant.IStatusCode;

import java.util.Map;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.minxc.emp.bpm.api.engine.action.cmd.BaseActionCmd;
import org.minxc.emp.bpm.api.engine.action.cmd.InstanceActionCmd;
import org.minxc.emp.bpm.api.exception.BpmStatusCode;
import org.minxc.emp.bpm.api.exception.WorkFlowException;
import org.minxc.emp.bpm.api.model.def.IBpmDefinition;
import org.minxc.emp.bpm.api.model.inst.IBpmInstance;

public class DefaultInstanceActionCmd extends BaseActionCmd implements InstanceActionCmd {
	protected ExecutionEntity an;

	public DefaultInstanceActionCmd(String flowParam) {
		super(flowParam);
	}

	public DefaultInstanceActionCmd() {
	}

	public String getFlowKey() {
		return this.getBpmDefinition().getKey();
	}

	public String getSubject() {
		return this.getBpmInstance().getSubject();
	}

	public ExecutionEntity getExecutionEntity() {
		return this.an;
	}

	public void setExecutionEntity(ExecutionEntity executionEntity) {
		this.an = executionEntity;
	}

	public Object getVariable(String variableName) {
		if (this.an == null) {
			throw new WorkFlowException((IStatusCode) BpmStatusCode.VARIABLES_NO_SYNC);
		}
		return this.an.getVariable(variableName);
	}

	public boolean hasVariable(String variableName) {
		if (this.an == null) {
			throw new WorkFlowException((IStatusCode) BpmStatusCode.VARIABLES_NO_SYNC);
		}
		return this.an.hasVariable(variableName);
	}

	public void removeVariable(String variableName) {
		if (this.an == null) {
			throw new WorkFlowException((IStatusCode) BpmStatusCode.VARIABLES_NO_SYNC);
		}
		this.an.removeVariable(variableName);
	}

	public void addVariable(String name, Object value) {
		if (this.an == null) {
			throw new WorkFlowException((IStatusCode) BpmStatusCode.VARIABLES_NO_SYNC);
		}
		this.an.setVariable(name, value);
	}

	public Map<String, Object> getVariables() {
		return this.an.getVariables();
	}

	public void initSpecialParam(JSONObject flowParam) {
	}

	public String getNodeId() {
		return this.an.getActivityId();
	}
}
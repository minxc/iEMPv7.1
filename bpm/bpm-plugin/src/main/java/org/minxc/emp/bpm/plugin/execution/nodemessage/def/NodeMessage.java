package org.minxc.emp.bpm.plugin.execution.nodemessage.def;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.minxc.emp.bpm.api.engine.plugin.def.UserAssignRule;
import org.minxc.emp.bpm.engine.plugin.plugindef.AbstractBpmnExecutionPluginDefinition;

/**
 * 节点消息
 */
public class NodeMessage extends AbstractBpmnExecutionPluginDefinition {
	
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "节点消息描述不能为空")
	private String desc;
	private String nodeId;
	@NotBlank
	private String event;
	private String condition;
	private List<UserAssignRule> userRules;
	@NotBlank
	private String msgType;
	private String htmlTemplate;
	private String textTemplate;

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getCondition() {
		return this.condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public List<UserAssignRule> getUserRules() {
		return this.userRules;
	}

	public void setUserRules(List<UserAssignRule> userRules) {
		this.userRules = userRules;
	}

	public String getMsgType() {
		return this.msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getHtmlTemplate() {
		return this.htmlTemplate;
	}

	public void setHtmlTemplate(String htmlTemplate) {
		this.htmlTemplate = htmlTemplate;
	}

	public String getTextTemplate() {
		return this.textTemplate;
	}

	public void setTextTemplate(String textTemplate) {
		this.textTemplate = textTemplate;
	}
}
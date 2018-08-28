package org.minxc.emp.bpm.plugin.execution.nodemessage.def;

import com.dstz.bpm.api.engine.plugin.def.UserAssignRule;
import com.dstz.bpm.engine.plugin.plugindef.AbstractBpmExecutionPluginDef;
import java.util.List;
import org.hibernate.validator.constraints.NotBlank;

public class NodeMessage extends AbstractBpmExecutionPluginDef {
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "节点消息描述不能为空")
	private String desc;
	private String a;
	@NotBlank
	private String event;
	private String condition;
	private List<UserAssignRule> b;
	@NotBlank
	private String c;
	private String d;
	private String e;

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getNodeId() {
		return this.a;
	}

	public void setNodeId(String nodeId) {
		this.a = nodeId;
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
		return this.b;
	}

	public void setUserRules(List<UserAssignRule> userRules) {
		this.b = userRules;
	}

	public String getMsgType() {
		return this.c;
	}

	public void setMsgType(String msgType) {
		this.c = msgType;
	}

	public String getHtmlTemplate() {
		return this.d;
	}

	public void setHtmlTemplate(String htmlTemplate) {
		this.d = htmlTemplate;
	}

	public String getTextTemplate() {
		return this.e;
	}

	public void setTextTemplate(String textTemplate) {
		this.e = textTemplate;
	}
}
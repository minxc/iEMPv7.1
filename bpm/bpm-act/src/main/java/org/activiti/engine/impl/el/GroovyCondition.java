package org.activiti.engine.impl.el;

import com.dstz.base.api.constant.IStatusCode;
import com.dstz.base.api.exception.BusinessException;
import com.dstz.base.core.util.AppUtil;
import com.dstz.bpm.api.constant.ActionType;
import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
import com.dstz.bpm.api.engine.context.BpmContext;
import com.dstz.bpm.api.exception.BpmStatusCode;
import com.dstz.sys.api.groovy.IGroovyScriptEngine;
import java.util.Map;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.impl.Condition;

public class GroovyCondition implements Condition {
	private static final long serialVersionUID = -5577703954744892854L;
	private String script = "";

	public GroovyCondition(String condition) {
		this.script = condition;
	}

	public boolean evaluate(String arg0, DelegateExecution execution) {
		Map maps = execution.getVariables();
		maps.put("variableScope", execution);
		ActionCmd cmd = BpmContext.getActionModel();
		maps.putAll(cmd.getBizDataMap());
		BaseActionCmd submitAction = (BaseActionCmd) BpmContext.submitActionModel();
		maps.put("submitActionName", submitAction.getActionType().getKey());
		IGroovyScriptEngine engine = (IGroovyScriptEngine) AppUtil.getBean(IGroovyScriptEngine.class);
		try {
			return engine.executeBoolean(this.script, maps);
		} catch (Exception e) {
			e.printStackTrace();
			StringBuffer message = new StringBuffer("条件脚本解析异常！请联系管理员。");
			message.append("\n节点：" + execution.getCurrentActivityName() + "——" + execution.getCurrentActivityId());
			message.append("\n脚本：" + this.script);
			message.append("\n异常：" + e.getMessage());
			message.append("\n\n流程变量：" + maps.toString());
			throw new BusinessException(message.toString(), (IStatusCode) BpmStatusCode.GATEWAY_ERROR, (Throwable) e);
		}
	}
}
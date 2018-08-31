package org.minxc.emp.bpm.plugin.execution.nodemessage.plugin;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.minxc.emp.basis.api.freemark.IFreemarkEngine;
import org.minxc.emp.basis.api.jms.model.DefaultJmsDTO;
import org.minxc.emp.basis.api.jms.model.JmsDTO;
import org.minxc.emp.basis.api.jms.model.msg.NotifyMessage;
import org.minxc.emp.basis.api.jms.producer.JmsProducer;
import org.minxc.emp.basis.api.model.SysIdentity;
import org.minxc.emp.basis.impl.groovy.GroovyScriptEngine;
import org.minxc.emp.basis.impl.util.ContextUtil;
import org.minxc.emp.bpm.api.engine.action.cmd.BaseActionCmd;
import org.minxc.emp.bpm.api.engine.context.BpmContext;
import org.minxc.emp.bpm.api.engine.plugin.def.UserAssignRule;
import org.minxc.emp.bpm.api.service.BpmProcessDefService;
import org.minxc.emp.bpm.engine.plugin.factory.BpmPluginSessionFactory;
import org.minxc.emp.bpm.engine.plugin.runtime.abstact.AbstractBpmExecutionPlugin;
import org.minxc.emp.bpm.engine.plugin.session.BpmExecutionPluginSession;
import org.minxc.emp.bpm.engine.plugin.session.BpmPluginSession;
import org.minxc.emp.bpm.engine.plugin.session.BpmUserCalcPluginSession;
import org.minxc.emp.bpm.plugin.execution.nodemessage.def.NodeMessage;
import org.minxc.emp.bpm.plugin.execution.nodemessage.def.NodeMessagePluginDef;
import org.minxc.emp.bpm.plugin.task.userassign.plugin.UserAssignRuleCalc;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.minxc.emp.core.util.BeanUtils;
import com.minxc.emp.core.util.StringUtil;

@Component
public class NodeMessagePlugin extends AbstractBpmExecutionPlugin<BpmExecutionPluginSession, NodeMessagePluginDef> {
	@Resource
	private GroovyScriptEngine g;
	@Resource
	private BpmProcessDefService h;
	@Resource
	private JmsProducer i;
	@Autowired
	private IFreemarkEngine j;

	public Void a(BpmExecutionPluginSession pluginSession, NodeMessagePluginDef pluginDef) {
		List<NodeMessage> messages = pluginDef.getNodeMessageList();
		for (NodeMessage nodeMessage : messages) {
			if (!this.a(pluginSession, nodeMessage))
				continue;
			List<JmsDTO> JmsDto = this.a(nodeMessage, pluginSession);
			this.i.sendToQueue(JmsDto);
			this.LOG.debug("【节点消息插件】发送消息成功！时机：{}，消息标题：{}", (Object) pluginSession.getEventType().getValue(),
					(Object) nodeMessage.getDesc());
		}
		return null;
	}

	private List<JmsDTO> a(NodeMessage nodeMessage, BpmExecutionPluginSession session) {
		String[] msgType = nodeMessage.getMsgType().split(",");
		String htmlTemplate = nodeMessage.getHtmlTemplate();
		String textTemplate = nodeMessage.getTextTemplate();
		try {
			if (StringUtil.isNotEmpty((String) htmlTemplate)) {
				htmlTemplate = htmlTemplate.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
				htmlTemplate = this.j.parseByString(htmlTemplate, (Object) session);
			}
			if (StringUtil.isNotEmpty((String) textTemplate)) {
				textTemplate = this.j.parseByString(textTemplate, (Object) session);
			}
		} catch (Exception e) {
			this.LOG.error("htmlTemplate:{};textTempalte:{}", (Object) htmlTemplate, (Object) textTemplate);
			this.LOG.error("消息发送插件解析消息模板失败，可能原因为:{}", (Object) e.getMessage(), (Object) e);
			e.printStackTrace();
		}
		List<SysIdentity> userList = new ArrayList();
		if (BeanUtils.isEmpty((Object) nodeMessage.getUserRules())) {
			BaseActionCmd cmd = (BaseActionCmd) BpmContext.getActionModel();
			userList = cmd.getBpmIdentity(cmd.getNodeId());
		} else {
			userList = this.a(session, nodeMessage.getUserRules());
		}
		if (BeanUtils.isEmpty(userList)) {
			this.LOG.info("【节点消息插件】发送消息失败！原因：接收消息人员为空。 节点：{}，时机：{}，消息标题：{}", new Object[]{this.getActivitiId(session),
					session.getEventType().getValue(), nodeMessage.getDesc()});
			return Collections.emptyList();
		}
		ArrayList<JmsDTO> jmsDto = new ArrayList<JmsDTO>();
		for (String type : msgType) {
			NotifyMessage message = new NotifyMessage(nodeMessage.getDesc(), htmlTemplate, ContextUtil.getCurrentUser(),
					userList);
			jmsDto.add((JmsDTO) new DefaultJmsDTO(type, (Serializable) message));
		}
		return jmsDto;
	}

	private List<SysIdentity> a(BpmExecutionPluginSession pluginSession, List<UserAssignRule> ruleList) {
		BpmUserCalcPluginSession calcSession = BpmPluginSessionFactory
				.buildBpmUserCalcPluginSession((BpmPluginSession) pluginSession);
		return UserAssignRuleCalc.a((BpmUserCalcPluginSession) calcSession, ruleList, (Boolean) false);
	}

	private boolean a(BpmExecutionPluginSession session, NodeMessage nodeMessage) {
		Boolean support;
		if (StringUtil.isNotEmpty((String) nodeMessage.getEvent())
				&& !nodeMessage.getEvent().equals(session.getEventType().getKey())) {
			return false;
		}
		if (StringUtil.isNotEmpty((String) nodeMessage.getNodeId())
				&& !nodeMessage.getNodeId().equals(this.getActivitiId(session))) {
			return false;
		}
		if (StringUtil.isNotEmpty((String) nodeMessage.getCondition())
				&& !(support = Boolean.valueOf(this.g.executeBoolean(nodeMessage.getCondition(), (Map) session)))
						.booleanValue()) {
			return false;
		}
		return true;
	}

	@Override
	public Void execute(BpmExecutionPluginSession pluginSession, NodeMessagePluginDef pluginDef) {
		return this.a(pluginSession, pluginDef);
	}
}
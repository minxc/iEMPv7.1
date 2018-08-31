package org.minxc.emp.bpm.engine.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.basis.impl.util.ContextUtil;
import org.minxc.emp.biz.api.model.IBusinessData;
import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.constant.ScriptType;
import org.minxc.emp.bpm.api.engine.action.cmd.ActionCmd;
import org.minxc.emp.bpm.api.engine.action.cmd.InstanceActionCmd;
import org.minxc.emp.bpm.api.engine.action.cmd.TaskActionCmd;
import org.minxc.emp.bpm.api.engine.context.BpmContext;
import org.minxc.emp.bpm.api.exception.BpmStatusCode;
import org.minxc.emp.bpm.api.model.def.BpmDefProperties;
import org.minxc.emp.bpm.api.model.def.BpmProcessDef;
import org.minxc.emp.bpm.api.model.def.IBpmDefinition;
import org.minxc.emp.bpm.api.model.inst.IBpmInstance;
import org.minxc.emp.bpm.api.service.BpmProcessDefService;
import org.minxc.emp.bpm.core.manager.BpmDefinitionManager;
import org.minxc.emp.bpm.core.manager.BpmInstanceManager;
import org.minxc.emp.bpm.core.manager.BpmTaskOpinionManager;
import org.minxc.emp.bpm.core.model.BpmDefinition;
import org.minxc.emp.bpm.core.model.BpmInstance;
import org.minxc.emp.bpm.engine.action.cmd.DefaultInstanceActionCmd;
import org.minxc.emp.bpm.engine.listener.AbstractInstanceListener;
import org.minxc.emp.bpm.engine.model.DefaultBpmProcessDef;
import org.minxc.emp.core.api.exception.BusinessException;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.minxc.emp.core.util.BeanUtils;
import com.minxc.emp.core.util.StringUtil;
import com.minxc.emp.core.util.time.DateUtil;

@Component
public class InstanceStartEventListener extends AbstractInstanceListener {
	@Resource
	BpmTaskOpinionManager aO;
	@Resource
	BpmProcessDefService a;
	@Resource
	BpmInstanceManager f;
	@Resource
	BpmDefinitionManager aP;

	public EventType getBeforeTriggerEventType() {
		return EventType.START_EVENT;
	}

	public EventType getAfterTriggerEventType() {
		return EventType.START_POST_EVENT;
	}

	public void a(InstanceActionCmd instanceActionModel) {
		this.LOG.debug("流程实例【{}】执行启动过程 instanceID:[{}]", (Object) instanceActionModel.getBpmInstance().getSubject(),
				(Object) instanceActionModel.getBpmInstance().getId());
		Map<String, Object> actionVariables = instanceActionModel.getActionVariables();
		if (BeanUtils.isEmpty((Object) actionVariables)) {
			return;
		}
		for (String key : actionVariables.keySet()) {
			instanceActionModel.addVariable(key, actionVariables.get(key));
		}
		this.LOG.debug("设置流程变量【{}】", (Object) actionVariables.keySet().toString());
	}

	public void b(InstanceActionCmd instanceActionModel) {
		this.aO.createOpinionByInstance(instanceActionModel.getBpmInstance(), instanceActionModel.getFormId(), true);
		this.g((DefaultInstanceActionCmd) instanceActionModel);
	}

	public void c(InstanceActionCmd instanceActionModel) {
		this.LOG.debug("流程实例【{}】完成创建过程   instanceID：{}", (Object) instanceActionModel.getBpmInstance().getSubject(),
				(Object) instanceActionModel.getBpmInstance().getId());
	}

	protected ScriptType getScriptType() {
		return ScriptType.START;
	}

	protected InstanceActionCmd a(ExecutionEntity excutionEntity) {
		ActionCmd actionCmd = BpmContext.getActionModel();
		this.a(excutionEntity, actionCmd);
		DefaultInstanceActionCmd actionModel = (DefaultInstanceActionCmd) BpmContext.getActionModel();
		actionModel.setExecutionEntity(excutionEntity);
		BpmInstance instance = (BpmInstance) actionModel.getBpmInstance();
		if (BeanUtils.isEmpty((Object) instance.getActInstId())) {
			instance.setActDefId(excutionEntity.getProcessDefinitionId());
			instance.setActInstId(excutionEntity.getProcessInstanceId());
		}
		return actionModel;
	}

	private void g(DefaultInstanceActionCmd data) {
		BpmInstance instance = (BpmInstance) data.getBpmInstance();
		DefaultBpmProcessDef processDef = (DefaultBpmProcessDef) this.a.getBpmProcessDef(instance.getDefId());
		String subjectRule = processDef.getExtProperties().getSubjectRule();
		if (StringUtil.isEmpty((String) subjectRule)) {
			return;
		}
		HashMap<String, Object> ruleVariables = new HashMap<String, Object>();
		ruleVariables.put("title", processDef.getName());
		ruleVariables.put("startorName", ContextUtil.getCurrentUser().getFullname());
		ruleVariables.put("startDate", DateUtil.getCurrentTime((String) "yyyy-MM-dd"));
		ruleVariables.put("startTime", DateUtil.getCurrentTime());
		ruleVariables.putAll(data.getVariables());
		Map boMap = data.getBizDataMap();
		if (BeanUtils.isNotEmpty((Object) boMap)) {
			Set<String> bocodes = boMap.keySet();
			for (String bocode : bocodes) {
				IBusinessData bizData = (IBusinessData) boMap.get(bocode);
				Map<String, Object> dataMap = bizData.getData();
				for (Map.Entry entry : dataMap.entrySet()) {
					ruleVariables.put(bocode + "." + entry.getKey(), entry.getValue());
				}
			}
		}
		String subject = this.a(subjectRule, ruleVariables);
		instance.setSubject(subject);
		this.LOG.debug("更新流程标题:{}", (Object) subject);
	}

	private String a(String subject, Map<String, Object> variables) {
		if (StringUtils.isEmpty((CharSequence) subject)) {
			return "";
		}
		Pattern regex = Pattern.compile("\\{(.*?)\\}", 98);
		Matcher matcher = regex.matcher(subject);
		while (matcher.find()) {
			String tag = matcher.group(0);
			String rule = matcher.group(1);
			String[] aryRule = rule.split(":");
			String name = "";
			name = aryRule.length == 1 ? rule : aryRule[1];
			if (variables.containsKey(name)) {
				Object obj = variables.get(name);
				if (obj != null) {
					try {
						subject = subject.replace(tag, obj.toString());
					} catch (Exception e) {
						subject = subject.replace(tag, "");
					}
					continue;
				}
				subject = subject.replace(tag, "");
				continue;
			}
			subject = subject.replace(tag, "");
		}
		return subject;
	}

	private void a(ExecutionEntity excutionEntity, ActionCmd preAction) {
		String preActionDefKey = preAction.getBpmInstance().getDefKey();
		if (preAction instanceof InstanceActionCmd) {
			if (!excutionEntity.getProcessDefinitionKey().equals(preActionDefKey)) {
				throw new BusinessException("流程启动失败，错误的线程数据！", BpmStatusCode.ACTIONCMD_ERROR);
			}
			return;
		}
		ExecutionEntity callActivityNode = excutionEntity.getSuperExecution();
		if (preAction instanceof TaskActionCmd && (callActivityNode == null
				|| !preAction.getBpmInstance().getActInstId().equals(callActivityNode.getProcessInstanceId()))) {
			throw new BusinessException(BpmStatusCode.ACTIONCMD_ERROR);
		}
		BpmDefinition subDefinition = this.aP.getByKey(excutionEntity.getProcessDefinitionKey());
		BpmInstance subInstance = this.f.genInstanceByDefinition((IBpmDefinition) subDefinition);
		subInstance.setActInstId(excutionEntity.getProcessInstanceId());
		subInstance.setParentInstId(preAction.getBpmInstance().getId());
		this.f.create(subInstance);
		DefaultInstanceActionCmd startAction = new DefaultInstanceActionCmd();
		startAction.setBpmDefinition((IBpmDefinition) subDefinition);
		startAction.setBpmInstance((IBpmInstance) subInstance);
		startAction.setBizDataMap(preAction.getBizDataMap());
		BpmContext.setActionModel((ActionCmd) startAction);
	}
}
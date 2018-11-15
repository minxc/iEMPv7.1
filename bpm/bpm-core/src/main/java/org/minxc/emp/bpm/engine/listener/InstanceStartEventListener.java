package org.minxc.emp.bpm.engine.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.biz.api.model.BusinessData;
import org.minxc.emp.bpm.api.constant.EventType;
import org.minxc.emp.bpm.api.constant.ScriptType;
import org.minxc.emp.bpm.api.engine.action.cmd.ActionCmd;
import org.minxc.emp.bpm.api.engine.action.cmd.InstanceActionCmd;
import org.minxc.emp.bpm.api.engine.action.cmd.TaskActionCmd;
import org.minxc.emp.bpm.api.engine.context.BpmnContext;
import org.minxc.emp.bpm.api.exception.BpmnStatusCode;
import org.minxc.emp.bpm.api.model.def.BpmnDefinition;
import org.minxc.emp.bpm.api.model.inst.BpmnInstance;
import org.minxc.emp.bpm.api.service.BpmnProcessDefinitionService;
import org.minxc.emp.bpm.core.manager.BpmnDefinitionManager;
import org.minxc.emp.bpm.core.manager.BpmnInstanceManager;
import org.minxc.emp.bpm.core.manager.BpmnTaskOpinionManager;
import org.minxc.emp.bpm.core.model.BpmnDefinitionImpl;
import org.minxc.emp.bpm.core.model.BpmnInstanceImpl;
import org.minxc.emp.bpm.engine.action.cmd.DefaultInstanceActionCmd;
import org.minxc.emp.bpm.engine.model.DefaultBpmnProcessDefinition;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.core.util.StringUtil;
import org.minxc.emp.core.util.time.DateUtil;
import org.minxc.emp.system.util.ContextUtil;
import org.springframework.stereotype.Component;

@Component
public class InstanceStartEventListener extends AbstractInstanceListener {
	@Resource
	BpmnTaskOpinionManager aO;
	@Resource
    BpmnProcessDefinitionService a;
	@Resource
	BpmnInstanceManager f;
	@Resource
    BpmnDefinitionManager aP;

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
		ActionCmd actionCmd = BpmnContext.getActionModel();
		this.a(excutionEntity, actionCmd);
		DefaultInstanceActionCmd actionModel = (DefaultInstanceActionCmd) BpmnContext.getActionModel();
		actionModel.setExecutionEntity(excutionEntity);
		BpmnInstanceImpl instance = (BpmnInstanceImpl) actionModel.getBpmInstance();
		if (BeanUtils.isEmpty((Object) instance.getActInstId())) {
			instance.setActDefId(excutionEntity.getProcessDefinitionId());
			instance.setActInstId(excutionEntity.getProcessInstanceId());
		}
		return actionModel;
	}

	private void g(DefaultInstanceActionCmd data) {
		BpmnInstanceImpl instance = (BpmnInstanceImpl) data.getBpmInstance();
		DefaultBpmnProcessDefinition processDef = (DefaultBpmnProcessDefinition) this.a.getBpmProcessDef(instance.getDefId());
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
				BusinessData bizData = (BusinessData) boMap.get(bocode);
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
				throw new BusinessException("流程启动失败，错误的线程数据！", BpmnStatusCode.ACTIONCMD_ERROR);
			}
			return;
		}
		ExecutionEntity callActivityNode = excutionEntity.getSuperExecution();
		if (preAction instanceof TaskActionCmd && (callActivityNode == null
				|| !preAction.getBpmInstance().getActInstId().equals(callActivityNode.getProcessInstanceId()))) {
			throw new BusinessException(BpmnStatusCode.ACTIONCMD_ERROR);
		}
		BpmnDefinitionImpl subDefinition = this.aP.getByKey(excutionEntity.getProcessDefinitionKey());
		BpmnInstanceImpl subInstance = this.f.genInstanceByDefinition((BpmnDefinition) subDefinition);
		subInstance.setActInstId(excutionEntity.getProcessInstanceId());
		subInstance.setParentInstId(preAction.getBpmInstance().getId());
		this.f.create(subInstance);
		DefaultInstanceActionCmd startAction = new DefaultInstanceActionCmd();
		startAction.setBpmDefinition((BpmnDefinition) subDefinition);
		startAction.setBpmInstance((BpmnInstance) subInstance);
		startAction.setBizDataMap(preAction.getBizDataMap());
		BpmnContext.setActionModel((ActionCmd) startAction);
	}
}
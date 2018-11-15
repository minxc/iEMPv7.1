package org.minxc.emp.bpm.engine.data;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.minxc.emp.basis.impl.groovy.DefaultGroovyScriptEngineImpl;
import org.minxc.emp.biz.api.model.BusinessData;
import org.minxc.emp.biz.api.model.BusinessPermission;
import org.minxc.emp.biz.api.service.BusinessDataService;
import org.minxc.emp.bpm.api.engine.action.button.ButtonFactory;
import org.minxc.emp.bpm.api.engine.data.BpmnFlowDataAccessor;
import org.minxc.emp.bpm.api.engine.data.result.BpmnFlowData;
import org.minxc.emp.bpm.api.engine.data.result.BpmnFlowInstanceData;
import org.minxc.emp.bpm.api.engine.data.result.BpmnFlowTaskData;
import org.minxc.emp.bpm.api.exception.BpmnStatusCode;
import org.minxc.emp.bpm.api.model.def.NodeInit;
import org.minxc.emp.bpm.api.model.form.BpmForm;
import org.minxc.emp.bpm.api.model.inst.BpmnInstance;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.minxc.emp.bpm.api.model.nodedef.Button;
import org.minxc.emp.bpm.api.model.task.BpmTask;
import org.minxc.emp.bpm.api.service.BpmnProcessDefinitionService;
import org.minxc.emp.bpm.api.service.BpmnRightsFormService;
import org.minxc.emp.bpm.core.manager.BpmnBusinessLinkManager;
import org.minxc.emp.bpm.core.manager.BpmnDefinitionManager;
import org.minxc.emp.bpm.core.manager.BpmnInstanceManager;
import org.minxc.emp.bpm.core.manager.BpmnTaskManager;
import org.minxc.emp.bpm.core.model.BpmnInstanceImpl;
import org.minxc.emp.bpm.engine.data.handle.BpmnBusinessDataHandle;
import org.minxc.emp.bpm.engine.model.DefaultBpmnProcessDefinition;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.api.exception.SystemException;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.core.util.StringUtil;
import org.minxc.emp.form.api.model.FormCategory;
import org.minxc.emp.form.api.model.FormType;
import org.minxc.emp.form.api.model.FormDefinition;
import org.minxc.emp.form.api.service.FormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DefaultBpmnFlowDataAccessor implements BpmnFlowDataAccessor {
	
	protected Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Resource
	private BpmnInstanceManager aD;
	@Resource
	private BpmnRightsFormService aE;
	@Resource
	private BpmnDefinitionManager c;
	@Resource
	private BpmnProcessDefinitionService a;
	@Resource
	private BpmnTaskManager ay;
	@Resource
	private FormService aF;
	@Resource
	private BpmnBusinessLinkManager aG;
	@Resource
	private BpmnBusinessDataHandle aH;
	@Resource
	private DefaultGroovyScriptEngineImpl av;
	@Resource
	private BusinessDataService businessDataService;

	public BpmnFlowInstanceData getInstanceData(String instanceId, FormType formType, String nodeId) {
		BpmnFlowInstanceData data = new BpmnFlowInstanceData();
		BpmnInstanceImpl instance = (BpmnInstanceImpl) this.aD.get(instanceId);
		data.setInstance((BpmnInstance) instance);
		this.a((BpmnFlowData) data, instanceId, nodeId, formType, true);
		this.a((BpmnFlowData) data, nodeId, true);
		return data;
	}

	public BpmnFlowData getStartFlowData(String defId, String instanceId, FormType formType, Boolean readonly) {
		if (StringUtil.isEmpty((String) instanceId) && StringUtil.isEmpty((String) defId)) {
			throw new BusinessException("获取发起流程数据失败!流程定义id或者实例id缺失", BpmnStatusCode.PARAM_ILLEGAL);
		}
		BpmnFlowInstanceData data = new BpmnFlowInstanceData();
		if (StringUtil.isEmpty((String) instanceId)) {
			data.setDefId(defId);
			this.a(data, formType);
		} else {
			BpmnInstanceImpl instance = (BpmnInstanceImpl) this.aD.get(instanceId);
			data.setInstance((BpmnInstance) instance);
			BpmNodeDef startNode = this.a.getStartEvent(instance.getDefId());
			this.a((BpmnFlowData) data, instanceId, readonly != false ? "" : startNode.getNodeId(), formType, readonly);
		}
		BpmNodeDef startNode = this.a.getStartEvent(data.getDefId());
		this.a((BpmnFlowData) data, startNode.getNodeId(), readonly);
		return data;
	}

	public BpmnFlowData getFlowTaskData(String taskId, FormType formType) {
		BpmnFlowTaskData taskData = new BpmnFlowTaskData();
		BpmTask task = (BpmTask) this.ay.get(taskId);
		if (task == null) {
			throw new BusinessException("任务可能已经办理完成", BpmnStatusCode.TASK_NOT_FOUND);
		}
		taskData.setTask(task);
		this.a((BpmnFlowData) taskData, task.getInstId(), task.getNodeId(), formType, false);
		this.a((BpmnFlowData) taskData, task.getNodeId(), false);
		return taskData;
	}

	private void a(BpmnFlowInstanceData flowData, FormType formType) {
		String defId = flowData.getDefId();
		BpmNodeDef startNode = this.a.getStartEvent(defId);
		flowData.setDefName(this.a.getBpmProcessDef(defId).getName());
		BusinessPermission permission = this.aE.getInstanceFormPermission((BpmnFlowData) flowData,
				startNode.getNodeId(), formType, false);
		BpmForm form = flowData.getForm();
		if (FormCategory.INNER.equals((Object) form.getType())) {
			Map dataMap = this.aH.a(permission, defId);
			FormDefinition formDef = this.aF.getByFormKey(form.getFormValue());
			if (formDef == null) {
				throw new BusinessException(form.getFormValue(), BpmnStatusCode.FLOW_FORM_LOSE);
			}
			form.setFormHtml(formDef.getHtml());
			flowData.setDataMap(dataMap);
			this.a((BpmnFlowData) flowData, startNode.getNodeId());
		} else {
			String url = form.getFormValue().replace("{bizId}", "");
			form.setFormValue(url);
		}
	}

	private void a(BpmnFlowData flowData, String instaneId, String nodeId, FormType formType, boolean isReadOnly) {
		BpmnInstanceImpl instance = (BpmnInstanceImpl) this.aD.get(instaneId);
		String defKey = instance.getDefKey();
		BusinessPermission businessPermision = this.aE.getInstanceFormPermission(flowData, nodeId, formType,
				isReadOnly);
		BpmForm form = flowData.getForm();
		if (FormCategory.INNER.equals((Object) form.getType())) {
			Map dataModel = this.aH.a(businessPermision, instance);
			flowData.setDataMap(dataModel);
			FormDefinition formDef = this.aF.getByFormKey(form.getFormValue());
			if (formDef == null) {
				throw new BusinessException(form.getFormValue(), BpmnStatusCode.FLOW_FORM_LOSE);
			}
			form.setFormHtml(formDef.getHtml());
			this.a(flowData, nodeId);
		}
		this.a(form, (BpmnInstance) instance);
	}

	private void a(BpmnFlowData flowData, String nodeId) {
		Map<String, BusinessData>  bos = flowData.getDataMap();
		if (BeanUtils.isEmpty((Object) bos)) {
			return;
		}
		DefaultBpmnProcessDefinition def = (DefaultBpmnProcessDefinition) this.a.getBpmProcessDef(flowData.getDefId());
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.putAll(bos);
		if (flowData instanceof BpmnFlowTaskData) {
			param.put("task", (Object) ((BpmnFlowTaskData) flowData).getTask());
		} else if (flowData instanceof BpmnFlowInstanceData) {
			param.put("instance", (Object) ((BpmnFlowInstanceData) flowData).getInstance());
		}
		for (NodeInit init : def.e(nodeId)) {
			if (!StringUtil.isNotEmpty((String) init.getBeforeShow()))
				continue;
			try {
				this.av.execute(init.getBeforeShow(), param);
			} catch (Exception e) {
				throw new SystemException("执行脚本初始化失败", BpmnStatusCode.FLOW_DATA_EXECUTE_SHOWSCRIPT_ERROR,
						(Throwable) e);
			}
			this.LOG.debug("执行节点数据初始化脚本{}", (Object) init.getBeforeShow());
		}
		JSONObject json = new JSONObject();
		JSONObject initJson = new JSONObject();
		for (String key : bos.keySet()) {
			BusinessData bd = (BusinessData) bos.get(key);
			JSONObject boJson = this.businessDataService.assemblyFormDefData(bd);
			json.put(key, (Object) boJson);
			bd.fullBusDataInitData(initJson);
		}
		flowData.setData(json);
		flowData.setInitData(initJson);
	}

	private void a(BpmnFlowData flowData, String nodeId, boolean isReadOnly) {
		BpmNodeDef nodeDef = this.a.getBpmNodeDef(flowData.getDefId(), nodeId);
		List<Button> buttons = nodeDef.getButtons();
		if (isReadOnly) {
			buttons = ButtonFactory.getInstanceButtons();
		}
		HashMap<String, Object> param = new HashMap<String, Object>();
		if (BeanUtils.isNotEmpty((Object) flowData.getDataMap())) {
			param.putAll(flowData.getDataMap());
		}
		if (flowData instanceof BpmnFlowTaskData) {
			param.put("task", (Object) ((BpmnFlowTaskData) flowData).getTask());
		} else if (flowData instanceof BpmnFlowInstanceData) {
			param.put("instance", (Object) ((BpmnFlowInstanceData) flowData).getInstance());
		}
		ArrayList<Button> btns = new ArrayList<Button>();
		for (Button btn : buttons) {
			if (StringUtil.isNotEmpty((String) btn.getGroovyScript())) {
				try {
					boolean isSkip = this.av.executeBoolean(btn.getGroovyScript(), param);
					this.LOG.debug("执行节点按钮脚本{},执行跳过结果{}", (Object) btn.getGroovyScript(), (Object) isSkip);
					if (isSkip) {
						continue;
					}
				} catch (Exception e) {
					throw new SystemException("按钮脚本执行失败", BpmnStatusCode.FLOW_DATA_GET_BUTTONS_ERROR,
							(Throwable) e);
				}
			}
			btns.add(btn);
		}
		flowData.setButtonList(btns);
	}

	private void a(BpmForm form, BpmnInstance instance) {
		if (form == null || form.isFormEmpty() || FormCategory.INNER == form.getType()) {
			return;
		}
		String url = form.getFormValue().replace("{bizId}", instance.getBizKey());
		form.setFormValue(url);
	}
}
package org.minxc.emp.bpm.engine.action.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.minxc.emp.core.util.BeanUtils;
import com.minxc.emp.core.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.minxc.emp.basis.impl.groovy.GroovyScriptEngine;
import org.minxc.emp.biz.api.model.IBusinessData;
import org.minxc.emp.biz.api.service.IBusinessDataService;
import org.minxc.emp.bpm.api.constant.ActionType;
import org.minxc.emp.bpm.api.engine.action.cmd.BaseActionCmd;
import org.minxc.emp.bpm.api.engine.action.cmd.TaskActionCmd;
import org.minxc.emp.bpm.api.engine.action.handler.ActionHandler;
import org.minxc.emp.bpm.api.engine.context.BpmContext;
import org.minxc.emp.bpm.api.exception.BpmStatusCode;
import org.minxc.emp.bpm.api.exception.WorkFlowException;
import org.minxc.emp.bpm.api.model.def.BpmDataModel;
import org.minxc.emp.bpm.api.model.def.NodeInit;
import org.minxc.emp.bpm.api.model.form.BpmForm;
import org.minxc.emp.bpm.api.model.inst.IBpmInstance;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.minxc.emp.bpm.api.model.task.IBpmTask;
import org.minxc.emp.bpm.api.service.BpmProcessDefService;
import org.minxc.emp.bpm.core.manager.BpmInstanceManager;
import org.minxc.emp.bpm.core.manager.TaskIdentityLinkManager;
import org.minxc.emp.bpm.core.model.BpmInstance;
import org.minxc.emp.bpm.engine.action.cmd.DefualtTaskActionCmd;
import org.minxc.emp.bpm.engine.constant.TaskSkipType;
import org.minxc.emp.bpm.engine.data.handle.BpmBusDataHandle;
import org.minxc.emp.bpm.engine.model.DefaultBpmProcessDef;
import org.minxc.emp.bpm.engine.util.HandlerUtil;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.api.exception.SystemException;
import org.minxc.emp.idm.api.model.User;
import org.minxc.emp.system.util.ContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbsActionHandler<T extends BaseActionCmd> implements ActionHandler<T> {
	
	protected Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Resource
	protected BpmProcessDefService a;
	@Resource
	protected BpmInstanceManager f;
	@Resource
	protected BpmBusDataHandle at;
	@Resource
	protected TaskIdentityLinkManager i;
	@Resource
	protected IBusinessDataService au;
	@Resource
	protected GroovyScriptEngine av;

	@Transactional
	public void execute(T model) {
		this.c(model);
		this.k((BaseActionCmd) model);
		BpmContext.setActionModel(model);
		this.d(model);
		this.b(model);
		this.f(model);
		BpmContext.removeActionModel();
		this.e(model);
	}

	protected abstract void b(T var1);

	protected abstract void c(T var1);

	protected void d(T data) {
		this.j(data);
		this.LOG.debug("流程启动处理业务数据...");
		this.at.n(data);
		this.h(data);
	}

	protected void e(T model) {
		this.i(model);
		if (this.getActionType() == ActionType.DRAFT) {
			return;
		}
		if (model.isSource()) {
			BpmContext.cleanTread();
		}
	}

	private void f(T model) {
		if (this.getActionType() != ActionType.AGREE && this.getActionType() != ActionType.OPPOSE
				&& this.getActionType() != ActionType.START) {
			return;
		}
		DefualtTaskActionCmd taskModel = (DefualtTaskActionCmd) BpmContext.getActionModel();
		if (taskModel.getActionName().equals((Object) ActionType.CREATE)) {
			return;
		}
		if (taskModel.b() == TaskSkipType.NO_SKIP) {
			return;
		}
		DefualtTaskActionCmd complateModel = new DefualtTaskActionCmd();
		complateModel.setBpmInstance(taskModel.getBpmInstance());
		complateModel.setBpmDefinition(taskModel.getBpmDefinition());
		complateModel.setBizDataMap(taskModel.getBizDataMap());
		complateModel.setBpmIdentities(taskModel.getBpmIdentities());
		complateModel.setBusinessKey(taskModel.getBusinessKey());
		complateModel.setActionName(ActionType.AGREE.getKey());
		complateModel.setBpmTask(taskModel.getBpmTask());
		complateModel.setOpinion(taskModel.b().getValue());
		complateModel.a();
	}

	public void g(T model) {
		BpmContext.setActionModel(model);
		this.h(model);
		this.b(model);
		this.f(model);
		BpmContext.removeActionModel();
		this.e(model);
	}

	protected abstract void h(T var1);

	protected abstract void i(T var1);

	protected void j(T actionModel) {
		String handler;
		BpmInstance instance = (BpmInstance) actionModel.getBpmInstance();
		if (StringUtil.isEmpty((String) actionModel.getBusinessKey())
				&& StringUtil.isNotEmpty((String) instance.getBizKey())) {
			actionModel.setBusinessKey(instance.getBizKey());
		}
		if (StringUtil.isEmpty((String) (handler = this.m(actionModel)))) {
			return;
		}
		try {
			HandlerUtil.a(actionModel, (String) handler);
			this.LOG.debug("执行URL表单处理器：{}", (Object) handler);
		} catch (Exception ex) {
			throw new WorkFlowException(BpmStatusCode.HANDLER_ERROR, (Throwable) ex);
		}
		if (StringUtil.isNotEmpty((String) actionModel.getBusinessKey())
				&& StringUtil.isEmpty((String) instance.getBizKey())) {
			instance.setBizKey(actionModel.getBusinessKey());
		}
	}

	protected void k(BaseActionCmd actionModel) {
		IBpmTask task;
		IBpmInstance instance = actionModel.getBpmInstance();
		if (instance.getIsForbidden() == 1) {
			throw new WorkFlowException("流程实例已经被禁止，请联系管理员", BpmStatusCode.DEF_FORBIDDEN);
		}
		DefaultBpmProcessDef def = (DefaultBpmProcessDef) this.a.getBpmProcessDef(instance.getDefId());
		if ("forbidden".equals(def.getExtProperties().getStatus())) {
			throw new WorkFlowException("流程定义已经被禁用，请联系管理员", BpmStatusCode.DEF_FORBIDDEN);
		}
		User user = ContextUtil.getCurrentUser();
		if (ContextUtil.isAdmin((User) user)) {
			return;
		}
		String taskId = null;
		String instId = actionModel.getInstanceId();
		if (actionModel instanceof DefualtTaskActionCmd) {
			task = ((DefualtTaskActionCmd) actionModel).getBpmTask();
			if (user.getUserId().equals(task.getAssigneeId())) {
				return;
			}
		} else {
			if (StringUtil.isNotEmpty((String) def.getProcessDefinitionId())) {
				return;
			}
			return;
		}
		taskId = task.getId();
		instId = null;
		Boolean hasPermission = this.i.checkUserOperatorPermission(user.getUserId(), instId, taskId);
		if (!hasPermission.booleanValue()) {
			throw new BusinessException("没有该任务的操作权限", BpmStatusCode.NO_PERMISSION);
		}
	}

	protected void l(T actionModel) {
		IBpmInstance instance = actionModel.getBpmInstance();
		if (StringUtil.isEmpty((String) actionModel.getBusData())) {
			return;
		}
		JSONObject data = JSON.parseObject((String) actionModel.getBusData());
		DefaultBpmProcessDef bpmProcessDef = (DefaultBpmProcessDef) this.a.getBpmProcessDef(instance.getDefId());
		for (BpmDataModel dataModel : bpmProcessDef.getDataModelList()) {
			String modelCode = dataModel.getCode();
			if (!data.containsKey((Object) modelCode))
				continue;
			IBusinessData businessData = this.au.parseBusinessData(data.getJSONObject(modelCode), modelCode);
			actionModel.getBizDataMap().put(modelCode, businessData);
		}
	}

	protected void a(BaseActionCmd cmd, BpmNodeDef nodeDef) {
		String nodeId = nodeDef.getNodeId();
		DefaultBpmProcessDef def = (DefaultBpmProcessDef) this.a.getBpmProcessDef(cmd.getBpmInstance().getDefId());
		List<NodeInit> nodeInitList = def.e(nodeId);
		Map bos = cmd.getBizDataMap();
		if (BeanUtils.isEmpty((Object) bos) || BeanUtils.isEmpty((Object) nodeInitList)) {
			return;
		}
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.putAll(bos);
		param.put("bpmInstance", (Object) cmd.getBpmInstance());
		if (cmd instanceof DefualtTaskActionCmd) {
			param.put("bpmTask", (Object) ((DefualtTaskActionCmd) cmd).getBpmTask());
		}
		for (NodeInit init : nodeInitList) {
			if (!StringUtil.isNotEmpty((String) init.getWhenSave()))
				continue;
			try {
				this.av.execute(init.getWhenSave(), param);
			} catch (Exception e) {
				throw new SystemException(e.getMessage(),
						BpmStatusCode.FLOW_DATA_EXECUTE_SHOWSCRIPT_ERROR, (Throwable) e);
			}
			this.LOG.debug("执行节点数据初始化脚本{}", (Object) init.getBeforeShow());
		}
	}

	private String m(T actionModel) {
		BpmForm form;
		String defId = actionModel.getDefId();
		if (actionModel instanceof TaskActionCmd) {
			TaskActionCmd cmd = (TaskActionCmd) actionModel;
			String nodeId = cmd.getNodeId();
			BpmNodeDef nodeDef = this.a.getBpmNodeDef(defId, nodeId);
			form = nodeDef.getForm();
		} else {
			BpmNodeDef nodeDef = this.a.getStartEvent(defId);
			form = nodeDef.getForm();
		}
		if (form == null || form.isFormEmpty()) {
			DefaultBpmProcessDef def = (DefaultBpmProcessDef) this.a.getBpmProcessDef(defId);
			form = def.getGlobalForm();
		}
		if (form != null) {
			return form.getFormHandler();
		}
		return null;
	}

	public Boolean isDefault() {
		return true;
	}

	public String getDefaultBeforeScript() {
		return "";
	}

//	@Transactional
//	public void execute(ActionCmd actionCmd) {
//		this.a((BaseActionCmd) actionCmd);
//	}
}
package org.minxc.emp.bpm.engine.model;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.minxc.emp.bpm.api.constant.NodeType;
import org.minxc.emp.bpm.api.engine.plugin.context.BpmnPluginContext;
import org.minxc.emp.bpm.api.model.def.BpmDataModel;
import org.minxc.emp.bpm.api.model.def.BpmDefProperties;
import org.minxc.emp.bpm.api.model.def.BpmProcessDef;
import org.minxc.emp.bpm.api.model.def.BpmVariableDef;
import org.minxc.emp.bpm.api.model.def.NodeInit;
import org.minxc.emp.bpm.api.model.form.BpmForm;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.core.util.StringUtil;

public class DefaultBpmnProcessDefinition implements BpmProcessDef {
	private static final long serialVersionUID = 1L;
	private String A = "";
	private String name = "";
	private String processDefinitionId = "";
	private List<BpmNodeDef> aT;
	private BpmProcessDef aU = null;
	private List<BpmnPluginContext> aV = new ArrayList<BpmnPluginContext>();
	private List<BpmVariableDef> aW = new ArrayList<BpmVariableDef>();
	private List<BpmDataModel> aX = new ArrayList<BpmDataModel>();
	private BpmForm aY = null;
	private BpmForm aZ = null;
	private BpmForm ba = null;
	private BpmForm bb = null;
	private BpmDefProperties bc = new BpmDefProperties();
	private List<NodeInit> bd = new ArrayList<NodeInit>();
	private JSONObject be;

	public BpmProcessDef getParentProcessDef() {
		return this.aU;
	}

	public BpmDefProperties getExtProperties() {
		return this.bc;
	}

	public void setExtProperties(BpmDefProperties extPropertys) {
		this.bc = extPropertys;
	}

	public List<BpmnPluginContext> getBpmPluginContexts() {
		return this.aV;
	}

	public BpmnPluginContext a(Class<?> clazz) {
		List<BpmnPluginContext> Plugins = this.getBpmPluginContexts();
		if (BeanUtils.isEmpty(Plugins)) {
			return null;
		}
		for (BpmnPluginContext pulgin : Plugins) {
			if (!pulgin.getClass().isAssignableFrom(clazz))
				continue;
			return pulgin;
		}
		return null;
	}

	public List<BpmVariableDef> getVariableList() {
		return this.aW;
	}

	public BpmForm getInstForm() {
		return this.aY;
	}

	public BpmForm getInstMobileForm() {
		return this.aZ;
	}

	public BpmForm getGlobalForm() {
		return this.ba;
	}

	public BpmForm getGlobalMobileForm() {
		return this.bb;
	}

	public List<BpmnPluginContext> getPluginContextList() {
		return this.aV;
	}

	public void setPluginContextList(List<BpmnPluginContext> pluginContextList) {
		Collections.sort(pluginContextList);
		this.aV = pluginContextList;
	}

	public List<BpmVariableDef> getVarList() {
		return this.aW;
	}

	public void setVarList(List<BpmVariableDef> varList) {
		this.aW = varList;
	}

	public void setInstForm(BpmForm instForm) {
		this.aY = instForm;
	}

	public void setInstMobileForm(BpmForm instMobileForm) {
		this.aZ = instMobileForm;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setGlobalForm(BpmForm globalForm) {
		this.ba = globalForm;
	}

	public void setGlobalMobileForm(BpmForm globalMobileForm) {
		this.bb = globalMobileForm;
	}

	public String getDefKey() {
		return this.A;
	}

	public void setDefKey(String defKey) {
		this.A = defKey;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getProcessDefinitionId() {
		return this.processDefinitionId;
	}

	public void setBpmnNodeDefs(List<BpmNodeDef> bpmnNodeDefs) {
		this.aT = bpmnNodeDefs;
	}

	public List<BpmNodeDef> getBpmnNodeDefs() {
		return this.aT;
	}

	public BpmNodeDef getStartEvent() {
		for (BpmNodeDef nodeDef : this.aT) {
			if (!nodeDef.getType().equals((Object) NodeType.START))
				continue;
			return nodeDef;
		}
		return null;
	}

	public List<NodeInit> getNodeInitList() {
		return this.bd;
	}

	public List<NodeInit> e(String nodeId) {
		ArrayList<NodeInit> initList = new ArrayList<NodeInit>();
		for (NodeInit init : this.bd) {
			if (!StringUtil.isNotEmpty((String) nodeId) || !init.getNodeId().equals(nodeId))
				continue;
			initList.add(init);
		}
		return initList;
	}

	public void setNodeInitList(List<NodeInit> nodeInitList) {
		this.bd = nodeInitList;
	}

	public List<BpmNodeDef> getStartNodes() {
		BpmNodeDef startNode = this.getStartEvent();
		if (startNode == null) {
			return null;
		}
		return startNode.getOutcomeNodes();
	}

	public List<BpmNodeDef> getEndEvents() {
		ArrayList<BpmNodeDef> rtnList = new ArrayList<BpmNodeDef>();
		for (BpmNodeDef nodeDef : this.aT) {
			if (!nodeDef.getType().equals((Object) NodeType.END))
				continue;
			rtnList.add(nodeDef);
		}
		return rtnList;
	}

	public List<BpmDataModel> getDataModelList() {
		return this.aX;
	}

	public String getDataModelKeys() {
		ArrayList<String> keys = new ArrayList<String>();
		for (BpmDataModel model : this.aX) {
			keys.add(model.getCode());
		}
		return StringUtil.convertCollectionAsString(keys);
	}

	public void setDataModelList(List<BpmDataModel> dataModelList) {
		this.aX = dataModelList;
	}

	public void setParentProcessDef(DefaultBpmnProcessDefinition processDef) {
		this.aU = processDef;
	}

	public void setJson(JSONObject bpmDefSetting) {
		this.be = bpmDefSetting;
	}

	public JSONObject getJson() {
		return this.be;
	}
}
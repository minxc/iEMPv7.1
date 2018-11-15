package org.minxc.emp.bpm.core.model;

import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.List;

import org.minxc.emp.biz.api.model.BusinessPermission;

public class BpmnOverallView {
	public static final String K = "override";
	public static final String L = "edit";
	public static final String M = "newVersion";
	private BpmnDefinitionImpl N;
	private List<BusinessPermission> O = new ArrayList<BusinessPermission>();
	private String importType = "edit";
	private String defId;
	private Boolean P;
	private JSONObject Q;

	public List<BusinessPermission> getFormRights() {
		return this.O;
	}

	public void setFormRights(List<BusinessPermission> formRights) {
		this.O = formRights;
	}

	public String getImportType() {
		return this.importType;
	}

	public void setImportType(String importType) {
		this.importType = importType;
	}

	public Boolean getIsUpdateVersion() {
		return this.P;
	}

	public void setIsUpdateVersion(Boolean isUpdateVersion) {
		this.P = isUpdateVersion;
	}

	public JSONObject getDefSetting() {
		return this.Q;
	}

	public void setDefSetting(JSONObject defSetting) {
		this.Q = defSetting;
	}

	public BpmnDefinitionImpl getBpmDefinition() {
		return this.N;
	}

	public void setBpmDefinition(BpmnDefinitionImpl bpmnDefinition) {
		this.N = bpmnDefinition;
	}

	public String getDefId() {
		return this.defId;
	}

	public void setDefId(String defId) {
		this.defId = defId;
	}
}
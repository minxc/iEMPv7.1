package org.minxc.emp.bpm.api.engine.data.result;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

import org.minxc.emp.bpm.api.model.form.BpmForm;
import org.minxc.emp.bpm.api.model.nodedef.Button;

public interface FlowData {

    public abstract String getDefId();

    public abstract BpmForm getForm();

    public abstract JSONObject getData();

    public abstract JSONObject getPermission();
    
    public abstract JSONObject getTablePermission();
    
    public abstract JSONObject getInitData();

//	public abstract BpmnDefinition getBpmDefinition();

    public List<Button> getButtonList();

}
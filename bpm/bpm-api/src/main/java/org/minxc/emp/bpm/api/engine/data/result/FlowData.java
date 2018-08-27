package org.minxc.emp.bpm.api.engine.data.result;

import com.alibaba.fastjson.JSONObject;
import org.minxc.emp.bpm.api.model.form.BpmnForm;
import org.minxc.emp.bpm.api.model.nodedef.Button;

import java.util.List;

public interface FlowData {

    public abstract String getDefId();

    public abstract BpmnForm getForm();

    public abstract JSONObject getData();

    public abstract JSONObject getPermission();
    
    public abstract JSONObject getTablePermission();
    
    public abstract JSONObject getInitData();

//	public abstract IBpmDefinition getBpmDefinition();

    public List<Button> getButtonList();

}
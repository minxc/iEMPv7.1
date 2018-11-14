package org.minxc.emp.bpm.api.engine.data.result;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

import org.minxc.emp.biz.api.model.BusinessData;
import org.minxc.emp.bpm.api.model.form.BpmForm;
import org.minxc.emp.bpm.api.model.nodedef.Button;

public class BpmFlowData implements FlowData {
    protected String defId;
    protected String defName;
    //	protected IBpmDefinition bpmDefinition;
    protected BpmForm Form;
    /**
     * model key dataModel
     **/
    protected JSONObject data;
    protected JSONObject permission;
    protected JSONObject tablePermission;
    protected JSONObject initData;

    protected List<Button> buttonList;
    
    transient Map<String, BusinessData>  dataMap;

    /* (non-Javadoc)
     * @see com.dstz.bpm.api.engine.data.model.FlowData#getDefId()
     */
    @Override
    public String getDefId() {
        return defId;
    }

    public void setDefId(String defId) {
        this.defId = defId;
    }

    /* (non-Javadoc)
     * @see com.dstz.bpm.api.engine.data.model.FlowData#getForm()
     */
    @Override
    public BpmForm getForm() {
        return Form;
    }

    public void setForm(BpmForm Form) {
        this.Form = Form;
    }

    @Override
    public JSONObject getPermission() {
        return permission;
    }

    public JSONObject getTablePermission() {
		return tablePermission;
	}

	public void setTablePermission(JSONObject tablePermission) {
		this.tablePermission = tablePermission;
	}

	public void setPermission(JSONObject permission) {
        this.permission = permission;
    }

    /* (non-Javadoc)
     * @see com.dstz.bpm.api.engine.data.model.FlowData#getBpmDefinition()
     */
/*	@Override
	public IBpmDefinition getBpmDefinition() {
		return bpmDefinition;
	}
	public void setBpmDefinition(IBpmDefinition bpmDefinition) {
		this.bpmDefinition = bpmDefinition;
	}*/
    public JSONObject getData() {
        return data;
    }

    public String getDefName() {
		return defName;
	}

	public void setDefName(String defName) {
		this.defName = defName;
	}

	public void setData(JSONObject dataModel) {
        this.data = dataModel;
    }

    @Override
    public List<Button> getButtonList() {
        return buttonList;
    }

    public Map<String, BusinessData> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, BusinessData> dataMap) {
		this.dataMap = dataMap;
	}

	public void setButtonList(List<Button> list) {
        this.buttonList = list;
    }

	@Override
	public JSONObject getInitData() {
		return initData;
	}

	public void setInitData(JSONObject initData) {
		this.initData = initData;
	}
}

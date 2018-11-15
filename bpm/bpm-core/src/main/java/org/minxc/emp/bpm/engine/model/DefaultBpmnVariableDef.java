package org.minxc.emp.bpm.engine.model;

import org.minxc.emp.bpm.api.model.def.BpmVariableDef;
import org.minxc.emp.core.util.StringUtil;
import org.minxc.emp.core.util.time.DateFormatUtil;


public class DefaultBpmnVariableDef implements BpmVariableDef {
	
	private static final long serialVersionUID = 672920299278313661L;
	private String R = "";
	private String name = "";
	private String key = "";
	private String bf = "string";
	private Object bg = "";
	private boolean isRequired = false;
	private String description = "";

	public DefaultBpmnVariableDef() {
	}

	public static Object getValue(String dataType, String value) {
		if ("double".equals(dataType)) {
			return new Double(value);
		}
		if ("float".equals(dataType)) {
			return new Float(value);
		}
		if ("int".equals(dataType)) {
			if (value == null || StringUtil.isEmpty((String) value)) {
				return 0;
			}
			return new Integer(value);
		}
		if ("date".equals(dataType)) {
			return DateFormatUtil.parse((String) value, (String[]) new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"});
		}
		return value;
	}

	public DefaultBpmnVariableDef(String name, String varKey, String dataType, String defaultVal, boolean isRequired,
								  String description) {
		this.name = name;
		this.key = varKey;
		this.bf = dataType;
		this.bg = DefaultBpmnVariableDef.getValue(dataType, defaultVal);
		this.isRequired = isRequired;
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String varKey) {
		this.key = varKey;
	}

	public String getDataType() {
		return this.bf;
	}

	public void setDataType(String dataType) {
		this.bf = dataType;
	}

	public Object getDefaultVal() {
		return this.bg;
	}

	public void setDefaultVal(Object defaultVal) {
		this.bg = defaultVal;
	}

	public void setDefaultVal(String defaulVal2) {
		this.bg = DefaultBpmnVariableDef.getValue(this.bf, defaulVal2);
	}

	public boolean getIsRequired() {
		return this.isRequired;
	}

	public void setIsRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}

	public boolean isRequired() {
		return this.isRequired;
	}

	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}

	public String getDescription() {
		return this.description == null ? "" : this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNodeId() {
		return this.R;
	}

	public void setNodeId(String nodeId) {
		this.R = nodeId;
	}
}
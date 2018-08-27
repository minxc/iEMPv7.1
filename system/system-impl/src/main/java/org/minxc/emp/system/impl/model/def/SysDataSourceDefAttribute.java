package org.minxc.emp.system.impl.model.def;


import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

/**
 * 描述：SysDataSourceDef的属性字段，SysDataSource也直接复用了
 */
public class SysDataSourceDefAttribute implements Serializable {
	
	private static final long serialVersionUID = -7787597296676685099L;
	/**
     * 名字
     */
    @NotEmpty
    private String name;
    /**
     * 描述
     */
    @NotEmpty
    private String comment;
    /**
     * 参数类型
     */
    @NotEmpty
    private String type;
    /**
     * 是否必填
     */
    private boolean required;
    /**
     * 默认值
     */
    private String defaultValue;
    /**
     * 值，这个字段
     */
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

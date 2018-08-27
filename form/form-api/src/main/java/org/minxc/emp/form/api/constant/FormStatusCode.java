package org.minxc.emp.form.api.constant;

import org.minxc.emp.core.api.status.StatusCode;

/**
 * @说明 基础系统状态码定义
 */
public enum FormStatusCode implements StatusCode {

	FORM_ELEMENT_GENERATOR_ERROR("f10001","表单element解析失败"),
    /**
     * 成功
     */
    SUCCESS("200", "成功"),

    /**
     * 系统异常
     */
    SYSTEM_ERROR("500", "系统异常"),

    /**
     * 访问超时
     */
    TIMEOUT("401", "访问超时"),

    /**
     * 访问受限
     */
    NO_ACCESS("403", "访问受限"),

    /**
     * 参数校验不通过
     */
    PARAM_ILLEGAL("100", "参数校验不通过"),

    /**
     * 数据已存在
     */
    DATA_EXISTS("101", "数据已存在");

    /**
     * 编码
     */
    private String code;

    /**
     * 描述
     */
    private String desc;

    /**
     * 系统
     */
    private String system;

    FormStatusCode(String code, String description) {
        this.code = code;
        this.desc = description;
        this.system = "FORM";
    }

    @Override
    public String getCode() {
        return code;
    }


	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return desc;
	}

	
	
	@Override
	public String getApplication() {
		// TODO Auto-generated method stub
		 return system;
	}

	@Override
	public String getTenant() {
		// TODO Auto-generated method stub
		return null;
	}
}
	 

package org.minxc.emp.basis.api.constant;

import org.minxc.emp.core.api.status.StatusCode;

public enum SystemStatusCode implements StatusCode {

    SUCCESS("200", "成功"),
    SYSTEM_ERROR("500", "系统异常"),
    TIMEOUT("401", "访问超时"),
    NO_ACCESS("403", "访问受限"),
    PARAM_ILLEGAL("100", "参数校验不通过"),


    SERIALNO_EXSIT("50001", "流水号已存在"),
    SERIALNO_NO_EXSIT("50002", "流水号不存在"),;

    private String code;
    private String description;
    private String application;
    private String tenant;

    SystemStatusCode(String code, String description) {
        this.setCode(code);
        this.setDescription(description);
        this.setApplication("SYS");
        this.setTenant("");
    }

    /**
	 * @param application the application to set
	 */
	public void setApplication(String application) {
		this.application = application;
	}

	/**
	 * @param tenant the tenant to set
	 */
	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getApplication() {
		return application;
	}
	
	@Override
	public String getTenant() {
		return this.tenant;
	}

}

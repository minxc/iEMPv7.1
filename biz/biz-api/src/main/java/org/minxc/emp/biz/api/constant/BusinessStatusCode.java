package org.minxc.emp.biz.api.constant;

import org.minxc.emp.core.api.status.StatusCode;


/**
 * <pre>
 * bus层的异常码
 * </pre>
 * 
 * @author min.xianchang
 *
 */
public enum BusinessStatusCode implements StatusCode {

	PARAM_ILLEGAL("100", "参数校验不通过");

	
	

	private BusinessStatusCode(String code, String description) {
		this.code = code;
		this.description = description;
		this.application = "BIZ";
	}
	
	private String code;
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getApplication() {
		return application;
	}


	public void setApplication(String application) {
		this.application = application;
	}


	public String getTenant() {
		return tenant;
	}


	public void setTenant(String tenant) {
		this.tenant = tenant;
	}


	private String description;
	private String application;
	private String tenant;

}

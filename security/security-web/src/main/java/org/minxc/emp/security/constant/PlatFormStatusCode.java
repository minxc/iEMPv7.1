package org.minxc.emp.security.constant;


import org.minxc.emp.core.api.status.StatusCode;

public enum PlatFormStatusCode implements StatusCode {

    SUCCESS("200", "成功"),
    SYSTEM_ERROR("500", "系统异常"),
    TIMEOUT("401", "访问超时"),
    NO_ACCESS("403", "访问受限"),
    LOGIN_ERROR("405", "登录失败"),
    PARAM_ILLEGAL("100", "参数校验不通过");

    private String code;
    private String description;
    private String application;

    PlatFormStatusCode(String code, String description) {
        this.setCode(code);
        this.setDescription(description);
        this.setApplication("PLATFORM");
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
    public void setCode(String code) {
        this.code = code;
    }


    public String getApplication() {
        return application;
    }

    @Override
    public String getTenant() {
        return null;
    }
    public void setApplication(String application) {
        this.application = application;
    }

}

package org.minxc.emp.core.api.status;

/**
 * @version V1.0
 * @Title: CommonStatusCode
 * @Package: org.minxc.emp.core.api.status
 * @Description: TODO:(用一句话描述该文件做什么)
 * @author: Xianchang.min
 * @date 2018/8/24 22:14
 */

public enum CommonStatusCode implements StatusCode{

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
     * 应用
     */
    private String application;

    /**
     * 租户
     */
    private String tenant;

    CommonStatusCode(String code, String description) {
        this.code = code;
        this.desc = description;
        this.application = "i-EMP";
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return desc;
    }

    @Override
    public String getApplication() {
        return application;
    }

    @Override
    public String getTenant() {
        return null;
    }
}

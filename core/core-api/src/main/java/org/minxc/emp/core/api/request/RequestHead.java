package org.minxc.emp.core.api.request;

/**
 * 
* 项目名称：base-intf   
* 类名称：RequestHead   
* 类描述：请求头信息描述   
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午11:00:04   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午11:00:04   
* 修改备注：   
* @version  1.0  
*
 */
public class RequestHead {

    private String sourceSystem;

    private String operator;

    private String memo;

    private String ip;

    private Boolean isEncryptData;

    private String secreKey;

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Boolean getIsEncryptData() {
        return isEncryptData;
    }

    public void setIsEncryptData(Boolean isEncryptData) {
        this.isEncryptData = isEncryptData;
    }

    public String getSecreKey() {
        return secreKey;
    }

    public void setSecreKey(String secreKey) {
        this.secreKey = secreKey;
    }
}

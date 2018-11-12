package org.minxc.emp.system.impl.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.minxc.emp.core.api.model.IdModel;

/**
 * 
* 项目名称：system-impl   
* 类名称：SystemLogErrorEntity   
* 类错误日志 实体对象   
* 创建人：Xianchang.min   
* 创建时间：2018年9月4日 下午1:36:01   
* 修改人：Xianchang.min   
* 修改时间：2018年9月4日 下午1:36:01   
* 修改备注：   
* @version  1.0  
*
 */
public class SystemLogErrorEntity implements IdModel {
    
	private static final long serialVersionUID = -2444398634104398692L;
	
	
	protected String id; /* 主键 */
    protected String account; /* 登录帐号 */
    protected String ip; /* IP地址 */
    protected String url; /* URL地址 */
    protected String content; /* 内容 */
    protected String stackTrace;/*堆栈*/
    protected java.util.Date createTime; /* 创建时间 */

    public SystemLogErrorEntity() {
        super();
    }

    public SystemLogErrorEntity(String id, String account, String ip, String url,
                  String content, Date createTime) {
        super();
        this.id = id;
        this.account = account;
        this.ip = ip;
        this.url = url;
        this.content = content;
        this.createTime = createTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 返回 主键
     *
     * @return
     */
    public String getId() {
        return this.id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 返回 登录帐号
     *
     * @return
     */
    public String getAccount() {
        return this.account;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 返回 IP地址
     *
     * @return
     */
    public String getIp() {
        return this.ip;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 返回 URL地址
     *
     * @return
     */
    public String getUrl() {
        return this.url;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 返回 内容
     *
     * @return
     */
    public String getContent() {
        return this.content;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 返回 创建时间
     *
     * @return
     */
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("id", this.id)
                .append("account", this.account).append("ip", this.ip)
                .append("url", this.url).append("content", this.content)
                .append("createTime", this.createTime).toString();
    }
}
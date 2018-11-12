package org.minxc.emp.form.core.model.custdialog;


import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

/**
 * 自定义对话框树形的配置信息
 * 日期:2018年1月17日 下午8:26:42
 */
public class FormCustDialogTreeConfig implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5620050404897728162L;
	/**
     * 树的节点ID字段
     */
    @NotEmpty
    private String id;
    /**
     * 节点的父ID字段
     */
    @NotEmpty
    private String pid;
    /**
     * 父id的初始化值，加载树时使用
     */
    private String pidInitVal;
    /**
     * 父id的初始化值是否配置的是脚本
     */
    private boolean pidInitValScript;
    /**
     * 用来显示的字段
     */
    @NotEmpty
    private String showColumn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPidInitVal() {
        return pidInitVal;
    }

    public void setPidInitVal(String pidInitVal) {
        this.pidInitVal = pidInitVal;
    }

    public boolean isPidInitValScript() {
        return pidInitValScript;
    }

    public void setPidInitValScript(boolean pidInitValScript) {
        this.pidInitValScript = pidInitValScript;
    }

    public String getShowColumn() {
        return showColumn;
    }

    public void setShowColumn(String showColumn) {
        this.showColumn = showColumn;
    }

}

package org.minxc.emp.form.core.model.custdialog;


import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

/**
 * 自定义对话框的返回字段
 * 日期:2018年1月17日 下午8:26:42
 */
public class FormCustDialogReturnField implements Serializable {
	
	private static final long serialVersionUID = -312150284956281644L;
	/**
     * 字段名
     */
    @NotEmpty
    private String columnName;
    /**
     * 返回名
     */
    @NotEmpty
    private String returnName;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getReturnName() {
        return returnName;
    }

    public void setReturnName(String returnName) {
        this.returnName = returnName;
    }

}

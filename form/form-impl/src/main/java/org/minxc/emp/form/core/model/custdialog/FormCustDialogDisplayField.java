package org.minxc.emp.form.core.model.custdialog;


import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

/**
 * 描述：自定义对话框列表数据时的展示字段
 * 日期:2018年1月17日 下午8:26:42
 */
public class FormCustDialogDisplayField implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4668067172516720993L;
	/**
     * 字段名
     */
    @NotEmpty
    private String columnName;
    /**
     * 显示名
     */
    @NotEmpty
    private String showName;
    /**
     * 格式化
     */
    private String formatter;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

}

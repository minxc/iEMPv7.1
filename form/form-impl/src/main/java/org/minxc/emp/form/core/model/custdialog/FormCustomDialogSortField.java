package org.minxc.emp.form.core.model.custdialog;


import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

/**
 * 自定义对话框的排序字段
 */
public class FormCustomDialogSortField implements Serializable {
	private static final long serialVersionUID = 2642234285672777599L;
	/**
     * 字段名
     */
    @NotEmpty
    private String columnName;
    /**
     * 排序类型 com.dstz.base.api.constant.Direction.key
     */
    @NotEmpty
    private String sortType;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

}

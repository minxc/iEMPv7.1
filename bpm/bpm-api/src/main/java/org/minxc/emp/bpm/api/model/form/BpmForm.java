package org.minxc.emp.bpm.api.model.form;

import java.io.Serializable;

import org.minxc.emp.form.api.model.FormCategory;


public interface BpmForm extends Serializable{
    /**
     * 获取表单名称
     *
     * @return
     */
    String getName();

    /**
     * 设置表单名称
     *
     * @param name
     */
    void setName(String name);

    /**
     * 获取表单分类
     *
     * @return
     */
    FormCategory getType();

    /**
     * 设置表单分类
     *
     * @param type
     */
    void setType(FormCategory type);

    /**
     * 获取表单值
     * 
     * 不同类型的表单该字段的值不一样：
     * 1、INNER（在线表单）：存放表单key
     * 2、FRAME（以iframe方式嵌入的表单）：存放表单的url
     * 
     *
     * @return
     */
    String getFormValue();

    /**
     * 设置表单值
     *
     * @param formValue
     */
    void setFormValue(String formValue);

    /**
     * 表单配置是否为空。
     *
     * @return
     */
    boolean isFormEmpty();
    
    
    public String getFormHandler() ;

	public void setFormHandler(String formHandler);
	
	public String getFormHtml();
	
	public void setFormHtml(String formHtml);
}

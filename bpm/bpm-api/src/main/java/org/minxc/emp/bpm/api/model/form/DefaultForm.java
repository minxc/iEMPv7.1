package org.minxc.emp.bpm.api.model.form;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.form.api.model.FormCategory;


/**
 * 
* 项目名称：bpm-api   
* 类名称：DefaultForm   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年8月29日 下午10:29:12   
* 修改人：Xianchang.min   
* 修改时间：2018年8月29日 下午10:29:12   
* 修改备注：   
* @version  1.0  
*
 */
public class DefaultForm  implements BpmForm {
	
	private static final long serialVersionUID = 450342472521698584L;
	private String name;
    private FormCategory type;
    private String formValue;
    // url 表单 处理器
    private String formHandler;
    
    private String formHtml;
    
    private String nodeId;

    public String getFormHandler() {
    	// 只有ifream类型的表单方支持
    	if(FormCategory.INNER == type) {
    		return null;
    	}
		return formHandler;
	}

	public void setFormHandler(String formHandler) {
		this.formHandler = formHandler;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FormCategory getType() {
        return type;
    }

    public void setType(FormCategory type) {
        this.type = type;
    }

    public String getFormValue() {
        return formValue;
    }

    public void setFormValue(String formValue) {
        this.formValue = formValue;
    }


    public void setId(String id) {
    }

    public String getId() {
        return "";
    }

    public boolean isFormEmpty() {
        boolean isEmpty = StringUtils.isEmpty(formValue);
        return isEmpty;
    }

	@Override
	public String getFormHtml() {
		return formHtml;
	}

	public void setFormHtml(String formHtml) {
		this.formHtml = formHtml;
	}

}

package org.minxc.emp.bpm.api.model.def;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 流程定义扩展属性。
 */
public class BpmnDefProperties implements Serializable {

    /**
     * 流程标题规则。
     * map.put("title", bpmDefinition.getName());
     * map.put("startorName", ContextUtil.getCurrentUser().getFullname() );
     * map.put("startDate", DateUtil.getCurrentDate());
     */
	@NotBlank
    protected String subjectRule = "{发起人:startorName}在{发起时间:startDate}发起{流程标题:title}";
    /**
     * 流程描述
     */
    protected String description = "";
    /**
     * 是否允许执行人为空。
     */
    protected boolean allowExecutorEmpty = true;
    
    protected Integer supportMobile = 0;
    /**
     * 流程状态。
     */
    @NotBlank
    protected String status = BpmnDefinition.STATUS.DRAFT;

    public String getSubjectRule() {
        return subjectRule;
    }

    public void setSubjectRule(String subjectRule) {
        this.subjectRule = subjectRule;
    }

    public String getDescription() {
        if (description == null) return "";
        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public boolean isAllowExecutorEmpty() {
        return allowExecutorEmpty;
    }

    public void setAllowExecutorEmpty(boolean allowExecutorEmpty) {
        this.allowExecutorEmpty = allowExecutorEmpty;
    }

    public void setSupportMobile( Integer supportMobile) {
		this.supportMobile = supportMobile;
	}
	
	/**
	 * 返回 support_mobile_
	 * @return
	 */
	public  Integer getSupportMobile() {
		return this.supportMobile;
	}

	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

package org.minxc.emp.form.model;


import org.minxc.emp.base.core.model.BaseModelImpl;
import org.minxc.emp.base.core.util.StringUtil;

import javax.validation.constraints.NotEmpty;

/**
 * <pre>
 * 描述：表单对象
 * 作者:min.xianchang
 * 邮箱:xianchangmin@126.com
 * 日期:2018年3月16日 下午4:19:26
 * 版权:summer
 * </pre>
 */
public class FormDef extends BaseModelImpl implements org.minxc.emp.form.api.model.FormDef {
	/**
	 * 表单类型 FormType
	 */
	private String type;
    /**
     * key
     */
    @NotEmpty
    private String key;
    /**
     * 名字
     */
    @NotEmpty
    private String name;
    /**
     * 描述
     */
    private String desc;
    /**
     * 分组id
     */
    private String groupId;
    /**
     * 分组名称
     */
    private String groupName;
    /**
     * 业务对象key
     */
    private String boKey;
    /**
     * 业务对象名称
     */
    private String boName;
    /**
     * <pre>
     * 表单内容
     * </pre>
     */
    private String html;
    
    /* (non-Javadoc)
	 * @see org.minxc.emp.form.model.IFormDef#getKey()
	 */
    @Override
	public String getKey() {
        return key;
    }

    /* (non-Javadoc)
	 * @see org.minxc.emp.form.model.IFormDef#setKey(java.lang.String)
	 */
    @Override
	public void setKey(String key) {
        this.key = key;
    }

    /* (non-Javadoc)
	 * @see org.minxc.emp.form.model.IFormDef#getName()
	 */
    @Override
	public String getName() {
        return name;
    }

    /* (non-Javadoc)
	 * @see org.minxc.emp.form.model.IFormDef#setName(java.lang.String)
	 */
    @Override
	public void setName(String name) {
        this.name = name;
    }

    /* (non-Javadoc)
	 * @see org.minxc.emp.form.model.IFormDef#getDesc()
	 */
    @Override
	public String getDesc() {
        return desc;
    }

    /* (non-Javadoc)
	 * @see org.minxc.emp.form.model.IFormDef#setDesc(java.lang.String)
	 */
    @Override
	public void setDesc(String desc) {
        this.desc = desc;
    }

    /* (non-Javadoc)
	 * @see org.minxc.emp.form.model.IFormDef#getGroupId()
	 */
    @Override
	public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /* (non-Javadoc)
	 * @see org.minxc.emp.form.model.IFormDef#getGroupName()
	 */
    @Override
	public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /* (non-Javadoc)
	 * @see org.minxc.emp.form.model.IFormDef#getBoKey()
	 */
    @Override
	public String getBoKey() {
        return boKey;
    }

    /* (non-Javadoc)
	 * @see org.minxc.emp.form.model.IFormDef#setBoKey(java.lang.String)
	 */
    @Override
	public void setBoKey(String boKey) {
        this.boKey = boKey;
    }

    /* (non-Javadoc)
	 * @see org.minxc.emp.form.model.IFormDef#getBoName()
	 */
    @Override
	public String getBoName() {
        return boName;
    }

    public void setBoName(String boName) {
        this.boName = boName;
    }

    /* (non-Javadoc)
	 * @see org.minxc.emp.form.model.IFormDef#getHtml()
	 */
    @Override
	public String getHtml() {
    	if(StringUtil.isNotEmpty(html)) { 
    		String content = html.replaceAll("&apos;", "'").replaceAll("&#39;", "'")
    							.replaceAll("#ctx#", "ctx");
    		return content;
    	}//&#39;
    	
        return html;
    }

    /* (non-Javadoc)
	 * @see org.minxc.emp.form.model.IFormDef#setHtml(java.lang.String)
	 */
    @Override
	public void setHtml(String html) {
        this.html = html;
    }
    
    @Override
	public String getType() {
		return type;
	}
    
    @Override
	public void setType(String type) {
		this.type = type;
	}

}

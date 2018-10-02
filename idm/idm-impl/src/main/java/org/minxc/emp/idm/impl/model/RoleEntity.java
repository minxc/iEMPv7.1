package org.minxc.emp.idm.impl.model;

import org.minxc.emp.core.impl.model.AbstractCommonModel;
import org.minxc.emp.idm.api.constant.GroupTypeConstant;
import org.minxc.emp.idm.api.model.GroupStructEnum;
import org.minxc.emp.idm.api.model.Group;
import org.minxc.emp.idm.api.model.IdentityType;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;


/**
 * 角色管理 实体对象
 */
public class RoleEntity extends AbstractCommonModel implements Group {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3960835577784897473L;

	/**
     * id_
     */
    protected String id;

    /**
     * 角色名称
     */
    protected String name;

    /**
     * 角色别名
     */
    protected String alias;

    /**
     * 0：禁用，1：启用
     */
    protected Integer enabled;

    /**
     * 角色描述
     */
    protected String description = "";


    public void setId(String id) {
        this.id = id;
    }

    /**
     * 返回 id_
     *
     * @return
     */
    public String getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 返回 角色名称
     *
     * @return
     */
    public String getName() {
        return this.name;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * 返回 角色别名
     *
     * @return
     */
    public String getAlias() {
        return this.alias;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    /**
     * 返回 0：禁用，1：启用
     *
     * @return
     */
    public Integer getEnabled() {
        return this.enabled;
    }

    /**
     * @see Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", this.id)
                .append("name", this.name)
                .append("alias", this.alias)
                .append("enabled", this.enabled)
                .toString();
    }

    public String getIdentityType() {
        return IdentityType.GROUP;
    }

    public String getGroupId() {
        return this.id;
    }

    public String getGroupCode() {

        return this.alias;
    }

    public Long getSeq() {
        return Long.valueOf(1);
    }

    public String getGroupType() {
        return GroupTypeConstant.ROLE.key();
    }

    public GroupStructEnum getStruct() {
        return GroupStructEnum.PLAIN;
    }

    public String getParentId() {
        return "";
    }

    public String getPath() {
        return this.name;
    }

    public Map<String, Object> getParams() {

        return null;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
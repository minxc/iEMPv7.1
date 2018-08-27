package org.minxc.emp.idm.impl.model;

import com.minxc.emp.core.impl.model.AbstractCommonModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import org.minxc.emp.idm.api.model.UserRole;


/**
 * 用户角色管理 实体对象
 */
public class UserRoleEntity extends AbstractCommonModel implements UserRole {

	private static final long serialVersionUID = -5985721994440075379L;

	/**
     * role_id_
     */
    protected String roleId;

    /**
     * user_id_
     */
    protected String userId;
    /**
     * 以下是扩展字段，用于关联显示。
     */

    //用户名
    protected String fullname;
    // 角色名称
    protected String roleName;
    //角色别名
    protected String alias;
    //账号
    protected String account = "";

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * 返回 role_id_
     *
     * @return
     */
    public String getRoleId() {
        return this.roleId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 返回 user_id_
     *
     * @return
     */
    public String getUserId() {
        return this.userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @see Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", this.id)
                .append("roleId", this.roleId)
                .append("userId", this.userId)
                .toString();
    }
}
package org.minxc.emp.system.impl.model;

import org.minxc.emp.core.api.model.IdModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 *角色资源分配 实体对象
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RoleResouceLinkEntity implements IdModel {

	private static final long serialVersionUID = -6104620361978799823L;

	/**
     * 主键
     */
    protected String id;

    /**
     * 系统ID
     */
    protected String applicationId;

    /**
     * 资源ID
     */
    protected String resId;

    /**
     * 角色ID
     */
    protected String roleId;

    /**
     * 角色别名。
     */
    protected String roleAlias;
    /**
     * 资源url连接。
     */
    protected String url;

    /**
     * 资源别名。
     */
    protected String resAlias;


    
}
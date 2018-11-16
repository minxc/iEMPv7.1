package org.minxc.emp.system.impl.model;

import org.minxc.emp.core.impl.model.AbstractCommonModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=true)
public class BusinessAuthorizationEntity extends AbstractCommonModel {

	private static final long serialVersionUID = -9041027522795386765L;
	public static final String RIGHT_TYPE_USER = "user";
    public static final String RIGHT_TYPE_ALL = "all";

    /**
     * id
     */
    protected String rightsId;

    /**
     * 授权对象
     **/
    protected String rightsObject;

    /**
     * 授权目标
     */
    protected String rightsTarget;

    /**
     * 权限类型
     */
    protected String rightsType;

    /**
     * 授权标识
     */
    protected String rightsIdentity;

    /**
     * 标识名字
     */
    protected String rightsIdentityName;

    /**
     * 授权code=identity+type
     */
    protected String rightsPermissionCode;

    /**
     * 创建时间
     */
    protected java.util.Date rightsCreateTime;

    /**
     * 创建人
     */
    protected String rightsCreateBy;

    
}
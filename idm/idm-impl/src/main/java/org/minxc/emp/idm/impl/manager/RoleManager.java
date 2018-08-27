package org.minxc.emp.idm.impl.manager;

import org.minxc.emp.idm.impl.model.RoleEntity;
import org.minxc.emp.common.manager.Manager;

import java.util.List;

/**
 * 角色管理 处理接口
 */
public interface RoleManager extends Manager<String, RoleEntity> {

	RoleEntity getByAlias(String alias);

	/**
	 * 根据用户ID获取角色列表
	 *
	 * @param userId
	 * @return
	 */
	List<RoleEntity> getListByUserId(String userId);

	/**
	 * 判断角色是否存在。
	 *
	 * @param role
	 * @return
	 */
	boolean isRoleExist(RoleEntity role);

}

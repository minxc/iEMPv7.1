package org.minxc.emp.idm.impl.manager;

import org.minxc.emp.idm.impl.model.UserRoleModel;
import org.minxc.emp.common.manager.Manager;

import java.util.List;

/**
 * 用户角色管理 处理接口
 */
public interface UserRoleManager extends Manager<String, UserRoleModel> {

	/**
	 * 根据用户和角色id 查询 关联关系。
	 *
	 * @param roleId
	 * @param userId
	 * @return
	 */
	UserRoleModel getByRoleIdUserId(String roleId, String userId);

	/**
	 * 获取用户的角色。
	 *
	 * @param userId
	 * @return
	 */
	List<UserRoleModel> getListByUserId(String userId);

	/**
	 * 根据角色ID查询关联的用户。
	 *
	 * @param roleId
	 * @return
	 */
	List<UserRoleModel> getListByRoleId(String roleId);

	/**
	 * 根据角色别名查询关联的用户。
	 *
	 * @param roleId
	 * @return
	 */
	List<UserRoleModel> getListByAlias(String alias);
}

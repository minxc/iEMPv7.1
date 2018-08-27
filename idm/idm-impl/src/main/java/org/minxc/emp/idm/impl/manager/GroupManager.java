package org.minxc.emp.idm.impl.manager;

import org.minxc.emp.idm.impl.model.GroupEntity;
import org.minxc.emp.common.manager.Manager;

import java.util.List;

/**
 * 描述：组织架构 处理接口
 */
public interface GroupManager extends Manager<String, GroupEntity> {
	/**
	 * 根据Code取定义对象。
	 *
	 * @param code
	 * @return
	 */
	GroupEntity getByCode(String code);

	/**
	 * 根据用户ID获取组织列表
	 *
	 * @param userId
	 * @return
	 */
	List<GroupEntity> getByUserId(String userId);

	/**
	 * 获取主组织。
	 *
	 * @param userId
	 * @return
	 */
	GroupEntity getMainGroup(String userId);
}

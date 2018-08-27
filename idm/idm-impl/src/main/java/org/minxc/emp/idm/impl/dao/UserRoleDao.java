package org.minxc.emp.idm.impl.dao;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.idm.impl.model.UserRoleEntity;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.minxc.emp.common.db.dao.CommonDao;

/**
 * 描述：用户角色管理 DAO接口
 */
@Mapper
public interface UserRoleDao extends CommonDao<String, UserRoleEntity> {

	/**
	 * 根据用户和角色id 查询 关联关系。
	 *
	 * @param roleId
	 * @param userId
	 * @return
	 */
	UserRoleEntity getByRoleIdUserId(@Param("roleId") String roleId, @Param("userId") String userId);

	List<UserRoleEntity> queryByParams(@Param("roleId") String roleId, @Param("userId") String userId,
			@Param("alias") String alias);
}

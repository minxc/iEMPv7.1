package org.minxc.emp.idm.impl.dao;

import org.apache.ibatis.annotations.Mapper;
import org.minxc.emp.idm.impl.model.UserEntity;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.core.api.query.QueryFilter;

/**
 * 描述：用户表 DAO接口
 */
@Mapper
public interface UserDao extends CommonDao<String, UserEntity> {
	/**
	 * 根据Account取定义对象。
	 *
	 * @param account
	 * @return
	 */
	UserEntity getByAccount(String account);

	/**
	 * 不含用户组织关系
	 *
	 * @param queryFilter
	 * @return
	 */
	List<UserEntity> queryOrgUser(QueryFilter queryFilter);

	/**
	 * 根据岗位编码获取用户列表
	 *
	 * @param relCode
	 * @return
	 */
	List<UserEntity> getListByRelCode(String relCode);

	/**
	 * 根据角色获取用户列表
	 *
	 * @param roleId
	 * @return
	 */
	List<UserEntity> getUserListByRole(@Param("roleId") String roleId, @Param("roleCode") String roleCode);

	/**
	 * 判断用户是否存在。
	 *
	 * @param user
	 * @return
	 */
	Integer isUserExist(UserEntity user);

	List getUserListByRelId(String relId);

	List queryUserGroupRel(QueryFilter queryFilter);

	List<UserEntity> getUserListByOrgId(String orgId);
}

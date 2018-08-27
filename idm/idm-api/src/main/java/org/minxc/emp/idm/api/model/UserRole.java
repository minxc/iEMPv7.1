package org.minxc.emp.idm.api.model;

public interface UserRole {

	String getAlias();


	String getFullname();


	String getRoleName();


	/**
	 * 返回 role_id_
	 *
	 * @return
	 */
	String getRoleId();


	/**
	 * 返回 user_id_
	 *
	 * @return
	 */
	String getUserId();

	String getAccount();
}
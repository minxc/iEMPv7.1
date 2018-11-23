package org.minxc.emp.idm.impl.model;

import org.minxc.emp.core.impl.model.AbstractCommonModel;

import lombok.ToString;

/**
 * 用户组织关系 实体对象
 */
@ToString
public class GroupUserLinkModel extends AbstractCommonModel {

	private static final long serialVersionUID = -3921358687321165230L;

	/**
	 * 主关系
	 */
	public static final Integer MASTER_YES = 1;

	/**
	 * 非主关系
	 */
	public static final Integer MASTER_NO = 0;

	
	protected String id;

	protected String groupId;

	protected String userId;

	/**
	 * 0:非主部门，1：主部门
	 */
	protected Integer isMaster;

	/**
	 * 岗位Id
	 */
	protected String positionId;

	public GroupUserLinkModel(String groupId, String userId, String positionId) {
		this.groupId = groupId;
		this.userId = userId;
		this.positionId = positionId;
		this.isMaster = 0;
	}

	public GroupUserLinkModel() {
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setIsMaster(Integer isMaster) {
		this.isMaster = isMaster;
	}

	/**
	 * 返回 0:非主部门，1：主部门
	 *
	 * @return
	 */
	public Integer getIsMaster() {
		return this.isMaster;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

}
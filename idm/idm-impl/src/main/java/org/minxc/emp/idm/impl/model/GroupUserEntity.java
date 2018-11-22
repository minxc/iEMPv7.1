package org.minxc.emp.idm.impl.model;

import org.minxc.emp.core.impl.model.AbstractCommonModel;

import lombok.ToString;


/**
 * 用户组织关系 实体对象
 */
@ToString
public class GroupUserEntity extends AbstractCommonModel {

	private static final long serialVersionUID = -3921358687321165230L;

	/**
     * 主关系
     */
    public static final Integer MASTER_YES = 1;

    /**
     * 非主关系
     */
    public static final Integer MASTER_NO = 0;


    /**
     * id_
     */
    protected String id;

    /**
     * org_id_
     */
    protected String groupId;

    /**
     * user_id_
     */
    protected String userId;

    /**
     * 0:非主部门，1：主部门
     */
    protected Integer isMaster;

    /**
     * rel_id_
     */
    protected String relId;


    public GroupUserEntity(String groupId, String userId, String relId) {
        this.groupId = groupId;
        this.userId = userId;
        this.relId = relId;
        this.isMaster = 0;
    }

    public GroupUserEntity() {
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
     * 	返回 0:非主部门，1：主部门
     *
     * @return
     */
    public Integer getIsMaster() {
        return this.isMaster;
    }

    public void setRelId(String relId) {
        this.relId = relId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * 返回 rel_id_
     *
     * @return
     */
    public String getRelId() {
        return this.relId;
    }


}
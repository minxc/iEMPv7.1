package org.minxc.emp.idm.api.model;

import java.io.Serializable;



/**
 * 描述：抽象用户组类型
 */
public interface Group extends IdentityType,Serializable {


    /**
     * 组织ID
     *
     * @return
     */
    String getGroupId();

    /**
     * 组织名称
     *
     * @return
     */
    String getName();

    /**
     * 组织编码
     *
     * @return
     */
    String getGroupCode();

    /**
     * 组排序
     *
     * @return
     */
    Long getSn();

    /**
     * 组织类型。
     * 比如：org,role,pos
     *
     * @return
     */
    String getGroupType();

    /**
     * 组织结构。
     *
     * @return
     */
    GroupStructEnum getStruct();

    /**
     * 上级ID
     *
     * @return
     */
    String getParentId();

    //路径 例如xxx.xxxx
    String getPath();

}

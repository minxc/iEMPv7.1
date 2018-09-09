package org.minxc.emp.idm.api.model;

/*
 * 
* 项目名称：idm-api   
* 类名称：Group   
* 类描述： 抽象用户组类型 
* 创建人：Xianchang.min   
* 创建时间：2018年9月4日 下午11:43:26   
* 修改人：Xianchang.min   
* 修改时间：2018年9月4日 下午11:43:26   
* 修改备注：   
* @version  1.0  
*
 */
public interface Group extends IdentityType {


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
    Long getSeq();

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

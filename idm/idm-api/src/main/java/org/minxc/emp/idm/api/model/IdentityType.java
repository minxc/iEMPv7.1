package org.minxc.emp.idm.api.model;

import java.io.Serializable;

/**
 * 
* 项目名称：idm-api   
* 类名称：IdentityType   
* 类描述：  用户标识类型
* 创建人：Xianchang.min   
* 创建时间：2018年9月4日 下午11:42:56   
* 修改人：Xianchang.min   
* 修改时间：2018年9月4日 下午11:42:56   
* 修改备注：   
* @version  1.0  
*
 */
public interface IdentityType extends Serializable {

    /**
     * 用户
     */
    public static final String USER = "user";

    /**
     * 用户组
     */
    public static final String GROUP = "group";

    /**
     * 返回用户标识类型
     *
     * @return String
     */
    String getIdentityType();


}

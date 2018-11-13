package org.minxc.emp.core.api.query;

/**
 * 
* 项目名称：base-intf   
* 类名称：WhereClause   
* 类   创建SQL语句中的Where条件组件的SQL片段
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午10:53:38   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午10:53:38   
* 修改备注：   
* @version  1.0  
*
 */

public interface WhereClause {

    /**
     * 返回SQL片段
     */
    public String getSql();
}

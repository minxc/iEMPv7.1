package org.minxc.emp.core.api.query;

import java.util.List;

/*
 * 
* 项目名称：base-intf   
* 类名称：FieldLogic   
* 类描述：   字段条件组合查询
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午10:55:24   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午10:55:24   
* 修改备注：   
* @version  1.0  
*
 */
public interface FieldLogic extends WhereClause {

    public List<WhereClause> getWhereClauses();
}

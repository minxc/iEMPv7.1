package org.minxc.emp.core.api.query;


/*
 * 
* 项目名称：base-intf   
* 类名称：QueryField   
* 类描述：   查询字段接口类
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午10:56:47   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午10:56:47   
* 修改备注：   
* @version  1.0  
*
 */
public interface QueryField extends WhereClause {
    /**
     * 返回字段名
     *
     * @return
     */
    public String getField();

    /**
     * 比较符
     *
     * @return
     */
    public QueryOperator getCompare();

    /**
     * 返回值
     *
     * @return
     */
    public Object getValue();

}

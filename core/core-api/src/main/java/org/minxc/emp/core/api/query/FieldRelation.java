package org.minxc.emp.core.api.query;


/*
 * 
* 项目名称：base-intf   
* 类名称：FieldRelation   
* 类描述： 查询字段之间的关系枚举  
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午10:55:42   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午10:55:42   
* 修改备注：   
* @version  1.0  
*
 */
public enum FieldRelation {

    AND("AND"), OR("OR"), NOT("NOT");
    private String val;

    FieldRelation(String _val) {
        val = _val;
    }

    public String value() {
        return val;
    }
}

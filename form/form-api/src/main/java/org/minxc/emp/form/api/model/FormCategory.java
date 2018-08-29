package org.minxc.emp.form.api.model;


/**
 * 
* 项目名称：form-api   
* 类名称：FormCategory   
* 类描述：   表单类型
* 创建人：Xianchang.min   
* 创建时间：2018年8月29日 下午10:24:01   
* 修改人：Xianchang.min   
* 修改时间：2018年8月29日 下午10:24:01   
* 修改备注：   
* @version  1.0  
*
 */
public enum FormCategory {
    INNER("inner"),
    FRAME("frame");

    private final String value;

    FormCategory(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FormCategory fromValue(String v) {
        for (FormCategory c : FormCategory.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

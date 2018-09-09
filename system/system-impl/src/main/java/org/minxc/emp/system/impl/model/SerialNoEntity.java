package org.minxc.emp.system.impl.model;


import org.minxc.emp.core.api.model.IdModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author Xianchang.min
 *
 */

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=false)
public class SerialNoEntity implements IdModel{
	
	private static final long serialVersionUID = -2719000242073544956L;
	protected String id; /*主键*/
    protected String name; /*名称*/
    protected String alias; /*别名*/
    protected String rule; /*规则*/
    protected Short genType; /*生成类型*/
    protected Integer noLength; /*流水号长度*/
    protected String curDate; /*当前日期*/
    protected Integer initValue; /*初始值*/
    protected Integer curValue = 0; /*当前值*/
    protected Integer step; /*步长*/

    /**
     * 新的流水号。
     */
    protected Integer newCurValue =  0;

    /**
     * 预览流水号。
     */
    protected String curIdenValue = "";



    
}
package org.minxc.emp.system.impl.model;


import com.minxc.emp.core.impl.model.AbstractCommonModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=true)
public class ScriptEntity extends AbstractCommonModel implements Cloneable {

    private static final long serialVersionUID = 1L;
    protected String name;        /*名称*/
    protected String script;    /*脚本*/
    protected String category;    /*脚本分类*/
    protected String notes;        /*备注*/

}

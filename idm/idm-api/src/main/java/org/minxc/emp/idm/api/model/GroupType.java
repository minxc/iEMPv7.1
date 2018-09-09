package org.minxc.emp.idm.api.model;

/**
 * 
* 项目名称：idm-api   
* 类名称：GroupType   
* 类描述：组织类型   
* 创建人：Xianchang.min   
* 创建时间：2018年9月4日 下午11:44:45   
* 修改人：Xianchang.min   
* 修改时间：2018年9月4日 下午11:44:45   
* 修改备注：   
* @version  1.0  
*
 */
public class GroupType {

    /**
     * 别名
     */
    private String alias = "";

    /**
     * 名称
     */
    private String name = "";

    public GroupType() {
    }

    public GroupType(String alias, String name) {
        this.alias = alias;
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

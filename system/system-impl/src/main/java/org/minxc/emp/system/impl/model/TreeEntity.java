package org.minxc.emp.system.impl.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.minxc.emp.core.impl.model.AbstractCommonModel;

/**
 * 系统树
 */
public class TreeEntity extends AbstractCommonModel {
	private static final long serialVersionUID = 7359943904089818902L;
	/**
     * 别名
     */
    @NotEmpty
    private String key;
    /**
     * 名字
     */
    @NotEmpty
    private String name;
    /**
     * 描述
     */
    private String desc;
    /**
     * 是否系统内置树
     */
    private boolean system;

    // 以下字段与数据库无关
    /**
     * 树的顶部节点
     */
    private List<SystemTreeNodeEntity> nodes;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isSystem() {
        return system;
    }

    public void setSystem(boolean system) {
        this.system = system;
    }

    public List<SystemTreeNodeEntity> getNodes() {
        return nodes;
    }

    public void setNodes(List<SystemTreeNodeEntity> nodes) {
        this.nodes = nodes;
    }

}

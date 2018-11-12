package org.minxc.emp.system.api.model;

public interface SystemTreeNode {
    /**
     * 主键
     * @return
     */
    String getId();

    /**
     * 别名
     * @return
     */
    String getKey();

    /**
     * 名字
     * @return
     */
    String getName();

    /**
     * 描述
     * @return
     */
    String getDesc();

    /**
     * 父ID
     * @return
     */
    String getParentId();

    /**
     * 路径 eg:pppid.ppid.pid
     * @return
     */
    String getPath();

    /**
     * 排序号
     * @return
     */
    int getSn();

    /**
     * 所属树id
     * @return
     */
    String getTreeId();
}

package org.minxc.emp.system.api.model;

/**
 * 
 * 系统树节点SysTreeNode接口类
 * 
 * 
 * 日期:2018年3月28日 下午3:27:50
 * 
 * 
 */
public interface SystemTreeNode {
    /**
     * 
     * 主键
     * 
     *
     * @return
     */
    String getId();

    /**
     * 
     * 别名
     * 
     *
     * @return
     */
    String getKey();

    /**
     * 
     * 名字
     * 
     *
     * @return
     */
    String getName();

    /**
     * 
     * 描述
     * 
     *
     * @return
     */
    String getDesc();

    /**
     * 
     * 父ID
     * 
     *
     * @return
     */
    String getParentId();

    /**
     * 
     * 路径 eg:pppid.ppid.pid
     * 
     *
     * @return
     */
    String getPath();

    /**
     * 
     * 排序号
     * 
     *
     * @return
     */
    int getSn();

    /**
     * 
     * 所属树id
     * 
     *
     * @return
     */
    String getTreeId();
}

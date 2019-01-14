package org.minxc.emp.core.api.model;

import java.util.List;

/**
 *
 * 项目名称：
 * 类名称：TreeModel
 * 类树结构对象，用于将列表数据转换成树结构。 C类型参数声明子数据的类型，那么在实现类中就能直接使用实现类作为children
 * 创建人：Xianchang.min
 * 创建时间：2018年8月21日 下午10:52:23
 * 修改人：Xianchang.min
 * 修改时间：2018年8月21日 下午10:52:23
 * 修改备注：
 * @version  1.0
 *
 */
public interface TreeModel<T extends TreeModel<?>>  {

    /**
     * 主键ID
     *
     * @return
     */
    String getId();

    /**
     * 父ID
     *
     * @return
     */
    String getParentId();

    /**
     * 子对象。
     *
     * @return
     */
    List<T> getChildren();

    /**
     * 设置子对象。
     *
     * @param list
     */
    void setChildren(List<T> list);
}

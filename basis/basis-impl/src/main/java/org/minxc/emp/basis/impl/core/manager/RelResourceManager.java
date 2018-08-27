package org.minxc.emp.basis.impl.core.manager;


import java.util.List;

import org.minxc.emp.basis.impl.core.model.RelResource;
import org.minxc.emp.common.manager.Manager;

/**
 * 关联资源 处理接口
 */
public interface RelResourceManager extends Manager<String, RelResource> {
    /**
     * 根据资源ID获得其拥有的URL
     *
     * @return
     */
    List<RelResource> getByResourceId(String resId);

    /**
     * 根据资源ID删除关联资源。
     *
     * @param resId
     */
    void removeByResId(String resId);
}

package org.minxc.emp.system.impl.manager;


import java.util.List;

import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.system.impl.model.ResourceLinkEntity;

/**
 * 关联资源 处理接口
 */
public interface RelResourceManager extends Manager<String, ResourceLinkEntity> {
    /**
     * 根据资源ID获得其拥有的URL
     *
     * @return
     */
    List<ResourceLinkEntity> getByResourceId(String resId);

    /**
     * 根据资源ID删除关联资源。
     *
     * @param resId
     */
    void removeByResId(String resId);
}

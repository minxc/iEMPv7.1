package org.minxc.emp.system.impl.manager;

import java.util.List;

import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.system.impl.model.SystemResourceEntity;

/**
 *  子系统资源 处理接口
 */
public interface SystemResourceManager extends Manager<String, SystemResourceEntity> {

    /**
     * 根据子系统ID获取实体列表。
     */
    List<SystemResourceEntity> getBySystemId(String id);

    /**
     * 根据资源ID获取资源对象，包括关联资源数据。
     *
     * @param id
     * @return
     */
    SystemResourceEntity getByResId(String id);

    /**
     * 根据资源id获取包括自身的下级数据。
     *
     * @param resId
     * @return
     */
    List<SystemResourceEntity> getRecursionById(String resId);

    /**
     * 根据系统和角色ID获取资源。
     *
     * @param systemId
     * @param roleId
     * @return
     */
    List<SystemResourceEntity> getBySystemAndRole(String systemId, String roleId);

    /**
     * 判断资源是否存在。
     *
     * @param resource
     * @return
     */
    boolean isExist(SystemResourceEntity resource);

    /**
     * 根据资源id递归删除资源数据。
     *
     * @param resId
     */
    void removeByResId(String resId);

    /**
     * 根据系统id和用户id获取资源。
     *
     * @param systemId
     * @param userId
     * @return
     */
    List<SystemResourceEntity> getBySystemAndUser(String systemId, String userId);
}

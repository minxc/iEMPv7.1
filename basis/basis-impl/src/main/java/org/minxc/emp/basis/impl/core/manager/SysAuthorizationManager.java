package org.minxc.emp.basis.impl.core.manager;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.minxc.emp.basis.api.constant.RightsObjectConstants;
import org.minxc.emp.basis.impl.core.model.SysAuthorization;
import org.minxc.emp.common.manager.Manager;


public interface SysAuthorizationManager extends Manager<String, SysAuthorization> {

    Set<String> getUserRights(String userId);

    /**
     * 获取授权sql
     *
     * @param userId    用户id
     * @param targetKey 默认为Id_ 关联authorization的 targetId在数据库中的字段名字，
     * @return
     */
    Map<String, Object> getUserRightsSql(RightsObjectConstants rightsObject, String userId, String targetKey);

    List<SysAuthorization> getByTarget(RightsObjectConstants rightsObject, String rightsTarget);


    void createAll(List<SysAuthorization> sysAuthorizationList, String targetId, String targetObject);

}

package org.minxc.emp.idm.impl.manager.impl;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.api.query.QueryFilter;
import org.springframework.stereotype.Service;

import org.minxc.emp.idm.impl.dao.GroupRelationDao;
import org.minxc.emp.idm.impl.manager.GroupRelationManager;
import org.minxc.emp.idm.impl.model.GroupRelationEntity;

/**
 * <pre>
 * 描述：组织关联关系 处理实现类
 * </pre>
 */
@Service("groupRelationManager")
public class GroupRelationManagerImpl extends CommonManager<String, GroupRelationEntity> implements GroupRelationManager {
    @Resource
    GroupRelationDao orgRelDao;

    public GroupRelationEntity getByCode(String code) {
        return this.orgRelDao.getByCode(code);
    }

    public List<GroupRelationEntity> getListByGroupId(String groupId) {
        return this.orgRelDao.getListByGroupId(groupId);
    }

    public List<GroupRelationEntity> queryInfoList(QueryFilter queryFilter) {
        return this.orgRelDao.queryInfoList(queryFilter);
    }

    public List<GroupRelationEntity> getListByUserId(String userId) {
        if (StringUtils.isEmpty(userId)) return Collections.emptyList();
        return this.orgRelDao.getRelListByParam(null, userId);
    }

    public List<GroupRelationEntity> getListByAccount(String account) {
        if (StringUtils.isEmpty(account)) return Collections.emptyList();
        return this.orgRelDao.getRelListByParam(account, null);
    }
}

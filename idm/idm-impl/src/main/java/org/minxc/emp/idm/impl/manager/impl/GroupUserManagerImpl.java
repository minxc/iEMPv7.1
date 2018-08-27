package org.minxc.emp.idm.impl.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.minxc.emp.common.manager.impl.CommonManager;
import org.springframework.stereotype.Service;

import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.core.cache.ICache;
import com.dstz.base.core.util.BeanUtils;
import com.dstz.base.core.util.StringUtil;
import com.dstz.base.manager.impl.BaseManager;
import org.minxc.emp.idm.api.context.CurrentContext;
import org.minxc.emp.idm.impl.dao.GroupUserDao;
import org.minxc.emp.idm.impl.manager.GroupUserManager;
import org.minxc.emp.idm.impl.model.GroupUserEntity;

/**
 * <pre>
 * 描述：用户组织关系 处理实现类
 * </pre>
 */
@Service("groupUserManager")
public class GroupUserManagerImpl extends CommonManager<String, GroupUserEntity> implements GroupUserManager {
    @Resource
    GroupUserDao groupUserDao;

    @Resource
    ICache iCache;


    public int updateUserPost(String id, String relId) {
        return groupUserDao.updateUserPost(id, relId);
    }

    public GroupUserEntity getGroupUser(String orgId, String userId, String relId) {
        return groupUserDao.getGroupUser(orgId, userId, relId);
    }

    public List<GroupUserEntity> getListByGroupIdUserId(String orgId, String userId) {
        return groupUserDao.getListByGroupIdUserId(orgId, userId);
    }

    public int removeByOrgIdUserId(String orgId, String userId) {
        return groupUserDao.removeByGroupIdUserId(orgId, userId);
    }

    public void setMaster(String id) {
        GroupUserEntity orgUser = this.get(id);
        if (orgUser.getIsMaster() == 0) {
            groupUserDao.cancelUserMasterGroup(orgUser.getUserId());
            groupUserDao.setMaster(id);
        } else {
            orgUser.setIsMaster(0);
            groupUserDao.update(orgUser);
        }

        //删除缓存。
        String userKey = CurrentContext.CURRENT_ORG + orgUser.getUserId();
        iCache.delByKey(userKey);
    }


    public GroupUserEntity getGroupUserMaster(String userId) {
        return groupUserDao.getGroupUserMaster(userId);
    }

    @Override
    public List getUserByGroup(QueryFilter queryFilter) {
        return groupUserDao.getUserByGroup(queryFilter);
    }


    @Override
    public void saveGroupUserRel(String groupId, String[] userIds, String[] relIds) {
        for (String userId : userIds) {
            if (StringUtil.isEmpty(userId)) continue;
            //没有选择岗位情况。仅仅加入组
            if (BeanUtils.isEmpty(relIds)) {
                List<GroupUserEntity> list = groupUserDao.getListByGroupIdUserId(groupId, userId);
                if (BeanUtils.isNotEmpty(list)) continue;

                GroupUserEntity user = new GroupUserEntity(groupId, userId, null);
                groupUserDao.create(user);
                continue;
            }

            for (String relId : relIds) {
                if (StringUtil.isEmpty(relId)) continue;

                GroupUserEntity groupUser = groupUserDao.getGroupUser(groupId, userId, relId);
                if (groupUser != null) continue;

                groupUser = new GroupUserEntity(groupId, userId, relId);
                groupUserDao.create(groupUser);
            }
        }
    }

}

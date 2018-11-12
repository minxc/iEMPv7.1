package org.minxc.emp.system.impl.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.system.impl.dao.RelResourceDao;
import org.minxc.emp.system.impl.manager.RelResourceManager;
import org.minxc.emp.system.impl.model.ResourceLinkEntity;
import org.springframework.stereotype.Service;

/**
 * 
 * 关联资源 处理实现类
 * 
 */
@Service("relResourceManager")
public class RelResourceManagerImpl extends CommonManager<String, ResourceLinkEntity> implements RelResourceManager {
    @Resource
    RelResourceDao relResourceDao;

    @Override
    public List<ResourceLinkEntity> getByResourceId(String resId) {
        return relResourceDao.getByResourceId(resId);
    }

    @Override
    public void removeByResId(String resId) {
        relResourceDao.removeByResId(resId);
    }

}

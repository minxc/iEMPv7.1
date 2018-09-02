package org.minxc.emp.system.impl.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.system.impl.dao.RelResourceDao;
import org.minxc.emp.system.impl.manager.RelResourceManager;
import org.minxc.emp.system.impl.model.RelResource;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * 描述：关联资源 处理实现类
 * </pre>
 */
@Service("relResourceManager")
public class RelResourceManagerImpl extends CommonManager<String, RelResource> implements RelResourceManager {
    @Resource
    RelResourceDao relResourceDao;

    @Override
    public List<RelResource> getByResourceId(String resId) {
        return relResourceDao.getByResourceId(resId);
    }

    @Override
    public void removeByResId(String resId) {
        relResourceDao.removeByResId(resId);
    }

}

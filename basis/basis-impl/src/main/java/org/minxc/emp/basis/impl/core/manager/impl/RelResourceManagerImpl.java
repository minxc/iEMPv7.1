package org.minxc.emp.basis.impl.core.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.minxc.emp.basis.impl.core.dao.RelResourceDao;
import org.minxc.emp.basis.impl.core.manager.RelResourceManager;
import org.minxc.emp.basis.impl.core.model.RelResource;
import org.minxc.emp.common.manager.impl.CommonManager;
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

package org.minxc.emp.system.impl.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.minxc.emp.common.db.id.UniqueIdUtil;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.system.impl.dao.RelResourceDao;
import org.minxc.emp.system.impl.dao.SysResourceDao;
import org.minxc.emp.system.impl.manager.SysResourceManager;
import org.minxc.emp.system.impl.model.RelResource;
import org.minxc.emp.system.impl.model.SystemResourceEntity;
import org.springframework.stereotype.Service;

import com.minxc.emp.core.util.BeanUtils;

/**
 * <pre>
 * 描述：子系统资源 处理实现类
 * </pre>
 */
@Service("sysResourceManager")
public class SysResourceManagerImpl extends CommonManager<String, SystemResourceEntity> implements SysResourceManager {
    @Resource
    SysResourceDao sysResourceDao;

    @Resource
    RelResourceDao relResourceDao;


    @Override
    public List<SystemResourceEntity> getBySystemId(String id) {
        List<SystemResourceEntity> list = sysResourceDao.getBySystemId(id);

        return list;
    }

    @Override
    public SystemResourceEntity getByResId(String id) {
        SystemResourceEntity sysResource = this.get(id);
        sysResource.setRelResources(relResourceDao.getByResourceId(id));
        return sysResource;
    }

    @Override
    public void create(SystemResourceEntity sysResource) {
        String resId = UniqueIdUtil.getSuid();
        sysResource.setId(resId);
        //先删除
        relResourceDao.removeByResId(resId);
        //在添加
        List<RelResource> relResources = sysResource.getRelResources();
        for (RelResource relResource : relResources) {
            relResource.setId(UniqueIdUtil.getSuid());
            relResource.setResId(resId);
            relResourceDao.create(relResource);
        }
        super.create(sysResource);
    }

    @Override
    public void update(SystemResourceEntity sysResource) {
        String resId = sysResource.getId();
        //先删除
        relResourceDao.removeByResId(resId);
        //在添加
        List<RelResource> relResources = sysResource.getRelResources();
        for (RelResource relResource : relResources) {
            relResource.setId(UniqueIdUtil.getSuid());
            relResource.setResId(resId);
            relResourceDao.create(relResource);
        }
        super.update(sysResource);
    }

    @Override
    public List<SystemResourceEntity> getBySystemAndRole(String systemId, String roleId) {
        return sysResourceDao.getBySystemAndRole(systemId, roleId);
    }

    @Override
    public boolean isExist(SystemResourceEntity resource) {
        boolean rtn = sysResourceDao.isExist(resource)>0;
        return rtn;
    }

    @Override
    public void removeByResId(String resId) {
        List<SystemResourceEntity> list = getRecursionById(resId);
        for (SystemResourceEntity resource : list) {
            this.remove(resource.getId());
        }
    }


    @Override
    public void remove(String entityId) {
        relResourceDao.removeByResId(entityId);
        super.remove(entityId);
    }

    @Override
    public List<SystemResourceEntity> getRecursionById(String resId) {
        List<SystemResourceEntity> list = new ArrayList<SystemResourceEntity>();

        SystemResourceEntity resource = this.get(resId);
        list.add(resource);

        List<SystemResourceEntity> tmpList = sysResourceDao.getByParentId(resId);
        if (BeanUtils.isEmpty(tmpList)) return list;

        for (SystemResourceEntity sysResource : tmpList) {
            recursion(sysResource, list);
        }
        return list;
    }

    private void recursion(SystemResourceEntity sysResource, List<SystemResourceEntity> list) {
        list.add(sysResource);
        List<SystemResourceEntity> tmpList = sysResourceDao.getByParentId(sysResource.getId());
        if (BeanUtils.isEmpty(tmpList)) return;

        for (SystemResourceEntity resource : tmpList) {
            recursion(resource, list);
        }
    }

    @Override
    public List<SystemResourceEntity> getBySystemAndUser(String systemId, String userId) {
        List<SystemResourceEntity> list = sysResourceDao.getBySystemAndUser(systemId, userId);
        return list;
    }


}
